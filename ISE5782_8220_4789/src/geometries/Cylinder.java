package geometries;

import primitives.*;

/**
 * Cylinder class represent a cylinder in space
 */
public class Cylinder extends Tube{
  
    final double height;

    /**
     * constructor
     * @param axisRay ray of the cylinder
     * @param radius radius of the cylinder
     * @param height height of the cylinder
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    /**
     * getter for height
     * @return double
     */
    public double getHeight() {
        return height;
    }

    @Override
    public Vector getNormal(Point p) {
        return super.getNormal(p);
    }

    @Override
    public String toString() {
        return "Cylinder: " + axisRay.toString() + "\nradius: " + radius + "\nheight: " + height;
    }
}
