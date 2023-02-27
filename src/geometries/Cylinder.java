package geometries;

import primitives.*;

/**
 Cylinder class represents a cylinder in 3D space,
 which is a tube with a certain height.
 It extends the Tube class and adds a height attribute.
*/
public class Cylinder extends Tube {
    private final double height;

    /**
     * Constructs a new cylinder with the given radius,
     * axis ray, and height.
     * @param radius The radius of the cylinder.
     * @param axisRay The axis ray of the cylinder.
     * @param height The height of the cylinder.
     */
    public Cylinder(double radius, Ray axisRay, double height)
    {
        super(radius, axisRay);
        this.height = height;
    }

    /**
     * Returns the height of the cylinder.
     * @return The height of the cylinder.
     */
    public double getHeight()
    {
        return height;
    }

    /**
     * Returns null as the normal of a cylinder is not defined.
     * @param point The point to get the normal at (not used).
     * @return null as the normal of a cylinder is not defined.
     */
    @Override
    public Vector getNormal(Point point)
    {
        return null;
    }
}
