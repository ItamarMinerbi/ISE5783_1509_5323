package renderer;

import primitives.*;
import lighting.*;
import scene.Scene;
import java.util.List;
import geometries.Intersectable.GeoPoint;
import static primitives.Util.*;

/**
 * A basic implementation of a ray tracer that extends the abstract RayTracerBase class.
 */
public class RayTracerBasic extends RayTracerBase {

    /**
     * The value of DELTA represents the small offset used for ray intersection calculations.
     */
    private static final double DELTA = 0.1;

    /**
     * Constructs a new RayTracerBasic object with the specified scene.
     *
     * @param scene the scene to be rendered by the ray tracer
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }


    /**
     * Calculates the color at a specific point
     * @param geoPoint The point to find the color
     * @return The color of the point
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return scene.ambientLight.getIntensity()
                .add(geoPoint.geometry.getEmission())
                .add(calcLocalEffects(geoPoint, ray));
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
        Vector deltaVector = n.scale(Util.alignZero(n.dotProduct(lightDirection)) > 0
                ? DELTA : -DELTA);
        Point startPoint = geoPoint.point.add(deltaVector);
        Ray lightRay = new Ray(startPoint, lightDirection);

        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections != null && !intersections.isEmpty()) {
            for(GeoPoint intersection : intersections)
                if (intersection.point.distance(geoPoint.point) < light.getDistance(geoPoint.point))
                    return false;
        }

        return true;
    }

    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray) {
        Color color = Color.BLACK;
        Vector v = ray.getDir();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);

        Double3 kd = geoPoint.geometry.getMaterial().kD;
        Double3 ks = geoPoint.geometry.getMaterial().kS;
        int nShininess = geoPoint.geometry.getMaterial().nShininess;

        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return color;

        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(geoPoint.point);
            double nl = alignZero(n.dotProduct(l));
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
     * Calculates the color for a given ray in the scene. For now, this method returns null.
     *
     * @param ray the ray to trace
     * @return null
     */
    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);

        if (intersections == null || intersections.isEmpty()) {
            return scene.background;
        }
        GeoPoint closestGeoPoint = ray.findClosestGeoPoint(intersections);
        return calcColor(closestGeoPoint, ray);
    }
}
