package lighting;

import primitives.*;

import java.util.List;

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

    /**
     * Gets vectors from the given point to the light source
     *
     * @param p the point
     * @return all vectors who created
     */
    public List<Vector> getLBeam(Point p);

    /**
     * Retrieves the distance between the light source and a given point.
     *
     * @param point the point for which the distance is to be determined
     * @return the distance between the light source and the specified point as a double value
     */
    public double getDistance(Point point);
}
