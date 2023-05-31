package lighting;

import primitives.*;

import static primitives.Util.alignZero;

public class SpotLight extends PointLight {
    private Vector direction;

    /**
     * Constructs a SpotLight object with the specified intensity, position and direction.
     *
     * @param intensity the intensity of the light represented by a {@link Color} object
     * @param position  the position of the light represented by a {@link Point} object
     * @param direction the direction of the light represented by a {@link Vector} object
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        double tmp = alignZero(direction.dotProduct(getL(p)));
        if (tmp <= 0)
            return Color.BLACK;
        return super.getIntensity(p).scale(tmp);
    }
}
