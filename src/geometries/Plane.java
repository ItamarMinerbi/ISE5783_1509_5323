package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry
{
    Point p0;
    Vector normal;

    public Plane(Point p1, Point p2, Point p3)
    {
        this.p0 = p1;
        this.normal = null;
    }

    public Plane(Point p, Vector normal)
    {
        this.p0 = p;
        if (normal == null)
            throw new IllegalArgumentException("Vector cannot be null");

        this.normal = normal.normalize();
    }

    public Point getP0()
    {
        return p0;
    }

    public Vector getNormal()
    {
        return normal;
    }

    @Override
    public Vector getNormal(Point point)
    {
        return normal;
    }
}
