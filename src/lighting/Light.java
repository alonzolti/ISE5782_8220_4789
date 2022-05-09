package lighting;

import primitives.Color;

/**
 * the class represent a light in the scene
 */
abstract class Light {
    protected final Color intensity;

    /**
     * constructor
     * 
     * @param intensity color of the light
     */
    protected Light(Color intensity) {
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
