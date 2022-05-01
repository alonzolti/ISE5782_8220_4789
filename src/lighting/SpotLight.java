package lighting;

import primitives.*;

/**
 * the class represent a spot light in the scene
 */
public class SpotLight extends PointLight {
    private Vector direction;

    /**
     * constructor
     * 
     * @param intensity intensity of the light
     * @param position  position of the light
     * @param direction direction of the light
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction;
    }

    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity(p).scale(Math.max(0d, direction.dotProduct(getL(p))));
    }
}
