package lighting;

import primitives.Color;
/**
 * the class represent a light in the scene
 */
abstract class Light {
    private Color intensity;

    /**
     * constructor
     * 
     * @param intensity color of the light
     */
    public Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * getter for intensity field
     * 
     * @return the intensity of the light
     */
    public Color getIntensity() {
        return intensity;
    }
}