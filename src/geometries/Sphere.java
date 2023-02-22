package geometries;

import primitives.*;

public class Sphere extends RadialGeometry {
    private Point center;

    public Sphere(double radius, Point center)
    {
        super(radius);
        this.center = center;
    }

    public Point getCenter()
    {
        return center;
    }

    @Override
    public Vector getNormal(Point point)
    {
        return null;
    }
}
