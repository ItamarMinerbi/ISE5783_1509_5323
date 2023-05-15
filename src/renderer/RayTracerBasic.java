package renderer;

import primitives.*;
import scene.Scene;
import java.util.List;
import geometries.Intersectable.GeoPoint;

/**
 * A basic implementation of a ray tracer that extends the abstract RayTracerBase class.
 */
public class RayTracerBasic extends RayTracerBase {

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
     * @param point The point to find the color
     * @return The color of the point
     */
    private Color calcColor(GeoPoint geoPoint) {
        return scene.ambientLight.getIntensity().add(geoPoint.geometry.getEmission());
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
        return calcColor(closestGeoPoint);
    }
}
