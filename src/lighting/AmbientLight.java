package lighting;

import primitives.*;

/**
 * the class represent a ambient light in a scene
 */
public class AmbientLight extends Light {

    /**
     * constructor, initialize the ligth with the given intensity
     * 
     * @param iA intensity of the light
     * @param kA color of the light
     */
    public AmbientLight(Color iA, Double3 kA) {
        // Ip = Ka * Ia
        super(iA.scale(kA));
    }

    /**
     * default constructor
     */
    public AmbientLight() {
        super(Color.BLACK);
    }
}