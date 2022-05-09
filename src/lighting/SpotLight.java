package lighting;

import primitives.*;
import static primitives.Util.*;

/**
 * the class represent a spot light in the scene
 */
public class SpotLight extends PointLight {
    private final Vector direction;

    /**
     * constructor
     * 
     * @param intensity intensity of the light
     * @param position  position of the light
     * @param direction direction of the light
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        double dl = alignZero(direction.dotProduct(getL(p)));
        return dl <= 0 ? Color.BLACK : super.getIntensity(p).scale(dl);
    }
}
