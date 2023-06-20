package lighting;

import primitives.*;
import geometries.*;
import java.util.List;
import java.util.LinkedList;
import static primitives.Util.*;

public class PointLight extends Light implements LightSource {
    private Point position;
    private double kC = 1;
    private double kL = 0;
    private double kQ = 0;

    /**
     * square edge size parameter
     */
    private int lengthOfTheSide = 9;

    /**
     * The amount of rays of the soft shadow.
     */
    public static int softShadowsRays = 36;


    /*
    * Make LightSource abstract class, add the lengthOfTheSide, softShadowsRays variables
    * to make soft shadow available to other Lights' types
    * */


    /**
     * Setter of the square edge size parameter
     *
     * @param lengthOfTheSide square edge size
     * @return the updated point light
     */
    public PointLight setLengthOfTheSide(int lengthOfTheSide) {
        if (lengthOfTheSide < 0)
            throw new IllegalArgumentException("LengthOfTheSide must be greater then 0");
        this.lengthOfTheSide = lengthOfTheSide;
        return this;
    }

    /**
     * Set the number of `soft shadows` rays
     *
     * @param numOfRays the number of `soft shadows` rays
     * @return the updated camera object
     */
    public PointLight setSoftShadowsRays(int numOfRays) {
        if (numOfRays < 0)
            throw new IllegalArgumentException("numOfRays must be greater then 0!");
        softShadowsRays = numOfRays;
        return this;
    }



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

    //region Setters (Builder Pattern)
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
    //endregion

    //region Getters
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
    //endregion

    @Override
    public List<Vector> getLBeam(Point p) {
        if (lengthOfTheSide == 0) return List.of(getL(p));

        List<Vector> vectors = new LinkedList<>();
        // help vectors
        Vector v0, v1;

        // A variable that tells how many divide each side
        double divided = Math.sqrt(softShadowsRays);

        // plane of the light
        Plane plane = new Plane(position, getL(p));

        // vectors of the plane
        List<Vector> vectorsOfThePlane = plane.findVectorsOfPlane();

        // Starting point of the square around the lighting
        Point startPoint = position.add(vectorsOfThePlane.get(0).normalize().scale(-lengthOfTheSide / 2.0))
                .add(vectorsOfThePlane.get(1).normalize().scale(-lengthOfTheSide / 2.0));

        // A loop that runs as the number of vectors and in each of its runs it brings a vector around the lamp
        for (double i = 0; i < lengthOfTheSide; i += lengthOfTheSide / divided) {
            for (double j = 0; j < lengthOfTheSide; j += lengthOfTheSide / divided) {
                v0 = vectorsOfThePlane.get(0).normalize()
                        .scale(random(i, i + lengthOfTheSide / divided));
                v1 = vectorsOfThePlane.get(1).normalize()
                        .scale(random(j, j + lengthOfTheSide / divided));
                vectors.add(p.subtract(startPoint.add(v0).add(v1)).normalize());
            }
        }
        return vectors;
    }
}
