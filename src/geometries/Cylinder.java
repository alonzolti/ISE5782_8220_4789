package geometries;

import primitives.*;

/**
 * Cylinder class represent a cylinder in space
 */
public class Cylinder extends Tube {

    final double height;

    /**
     * constructor
     * 
     * @param axisRay ray of the cylinder
     * @param radius  radius of the cylinder
     * @param height  height of the cylinder
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    /**
     * getter for height
     * 
     * @return double, height of the cylinder
     */
    public double getHeight() {
        return height;
    }

    @Override
    public Vector getNormal(Point p) {
        double cap = axisRay.getP0().subtract(p).dotProduct(axisRay.getDir());
        Point meetingpoint = axisRay.getP0().add(axisRay.getDir().normalize().scale(cap));
        if(p.distance(meetingpoint) < radius){ // if it is on the base.
            return axisRay.getDir();
        }
        return super.getNormal(p); // if on the side
    }
}
