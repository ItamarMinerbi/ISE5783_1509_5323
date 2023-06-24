package lighting;

import primitives.*;
import java.util.List;

public class DirectionalLight extends Light implements LightSource {

    private Vector direction;

    /**
     * Constructs a DirectionalLight object with the specified intensity and the direction
     * of the light.
     *
     * @param intensity the intensity of the light represented by a {@link Color} object
     * @param direction the direction of the light represented by a {@link Vector} object
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    /**
     * Retrieves the intensity of the light at a given point.
     *
     * @param p The point at which the intensity is to be determined.
     * @return The intensity of the light at the specified point.
     */
    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity();
    }

    /**
     * Retrieves the direction from the light source to a given point.
     *
     * @param p The point for which the direction is to be determined.
     * @return The direction from the light source to the specified point as a Vector.
     */
    @Override
    public Vector getL(Point p) {
        return direction;
    }

    /**
     * Gets vectors from the given point to the light source
     *
     * @param p the point
     * @return all vectors who created
     */
    public List<Vector> getLBeam(Point p) {
        return List.of(getL(p));
    }

    /**
     * Retrieves the distance between the light source and a given point.
     *
     * @param point the point for which the distance is to be determined
     * @return the distance between the light source and the specified point as a double value
     */
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }
}
