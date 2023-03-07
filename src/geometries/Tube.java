/**
 * The Tube class represents a tube in three-dimensional space.
 * It extends the RadialGeometry class and is defined by a radius and an axis Ray.
 */
package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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
}
