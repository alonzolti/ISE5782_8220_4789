package geometries;

import java.util.List;

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
        if(p.equals(axisRay.getP0())){
            return axisRay.getDir().scale(-1);
        }
        double cap = p.subtract(axisRay.getP0()).dotProduct(axisRay.getDir());
        if(cap == 0){
            return axisRay.getDir().scale(-1);
        }
 //       Point meetingpoint = axisRay.getP0().add(axisRay.getDir().normalize().scale(cap));
 //       if(meetingpoint.equals(axisRay.getP0()) || meetingpoint.equals(axisRay.getP0().add(axisRay.getDir()))){ // if it is on the base.
        if (cap == radius){
            return axisRay.getDir(); 
        }
        return super.getNormal(p); // if on the side
    }

    //bonus
    @Override
    public List<Point> findIntersections(Ray ray) {
        // TODO Auto-generated method stub
        return super.findIntersections(ray);
    }

    
}
