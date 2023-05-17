package lighting;

import primitives.*;

public class DirectionalLight extends Light implements LightSource {

    private Vector direction;

    /**
     * Constructs a DirectionalLight object with the specified intensity and the direction
     * of the light.
     *
     * @param intensity the intensity of the light represented by a {@link Color} object
     * @param direction the direction of the light represented by a {@link Vector} object
     */
    protected DirectionalLight(Color intensity, Vector direction) {
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
}
