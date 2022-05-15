package lighting;

import primitives.*;
import static primitives.Util.*;

/**
 * the class represent a spot light in the scene
 */
public class SpotLight extends PointLight {
    /**
     * the direction of the light
     */
    private final Vector direction;

    /**
     * the narrowness of the light
     */
    private int narrowBeam = 1;

    /**
     * constructor, initialize the light with the given intensity and the direction
     * of the light
     * 
     * @param intensity intensity of the light
     * @param position  position of the light
     * @param direction direction of the light
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    /**
     * setter for narrowBeam field
     * 
     * @param narrowBeam the angle of the beam
     * @return the object itself
     */
    public SpotLight setNarrowBeam(int narrowBeam) {
        this.narrowBeam = narrowBeam;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        double dl = alignZero(direction.dotProduct(getL(p)));
        return dl <= 0 ? Color.BLACK : super.getIntensity(p).scale(Math.pow(dl,narrowBeam));
    }
}
