package lighting;

import primitives.*;

/**
 * the class represent a directional light source in the scene
 */
public class DirectionalLight extends Light implements LightSource {
    /**
     * direction of the light
     */
    private final Vector direction;

    /**
     * constructor, initialize the light with the given intensity and the direction
     * of the light
     * 
     * @param intensity intensity of the light
     * @param direction direction of the light
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        return intensity;
    }

    @Override
    public Vector getL(Point p) {
        return direction;
    }

    @Override
    public double getDistance(Point p) {
        return Double.POSITIVE_INFINITY;
    }

}
