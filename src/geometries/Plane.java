package geometries;

import primitives.*;

/**
 * The Plane class represents a plane in a 3-dimensional space.
 * A plane is defined by a point (p0) and a normal vector.
 */
public class Plane implements Geometry {
    final Point p0;
    final Vector normal;

    /**
     * Constructs a new Plane object from three given points.
     * The normal vector is calculated by the given points.
     *
     * @param p1 the first point on the plane.
     * @param p2 the second point on the plane.
     * @param p3 the third point on the plane.
     */
    public Plane(Point p1, Point p2, Point p3) {
        this.p0 = p1;

        // calculate the plane's normal
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        this.normal = v1.crossProduct(v2).normalize();
    }

    /**
     * Constructs a new Plane object from a given point and a normal vector.
     *
     * @param p      the point on the plane.
     * @param normal the normal vector of the plane.
     */
    public Plane(Point p, Vector normal) {
        this.p0 = p;
        this.normal = normal.normalize();
    }

    /**
     * Returns the point on the plane.
     *
     * @return the point on the plane.
     */
    public Point getP0() {
        return p0;
    }


    /**
     * Returns the normal vector of the plane.
     *
     * @return the normal vector of the plane.
     */
    public Vector getNormal() {
        return normal;
    }


    /**
     * Returns the normal vector of the plane at a given point.
     *
     * @param point any point on the plane.
     * @return the normal vector of the plane.
     */
    @Override
    public Vector getNormal(Point point) {
        return normal;
    }
}
