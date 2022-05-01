package lighting;

import primitives.*;
/**
 * the class represent a directional light source in the scene
 */
public class DirectionalLight extends Light implements LightSource {
    private Vector direction;

    /**
     * constructor
     * @param intensity intensity of the light
     * @param direction direction of the light
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction;
    }

    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return direction;
    }

}
