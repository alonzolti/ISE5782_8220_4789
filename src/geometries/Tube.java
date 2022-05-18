package geometries;

import java.util.List;

import primitives.*;
import static primitives.Util.*;

/**
 * Tube class represents a tube in space
 */
public class Tube extends Geometry {
    protected final Ray axisRay;
    protected final double radius;

    /**
     * constructor
     * 
     * @param axisRay ray of the tube
     * @param radius  radius of the tube
     */
    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    /**
     * getter for axisRay field
     * 
     * @return Ray
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * getter for radius field
     * 
     * @return double
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public Vector getNormal(Point p) {
        double cap = alignZero(p.subtract(axisRay.getP0()).dotProduct(axisRay.getDir()));
        Point meetingpoint = axisRay.getPoint(cap);
        return p.subtract(meetingpoint);
    }

    
    
    /** 
     * return the intesection of ta ray and the tube
     * @param ray
     * @param maxDistance
     * @return List<GeoPoint>
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Point p0 = axisRay.getP0();
        Point rayP = ray.getP0();
        // TODO find intersect 
        
        return null;
    }
}
