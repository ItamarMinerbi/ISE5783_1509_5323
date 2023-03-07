package geometries;

import primitives.*;
import static primitives.Util.isZero;

/**
 * Cylinder class represents a cylinder in 3D space,
 * which is a tube with a certain height.
 * It extends the Tube class and adds a height attribute.
 */
public class Cylinder extends Tube {
    private final double height;

    /**
     * Constructs a new cylinder with the given radius,
     * axis ray, and height.
     *
     * @param radius  The radius of the cylinder.
     * @param axisRay The axis ray of the cylinder.
     * @param height  The height of the cylinder.
     */
    public Cylinder(double radius, Ray axisRay, double height) {
        super(radius, axisRay);
        this.height = height;
    }

    /**
     * Returns the height of the cylinder.
     *
     * @return The height of the cylinder.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the normal vector of the cylinder.
     *
     * @param point The point to get the normal at.
     * @return the normal vector of the cylinder at a specific point.
     */
    @Override
    public Vector getNormal(Point point) {
        Point p0 = axisRay.getP0();
        Vector v1 = axisRay.getDir();

        if (isZero(p0.distanceSquared(point))) {
            return v1.scale(-1);
        }

        double t = v1.dotProduct(point.subtract(p0));
        if (isZero(t)) {
            return v1.scale(-1);
        }

        if (isZero(t - this.height)) {
            return v1;
        }
        return point.subtract(p0.add(v1.scale(t))).normalize();
    }
}
