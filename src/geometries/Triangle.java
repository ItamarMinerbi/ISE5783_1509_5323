/**
 * The Triangle class represents a three-sided polygon in three-dimensional space.
 * It extends the Polygon class and is defined by three Point objects.
 */
package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Triangle extends Polygon {

    /**
     * Constructs a new Triangle object with the specified vertices.
     *
     * @param p1 The first vertex of the triangle.
     * @param p2 The second vertex of the triangle.
     * @param p3 The third vertex of the triangle.
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }



    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point vertex1 = vertices.get(0);
        Point vertex2 = vertices.get(1);
        Point vertex3 = vertices.get(2);

        List<GeoPoint> intersections = plane.findGeoIntersectionsHelper(ray);

        if (intersections == null) {
            return null;
        }

        Point rayOrigin = ray.getP0();
        Vector rayDirection = ray.getDir();

        Vector edge1 = (vertex1.subtract(rayOrigin));
        Vector edge2 = (vertex2.subtract(rayOrigin));
        Vector edge3 = (vertex3.subtract(rayOrigin));

        double normal1 = alignZero(rayDirection.dotProduct((edge1.crossProduct(edge2)).normalize()));
        if (isZero(normal1)) {
            return null;
        }

        double normal2 = alignZero(rayDirection.dotProduct((edge2.crossProduct(edge3)).normalize()));
        if (normal1 * normal2 <= 0) {
            return null;
        }

        double normal3 = alignZero(rayDirection.dotProduct((edge3.crossProduct(edge1)).normalize()));
        if (normal1 * normal3 <= 0) {
            return null;
        }

        return List.of(new GeoPoint(this, intersections.get(0).point));
    }
}
