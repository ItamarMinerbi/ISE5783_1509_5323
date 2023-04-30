package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

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


    /**

     Finds the intersection points between the plane and a given ray.
     @param ray the ray to intersect with the plane.
     @return a list of intersection points, or null if there are no intersections.
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        Vector rayDir = ray.getDir();
        Point rayP0 = ray.getP0();
        Vector v;
        try {
            v = p0.subtract(rayP0);
        } catch (IllegalArgumentException e) {
            // Return null if p0 and rayP0 are the same point
            return null;
        }
        double denominator = rayDir.dotProduct(normal);
        if (isZero(denominator)) {
            // Return null if the ray is parallel to the plane
            return null;
        }
        double t = alignZero(v.dotProduct(normal) / denominator);
        if (isZero(t) || t < 0) {
            // Return null if the intersection is behind the ray origin or at the origin
            return null;
        }
        Point intersectionPoint = ray.getPoint(t);
        return List.of(intersectionPoint);
    }

}
