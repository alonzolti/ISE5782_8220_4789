package geometries;

import java.util.List;
import java.lang.Math;
import primitives.*;

/**
 * Sphere class represents sphere in space
 */
public class Sphere implements Geometry {
    final Point center;
    final double radius;

    /**
     * constructor
     * 
     * @param center center of the sphere
     * @param radius radius of the sphere
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * getter for center field
     * 
     * @return Point
     */
    public Point getCenter() {
        return center;
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
        return p.subtract(center).normalize() ;
    }


    @Override
    public List<Point> findIntersections(Ray ray) {
        //Ray -> P = P0 + t*v, t >0
        //Sphere points: |P-O|^2 - r^2 = 0

        Vector u = this.center.subtract(ray.getP0());
        double tm = ray.getDir().dotProduct(u);
        double d = Math.sqrt(u.lengthSquared() - tm*tm);
        double th = Math.sqrt(radius * radius - d * d);
        double t1 = tm + th;
        double t2 = tm - th;
        if(t1 > 0){
            Point p1 = ray.getP0().add(ray.getDir().scale(t1));
            if(t2 > 0)
            {
                Point p2 = ray.getP0().add(ray.getDir().scale(t2));
                return List.of(p1,p2);
            }
        }
        else if(t2 > 0)
        {
            return List.of(ray.getP0().add(ray.getDir().scale(t2)));
        }
        return null;
    }
}
