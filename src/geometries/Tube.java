/**
 * The Tube class represents a tube in three-dimensional space.
 * It extends the RadialGeometry class and is defined by a radius and an axis Ray.
 */
package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;

public class Tube extends RadialGeometry {
    /**
     * The axis Ray of the tube.
     */
    protected final Ray axisRay;

    /**
     * Constructs a new Tube object with the specified radius and axis Ray.
     *
     * @param radius The radius of the tube.
     * @param axisRay The axis Ray of the tube.
     */
    public Tube(double radius, Ray axisRay) {
        super(radius);
        this.axisRay = axisRay;
    }

    /**
     * Gets the axis Ray of the tube.
     *
     * @return The axis Ray of the tube.
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * Returns the normal vector of the tube.
     *
     * @param point The point on the tube for which to calculate the normal.
     * @return the normal vector of the tube at a specific point.
     */
    @Override
    public Vector getNormal(Point point) {
        Point p0 = axisRay.getP0();
        Vector dir = axisRay.getDir();

        // the names t and o are from Dan's theoretical presentations.
        double t = dir.dotProduct(point.subtract(p0));
        Point o = isZero(t) ? p0 : p0.add(dir.scale(t));
        return point.subtract(o).normalize();
    }


    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Vector d = ray.getDir();
        Vector v = axisRay.getDir();
        double dv = d.dotProduct(v);

        if (ray.getP0().equals(axisRay.getP0())) {
            if (Util.isZero(dv)) {
                return List.of(new GeoPoint(this, ray.getPoint(radius)));
            }

            Vector dvv = v.scale(d.dotProduct(v));

            if (d.equals(dvv)) {
                return null;
            }
            double offset = Util.alignZero(Math.sqrt(radius * radius / d.subtract(dvv).lengthSquared()));
            return List.of(new GeoPoint(this, ray.getPoint(offset)));
        }

        Vector x = ray.getP0().subtract(axisRay.getP0());

        double xv = x.dotProduct(v);

        double a = 1 - dv * dv;
        double b = 2 * d.dotProduct(x) - 2 * dv * xv;
        double c = x.lengthSquared() - xv * xv - radius * radius;

        if (Util.isZero(a)) {
            if (Util.isZero(b)) {
                return null;
            }
            return List.of(new GeoPoint(this, ray.getPoint(-c / b)));
        }

        double delta = Util.alignZero(b * b - 4 * a * c);

        if (delta <= 0) {
            return null;
        }

        List<GeoPoint> intersections = null;
        double t = Util.alignZero(-(b + Math.sqrt(delta)) / (2 * a));
        if (t > 0) {
            intersections = new LinkedList<>();
            intersections.add(new GeoPoint(this, ray.getPoint(t)));
        }
        t = Util.alignZero(-(b - Math.sqrt(delta)) / (2 * a));
        if (t > 0) {
            if (intersections == null) {
                intersections = new LinkedList<>();
            }
            intersections.add(new GeoPoint(this, ray.getPoint(t)));
        }

        return intersections;
    }
}
