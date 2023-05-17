package primitives;

/**
 * The Material class represents the properties of a material used in rendering.
 */
public class Material {
    /**
     * The diffuse reflection coefficient of the material.
     */
    public Double3 kD = Double3.ZERO;

    /**
     * The specular reflection coefficient of the material.
     */
    public Double3 kS = Double3.ZERO;

    /**
     * The shininess value of the material.
     */
    public int nShininess = 0;

    /**
     * Sets the diffuse reflection coefficient of the material.
     *
     * @param kD The new diffuse reflection coefficient
     * @return This Material object with the updated diffuse reflection coefficient
     */
    public Material setKd(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Sets the diffuse reflection coefficient of the material.
     *
     * @param kD The new diffuse reflection coefficient
     * @return This Material object with the updated diffuse reflection coefficient
     */
    public Material setKd(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * Sets the specular reflection coefficient of the material.
     *
     * @param kS The new specular reflection coefficient
     * @return This Material object with the updated specular reflection coefficient
     */
    public Material setKs(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Sets the specular reflection coefficient of the material.
     *
     * @param kS The new specular reflection coefficient
     * @return This Material object with the updated specular reflection coefficient
     */
    public Material setKs(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * Sets the shininess of the material.
     *
     * @param nShininess The new shininess value.
     * @return This Material object with the updated shininess
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
