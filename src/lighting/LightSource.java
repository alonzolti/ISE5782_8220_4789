package lighting;

import primitives.*;

/**
 * the class represent a light source in the scene
 */
public interface LightSource {

    /**
     * getter for the intensity of the light on a specific point
     * 
     * @param p the point to get the intensity
     * @return the intensity of the light on the point
     */
    public Color getIntensity(Point p);

    /**
     * getter for the direction of the light source
     * 
     * @param p
     * @return
     */
    public Vector getL(Point p);
}
