package geometries;

/**
 * The RadialGeometry class is an abstract class that represents a geometry with a radius.
 * It implements the Geometry interface.
 */
public abstract class RadialGeometry implements Geometry {
    protected final double radius;

    /**
     * Constructs a new RadialGeometry object with the given radius.
     *
     * @param radius the radius of the radial geometry
     * @throws IllegalArgumentException if the radius is not positive
     */
    public RadialGeometry(double radius) {
        if (radius <= 0)
            throw new IllegalArgumentException("Radius must be positive");

        this.radius = radius;
    }

    /**
     * Returns the radius of the radial geometry.
     *
     * @return the radius of the radial geometry
     */
    public double getRadius() {
        return radius;
    }
}
