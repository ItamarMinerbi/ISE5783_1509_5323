package geometries;

import primitives.*;

/**
 * The Geometry interface represents a geometric object in three-dimensional space.
 */
public abstract class Geometry extends Intersectable {
    protected Color emission = Color.BLACK;
    private Material material = new Material();

    /**

     Returns the emission color of the geometry.
     @return the emission color of the geometry
     */
    public Color getEmission() {
        return emission;
    }


    /**
     * Returns the material of the geometry.
     *
     * @return the material of the geometry
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Sets the material of the geometry.
     *
     * @param material The new material
     * @return This Geometry object with the updated material
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**

     Sets the emission color of the geometry.
     @param emission the new emission color for the geometry
     @return the reference to the modified geometry object
     */

    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * Returns a normal vector to the geometry at the specified point.
     *
     * @param point the point on the geometry to retrieve the normal vector from
     * @return a normal vector to the geometry at the specified point
     */
    public abstract Vector getNormal(Point point);
}
