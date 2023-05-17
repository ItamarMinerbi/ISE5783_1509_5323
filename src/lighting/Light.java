package lighting;

import primitives.*;

/**
 * An abstract class representing a light source.
 */
abstract class Light {
    private Color intensity;

    /**
     * Constructs a Light object with the specified intensity.
     * @param intensity the intensity of the light represented by a {@link primitives.Color} object
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * Returns the intensity value of the light.
     *
     * @return the {@link primitives.Color} object representing the color of the light
     */
    public Color getIntensity() {
        return intensity;
    }
}
