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

    //bonus
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        // TODO Auto-generated method stub
        return null;
    }
}
