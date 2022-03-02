package geometries;

import primitives.*;
/**
 * Tube class represents a tube in space
 */
public class Tube implements Geometry{
    final Ray axisRay;
    final double radius;

    /**
     * constructor
     * @param axisRay ray of the tube
     * @param radius radius of the tube
     */
    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    /**
     * getter for axisRay field
     * @return Ray
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * getter for radius field
     * @return double
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }

    @Override
    public String toString() {
        return "Tube: " + axisRay.toString() + "\nradius: " + radius;
    }  
}
