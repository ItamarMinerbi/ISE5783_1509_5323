package geometries;

import primitives.*;

/**

 The Sphere class represents a geometric sphere object in 3D space.
 It extends the RadialGeometry class and includes a center point.
 */
public class Sphere extends RadialGeometry {
    private final Point center;

    /**
     * Constructs a sphere object with the given radius and center point.
     * @param radius The radius of the sphere.
     * @param center The center point of the sphere.
     */
    public Sphere(double radius, Point center)
    {
        super(radius);
        this.center = center;
    }

    /**
     * Returns the center point of the sphere.
     * @return The center point of the sphere.
     */
    public Point getCenter()
    {
        return center;
    }

    /**
     * Returns the normal vector to the surface of the sphere at the given point.
     * This method is not implemented for the Sphere class and always returns null.
     * @param point The point on the surface of the sphere.
     * @return Null because the Sphere class does not implement this method.
     */
    @Override
    public Vector getNormal(Point point)
    {
        return null;
    }
}
