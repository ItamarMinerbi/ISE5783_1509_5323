package renderer;

import primitives.*;
import lighting.*;
import scene.Scene;

import java.util.ArrayList;
import java.util.List;
import geometries.Intersectable.GeoPoint;
import static primitives.Util.*;

/**
 * A basic implementation of a ray tracer that extends the abstract RayTracerBase class.
 */
public class RayTracerBasic extends RayTracerBase {

    private static final Double3 INITIAL_K = new Double3(1.0);

    private static final int MAX_CALC_COLOR_LEVEL = 10;

    private static final double MIN_CALC_COLOR_K = 0.001;

    /**
     * Constructs a new RayTracerBasic object with the specified scene.
     *
     * @param scene the scene to be rendered by the ray tracer
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * Calculates the color at a specific point.
     *
     * @param closestPoint The point to find the color
     * @param ray The light ray
     * @return The color at the point
     */
    private Color calcColor(GeoPoint closestPoint, Ray ray) {
        return calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * Calculates the color at a specific point.
     *
     * @param intersection The intersection point to find the color
     * @param ray The light ray
     * @param level Level of recursion
     * @param k The transparency (%) * the K effect
     * @return The color of the intersection point
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
        Color color = intersection.geometry.getEmission()
                .add(calcLocalEffects(intersection, ray));
        return level == 1 ? color
                : color.add(calcGlobalEffects(intersection, ray, level, k));
    }


    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray) {
        Color color = Color.BLACK;
        Vector v = ray.getDir();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);

        Double3 kd = geoPoint.geometry.getMaterial().kD;
        Double3 ks = geoPoint.geometry.getMaterial().kS;
        int nShininess = geoPoint.geometry.getMaterial().nShininess;

        double nv = n.dotProduct(v);
        if (nv == 0)
            return color;

        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(geoPoint.point);
            double nl = n.dotProduct(l);
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                if(unshaded(lightSource, l, n, geoPoint)) {
                    Color lightIntensity = lightSource.getIntensity(geoPoint.point);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }

    /**
     * Calculates the global effects of reflection and refraction on the color of the given
     * intersection point.
     *
     * @param intersection the geometric point of intersection
     * @param ray the ray that intersected with the geometry
     * @param level the recursion level for ray tracing
     * @param k the coefficient for global effects
     * @return the resulting color after considering global effects
     */
    private Color calcGlobalEffects(GeoPoint intersection, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Double3 kr = intersection.geometry.getMaterial().kR;
        Double3 kt = intersection.geometry.getMaterial().kT;

        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);

        Ray reflectedRay = constructReflectedRay(intersection.point, v, n);
        Ray refractedRay = constructRefractedRay(intersection.point, v, n);
        color = color.add(calcGlobalEffect(reflectedRay, level, k, kr))
                     .add(calcGlobalEffect(refractedRay, level, k, kt));

        return color;
    }

    private Color calcGlobalEffect(Ray ray, int level, Double3 k, Double3 kx) {
        Double3 kkx = k.product(kx);
        if (kkx.lowerThan(MIN_CALC_COLOR_K))
            return Color.BLACK;

        GeoPoint gp = findClosestIntersection(ray);
        if (gp == null)
            return scene.background.scale(kx);

        return isZero(gp.geometry.getNormal(gp.point).dotProduct(ray.getDir()))
                ? Color.BLACK
                : calcColor(gp, ray, level - 1, kkx).scale(kx);
    }


    /**
     * Checks if a given point on a geometry is unshaded by a specific light source.
     *
     * @param light the light source to check
     * @param l the vector representing the direction from the light source to the point
     * @param n the normal vector at the point on the geometry
     * @param geoPoint the geometric point on the surface of the geometry
     * @return {@code true} if the point is unshaded by the light source, {@code false} otherwise
     */
    private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geoPoint) {
        Vector lightDirection = l.scale(-1);
        Ray lightRay = new Ray(geoPoint.point, lightDirection, n);

        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections != null && !intersections.isEmpty()) {
            for(GeoPoint intersection : intersections)
                if (intersection.point.distance(geoPoint.point) < light.getDistance(geoPoint.point) &&
                    intersection.geometry.getMaterial().kT == Double3.ZERO)
                    return false;
        }

        return true;
    }

    private Color calcDiffusive(Double3 kD, Vector l, Vector n, Color lightIntensity) {
        return lightIntensity.scale(kD.scale(Math.abs(l.dotProduct(n))));
    }

    private Color calcSpecular(Double3 kS, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(2 * (l.dotProduct(n))));
        double minusVR = -v.dotProduct(r);
        return alignZero(minusVR) <= 0 ? Color.BLACK //
                : lightIntensity.scale(kS.scale(Math.pow(minusVR, nShininess)));
    }


    /**
     * The function calculates the reflected ray.
     *
     * @param p The source of the ray
     * @param v The direction of the ray
     * @param n The normal vector to the geometry
     * @return The reflected ray
     */
    private Ray constructReflectedRay(Point p, Vector v, Vector n) {
        Vector reflectedVector = v.subtract(n.scale(2 * v.dotProduct(n)));
        return new Ray(p, reflectedVector, n);
    }

    /**
     * The function calculates the refracted ray.
     *
     * @param p The source of the ray
     * @param v The direction of the ray
     * @param n The normal vector to the geometry
     * @return The refracted ray
     */
    private Ray constructRefractedRay(Point p, Vector v, Vector n) {
        return new Ray(p, v, n);
    }


    /**
     * The function finds the closes point among the intersection points to the ray's source.
     *
     * @param ray The ray
     * @return The closes point to the ray's source
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        return intersections == null ? null : ray.findClosestGeoPoint(intersections);
    }


    /**
     * Calculates the color for a given ray in the scene. For now, this method returns null.
     *
     * @param ray the ray to trace
     * @return null
     */
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);

        return closestPoint == null ? scene.background
                                    : calcColor(closestPoint, ray);
    }
}
