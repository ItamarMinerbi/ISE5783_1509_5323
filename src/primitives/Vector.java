/**
 * The Vector class represents a mathematical vector in 3D space,
 * defined by its three Cartesian coordinates (x, y, z).
 * The class extends the Point class to utilize its mathematical operations.
 */
package primitives;

public class Vector extends Point {

    /**
     * Constructs a vector from three double values representing its three Cartesian coordinates (x, y, z).
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param z The z coordinate.
     * @throws IllegalArgumentException If the vector has a zero length.
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (new Double3(x, y, z).equals(Double3.ZERO)) {
            throw new IllegalArgumentException("a vector cannot be zero");
        }
    }

    /**
     * Constructs a vector from a Double3 object representing its three Cartesian coordinates (x, y, z).
     *
     * @param xyz The Double3 object representing the vector's coordinates.
     * @throws IllegalArgumentException If the vector has a zero length.
     */
    Vector(Double3 xyz) {
        super(xyz);
        if (xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("a vector cannot be zero");
        }
    }

    /**
     * Adds another vector to this vector and returns the result as a new Vector object.
     *
     * @param vec The vector to add to this vector.
     * @return A new Vector object representing the sum of this vector and the given vector.
     */
    public Vector add(Vector vec) {
        return new Vector(this.xyz.add(vec.xyz));
    }

    /**
     * Scales this vector by a scalar value and returns the result as a new Vector object.
     *
     * @param scalar The scalar value to scale this vector by.
     * @return A new Vector object representing the scaled vector.
     */
    public Vector scale(double scalar) {
        return new Vector(this.xyz.scale(scalar));
    }

    /**
     * Calculates the dot product of this vector and another vector.
     *
     * @param vec The other vector.
     * @return The dot product of this vector and the other vector.
     */
    public double dotProduct(Vector vec) {
        return Util.alignZero(
                (this.xyz.d1 * vec.xyz.d1) +
                        (this.xyz.d2 * vec.xyz.d2) +
                        (this.xyz.d3 * vec.xyz.d3));
    }

    /**
     * Calculates the cross product of this vector and another vector.
     *
     * @param vec The other vector.
     * @return A new Vector object representing the cross product of this vector and the other vector.
     */
    public Vector crossProduct(Vector vec) {
        return new Vector(
                Util.alignZero((this.xyz.d2 * vec.xyz.d3) - (this.xyz.d3 * vec.xyz.d2)),
                Util.alignZero((this.xyz.d3 * vec.xyz.d1) - (this.xyz.d1 * vec.xyz.d3)),
                Util.alignZero((this.xyz.d1 * vec.xyz.d2) - (this.xyz.d2 * vec.xyz.d1)));
    }

    /**
     * Calculates the squared length of this vector.
     *
     * @return The squared length of this vector.
     */
    public double lengthSquared() {
        return dotProduct(this);
    }

    /**
     * Calculates the length of this vector.
     *
     * @return The length of this vector.
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Returns a new vector that is the normalized version of this vector.
     *
     * @return a new vector that is the normalized version of this vector
     * @throws IllegalArgumentException if this vector has zero length
     */
    public Vector normalize() {
        double len = length();
        return scale(1 / len);
    }

    /**
     * Returns true if this vector is equal to the specified object.
     *
     * @param obj the object to compare this vector to
     * @return true if this vector is equal to the specified object, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;

        return ((Vector) obj).xyz.equals(this.xyz);
    }

    /**
     * Returns a string representation of this vector.
     *
     * @return a string representation of this vector
     */
    @Override
    public String toString() {
        return "->" + this.xyz.toString();
    }
}
