package geometries;

import primitives.*;

/**
 * The Geometry interface represents a geometric object in three-dimensional space.
 */
public interface Geometry {
    /**
     * Returns a normal vector to the geometry at the specified point.
     *
     * @param point the point on the geometry to retrieve the normal vector from
     * @return a normal vector to the geometry at the specified point
     */
    Vector getNormal(Point point);
}
