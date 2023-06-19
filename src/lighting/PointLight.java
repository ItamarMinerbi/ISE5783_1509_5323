package lighting;

import primitives.*;

public class PointLight extends Light implements LightSource {
    private Point position;
    private double kC = 1;
    private double kL = 0;
    private double kQ = 0;


    /**
     * Constructs a PointLight object with the specified intensity and position.
     *
     * @param intensity the intensity of the light represented by a {@link Color} object
     * @param position the position of the light represented by a {@link Point} object
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     * The function sets the kC coefficient.
     *
     * @param kC the new kC value for the coefficient.
     * @return the updated PointLight with the new updated value.
     */
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * The function sets the kL coefficient.
     *
     * @param kL the new kL value for the coefficient.
     * @return the updated PointLight with the new updated value.
     */
    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * The function sets the kQ coefficient.
     *
     * @param kQ the new kQ value for the coefficient.
     * @return the updated PointLight with the new updated value.
     */
    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }


    /**
     * Retrieves the intensity of the light at a given point.
     *
     * @param p The point at which the intensity is to be determined.
     * @return The intensity of the light at the specified point.
     */
    @Override
    public Color getIntensity(Point p) {
        double d = p.distance(position);
        return getIntensity().reduce(kC + kL * d + kQ * d * d);
    }

    /**
     * Retrieves the direction from the light source to a given point.
     *
     * @param p The point for which the direction is to be determined.
     * @return The direction from the light source to the specified point as a Vector.
     */
    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    /**
     * Retrieves the distance between the light source and a given point.
     *
     * @param point the point for which the distance is to be determined
     * @return the distance between the light source and the specified point as a double value
     */
    public double getDistance(Point point) {
        return position.distance(point);
    }
}
