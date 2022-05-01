package lighting;

import primitives.*;

/**
 * the class represent a ambient light in a scene
 */
public class AmbientLight extends Light {

    /**
     * constructor
     * 
     * @param Ia
     * @param Ka
     */
    public AmbientLight(Color Ia, Double3 Ka) {
        // Ip = Ka * Ia
        super(Ia.scale(Ka));
    }

    /**
     * default constructor
     */
    public AmbientLight() {
        super(Color.BLACK);
    }
}