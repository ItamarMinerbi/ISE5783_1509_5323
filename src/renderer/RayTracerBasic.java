package renderer;

import primitives.*;
import scene.Scene;
import java.util.List;

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
    private Color calcColor(Point point) {
        return scene.ambientLight.getIntensity();
    }

    /**
     * Calculates the color for a given ray in the scene. For now, this method returns null.
     *
     * @param ray the ray to trace
     * @return null
     */
    @Override
    public Color traceRay(Ray ray) {
        List<Point> intersections = scene.geometries.findIntersections(ray);

        if (intersections == null || intersections.isEmpty()) {
            return scene.background;
        }
        Point closestPoint = ray.findClosestPoint(intersections);
        return calcColor(closestPoint);
    }
}
