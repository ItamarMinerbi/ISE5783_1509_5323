/**

 The Tube class represents a tube in three-dimensional space.
 It extends the RadialGeometry class and is defined by a radius and an axis Ray.
 */
package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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
    public Tube(double radius, Ray axisRay)
    {
        super(radius);
        this.axisRay = axisRay;
    }

    /**
     * Gets the axis Ray of the tube.
     *
     * @return The axis Ray of the tube.
     */
    public Ray getAxisRay()
    {
        return axisRay;
    }

    /**
     * Returns null as the normal of the tube is undefined.
     *
     * @param point The point on the tube for which to calculate the normal (ignored).
     * @return null.
     */
    @Override
    public Vector getNormal(Point point)
    {
        return null;
    }
}
