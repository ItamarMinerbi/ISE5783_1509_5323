package geometries;

import primitives.*;

public class Cylinder extends Tube {
    private double height;

    public Cylinder(double radius, Ray axisRay, double height)
    {
        super(radius, axisRay);
        this.height = height;
    }

    public double getHeight()
    {
        return height;
    }

    @Override
    public Vector getNormal(Point point)
    {
        return null;
    }
}
