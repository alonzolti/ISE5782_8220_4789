package lighting;

import primitives.*;
//import javafx.scene.paint.Color;
/**
 * the class represent a ambient light in a scene
 */
public class AmbientLight {
    Color intensity;
    
    /**
     * constructor
     * @param Ia 
     * @param Ka 
     */
    public AmbientLight(Color Ia, Double3 Ka) {
        //Ip = Ka * Ia
        intensity = Ia.scale(Ka);
    }
    
    /**
     * default constructor
     */
    public AmbientLight() {
        intensity = Color.BLACK;
    }

    /**
     * getter for intensity field
     * @return  intensity
     */
    public Color getIntensity() {
        return intensity;
    }
    
}
