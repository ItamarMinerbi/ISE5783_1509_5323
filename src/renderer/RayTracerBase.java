package renderer;

import primitives.*;
import scene.Scene;

/**
 * An abstract class that provides a base for ray tracing algorithms.
 */
public abstract class RayTracerBase {

    /**
     * The scene to be rendered by the ray tracer.
     */
    protected Scene scene;

    /**
     * Using as the super class for the inherited classes to set the scene
     *
     * @param scene the scene to be rendered by the ray tracer
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * Calculates the color for a given ray in the scene.
     * This method is implemented by concrete ray tracer implementations.
     *
     * @param ray the ray to trace
     * @return the color of the ray after it interacts with objects in the scene
     */
    public abstract Color traceRay(Ray ray);
}
