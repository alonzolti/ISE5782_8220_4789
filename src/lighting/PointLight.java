package lighting;

import static primitives.Util.*;
import primitives.*;

/**
 * the class represent a point light in the scene
 */
public class PointLight extends Light implements LightSource {
    /**
     * position of the light
     */
    private final Point position;

    /**
     * factor of the attenuation
     */
    private double kC = 1;
    private double kL = 0;
    private double kQ = 0;

    /**
     * constructor, initialize the light with the given intensity and the position
     * of the light
     * 
     * @param intensity intensity of the light
     * @param position  position of the light
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     * setter for kC field
     * 
     * @param kC constant of attenuation
     * @return the object itself
     */
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * setter for kL field
     * 
     * @param kL constant of attenuation
     * @return the object itself
     */
    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * setter for kQ field
     * 
     * @param kQ constant of attenuation
     * @return the object itself
     */
    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        double distance2 = position.distanceSquared(p);
        return intensity.scale(1 / (kC + kL * Math.sqrt(distance2) + kQ * distance2));
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    @Override
    public double getDistance(Point p) {
        return alignZero(position.distance(p));
    }

}
