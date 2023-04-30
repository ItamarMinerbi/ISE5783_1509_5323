/**
 * The Point class represents a point in three-dimensional space.
 * It is defined by its x, y, and z coordinates.
 */
package primitives;

public class Point {
    final Double3 xyz;

    public double getX(){return xyz.d1;}

    public double getY(){return xyz.d2;}

    public double getZ(){return xyz.d3;}


    /**
     * Constructs a new Point object with the specified x, y, and z coordinates.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @param z The z-coordinate of the point.
     */
    public Point(double x, double y, double z) {
        this.xyz = new Double3(x, y, z);
    }

    /**
     * Constructs a new Point object with the specified Double3 object.
     *
     * @param xyz The Double3 object containing the x, y, and z coordinates of the point.
     */
    Point(Double3 xyz) {
        this.xyz = new Double3(xyz.d1, xyz.d2, xyz.d3);
    }

    /**
     * Returns a new Point object which is the result of adding the specified Vector to this Point.
     *
     * @param vec The Vector to add to this Point.
     * @return A new Point object which is the result of adding the specified Vector to this Point.
     */
    public Point add(Vector vec) {
        return new Point(this.xyz.add(vec.xyz));
    }

    /**
     * Returns a new Vector object which is the result of subtracting the specified Point from this Point.
     *
     * @param point The Point to subtract from this Point.
     * @return A new Vector object which is the result of subtracting the specified Point from this Point.
     */
    public Vector subtract(Point point) {
        return new Vector(this.xyz.subtract(point.xyz));
    }

    /**
     * Returns the square of the distance between this Point and the specified Point.
     *
     * @param other The other Point.
     * @return The square of the distance between this Point and the specified Point.
     */
    public double distanceSquared(Point other) {
        double d1 = Util.alignZero(this.xyz.d1 - other.xyz.d1);
        double d2 = Util.alignZero(this.xyz.d2 - other.xyz.d2);
        double d3 = Util.alignZero(this.xyz.d3 - other.xyz.d3);

        // d^2 = x^2 + y^2 + z^2
        return d1 * d1 + d2 * d2 + d3 * d3;
    }

    /**
     * Returns the distance between this Point and the specified Point.
     *
     * @param other The other Point.
     * @return The distance between this Point and the specified Point.
     */
    public double distance(Point other) {
        // d = sqrt(x^2 + y^2 + z^2)
        return Util.alignZero(Math.sqrt(distanceSquared(other)));
    }

    /**
     * Determines whether this Point is equal to the specified Object.
     *
     * @param obj The Object to compare to this Point.
     * @return true if this Point is equal to the specified Object, false otherwise.
     */

    /**
     * Returns a String representation of this Point.
     *
     * @return A String representation of this Point.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;

        return ((Point) obj).xyz.equals(this.xyz);
    }

    @Override
    public String toString() {
        return this.xyz.toString();
    }
}
