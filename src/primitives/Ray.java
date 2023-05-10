/**
 * The Ray class represents a ray in a three-dimensional space,
 * defined by a starting point and a direction vector.
 */
package primitives;

import java.util.List;

public class Ray {
    final Point p0;
    final Vector dir;

    public Point getPoint(double t){
        return p0.add(dir.scale(t));}

    /**
     * Constructs a new Ray object with the specified starting point and direction vector.
     *
     * @param p0 the starting point of the ray.
     * @param dir the direction vector of the ray.
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * Returns the starting point of the ray.
     *
     * @return the starting point of the ray.
     */
    public Point getP0() {
        return p0;
    }


    /**
     * Returns the direction vector of the ray.
     *
     * @return the direction vector of the ray.
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * Finds the point in the given list that is closest to p0.
     *
     * @param points a List of points to search through
     * @return the closest Point to p0, or null if the list is empty or null
     */
    public Point findClosestPoint(List<Point> points) {
        if (points == null || points.isEmpty()) {
            return null;
        }

        Point closestPoint = points.get(0);
        double minDistance = closestPoint.distance(p0);

        for (Point point : points) {
            if (point.distance(p0) < minDistance) {
                closestPoint = point;
                minDistance = closestPoint.distance(p0);
            }
        }
        return closestPoint;
    }

    /**
     * Checks if this Ray object is equal to another object.
     *
     * @param obj the object to compare with this Ray object.
     * @return true if the two objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;

        Ray otherRay = (Ray) obj;
        return otherRay.p0.equals(this.p0) &&
                otherRay.dir.equals(this.dir);
    }

    /**
     * Returns a string representation of this Ray object.
     *
     * @return a string representation of this Ray object.
     */
    @Override
    public String toString() {
        return "[" + p0 + ", " + dir + "]";
    }
}
