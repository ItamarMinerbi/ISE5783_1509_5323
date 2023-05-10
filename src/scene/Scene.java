package scene;

import geometries.*;
import primitives.*;
import lighting.*;

/**
 * The Scene class represents a 3D scene consisting of a collection of geometries,
 * an ambient light source, and a background color.
 */
public class Scene {
    /**
     * The name of the scene.
     */
    public String name;

    /**
     * The background color of the scene.
     */
    public Color background = Color.BLACK;

    /**
     * The ambient light source of the scene.
     */
    public AmbientLight ambientLight = AmbientLight.NONE;

    /**
     * The collection of geometries that make up the scene.
     */
    public Geometries geometries = new Geometries();

    /**
     * Constructs an instance of the Scene class with the specified name.
     *
     * @param name the name of the scene
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * Sets the background color of the scene.
     *
     * @param background the {@link primitives.Color} object representing the new background color of the scene
     * @return the instance of the Scene class with the updated background color
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * Sets the ambient light source of the scene.
     *
     * @param ambientLight the {@link lighting.AmbientLight} object representing the new ambient light source of the scene
     * @return the instance of the Scene class with the updated ambient light source
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * Sets the collection of geometries that make up the scene.
     *
     * @param geometries the {@link geometries.Geometries} object representing the new collection of geometries that make up the scene
     * @return the instance of the Scene class with the updated collection of geometries
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}
