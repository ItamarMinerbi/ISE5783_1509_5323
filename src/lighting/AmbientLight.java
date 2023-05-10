package lighting;

import primitives.*;

 /**
  * The AmbientLight class represents an ambient light source in a 3D scene.
  * Ambient light is the uniform light that fills the entire scene and does not have a specific
  * direction or position.
  */
public class AmbientLight {
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    private Color intensity;

    /**
     * Constructs an instance of the AmbientLight class with the specified color and
     * vector coefficient.
     *
     * @param iA the {@link primitives.Color} object representing the color of the ambient light
     * @param kA the {@link primitives.Double3} object representing a vector coefficient to scale the intensity of the ambient light
     */
    public AmbientLight(Color iA, Double3 kA) {
        intensity = iA.scale(kA);
    }

    /**
     * Constructs an instance of the AmbientLight class with the specified color and coefficient
     * value.
     *
     * @param iA the {@link primitives.Color} object representing the color of the ambient light
     * @param kA the double value representing the coefficient value to scale the intensity of the ambient light
     */
    public AmbientLight(Color iA, double kA) {
        intensity = iA.scale(kA);
    }

    /**
     * Returns the intensity value of the ambient light.
     *
     * @return the {@link primitives.Color} object representing the color of the ambient light
     */
    public Color getIntensity() {
        return intensity;
    }
}
