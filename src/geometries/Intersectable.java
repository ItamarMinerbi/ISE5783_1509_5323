package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

public abstract class Intersectable {
    /**
     * This class is a PDS class that holds Geometry and point.
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        /**
         * A constructor for the GeoPoint class
         *
         * @param geometry The geometry of the GeoPoint.
         * @param point    The point of the GeoPoint.
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        /**

         Checks if this GeoPoint is equal to the specified object.
         @param o the object to compare
         @return {@code true} if the objects are equal, {@code false} otherwise.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || !(o instanceof GeoPoint geoPoint))
                return false;
            return geometry == geoPoint.geometry && point.equals(geoPoint.point);
        }

        /**

         Returns a string representation of this GeoPoint.
         @return a string representation of the GeoPoint.
         */
        @Override
        public String toString() {
            return "{ " + point + "," + geometry + " }";
        }
    }

/**

 Finds the geometric intersection points between the intersectable object and a given ray.
 This method should be implemented in subclasses.
 @param ray the ray to intersect with the object.
 @return a list of GeoPoint objects representing the intersection points,
 or {@code null} if no intersection occurs.
 */
 protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

    /**

     Finds the geometric intersection points between the intersectable object and a given ray.
     @param ray the ray to intersect with the object.
     @return a list of GeoPoint objects representing the intersection points,
     or an empty list if no intersection occurs.
     */
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    /**

     Finds the intersection points between the intersectable object and a given ray.
     @param ray the ray to intersect with the object.
     @return a list of Point objects representing the intersection points,
     or {@code null} if no intersection occurs.
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

}
