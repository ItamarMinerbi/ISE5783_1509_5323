package lighting;

import primitives.*;

/**
 * The LightSource interface represents a light source in our lighting package.
 */
public interface LightSource {
    /**
     * Retrieves the intensity of the light at a given point.
     *
     * @param p The point at which the intensity is to be determined.
     * @return The intensity of the light at the specified point.
     */
    public Color getIntensity(Point p);

    /**
     * Retrieves the direction from the light source to a given point.
     *
     * @param p The point for which the direction is to be determined.
     * @return The direction from the light source to the specified point as a Vector.
     */
    public Vector getL(Point p);
}