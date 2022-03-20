package geometries;

import java.util.List;

import primitives.*;
/**
 * triangle represents a triangle in space
 */
public class Triangle extends Polygon {

    /**
     * constructor
     * 
     * @param v1 point 1
     * @param v2 point 2
     * @param v3 point 3
     */
    public Triangle(Point v1, Point v2, Point v3) {
        super(v1, v2, v3);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        Plane p = new Plane(vertices.get(0),vertices.get(1),vertices.get(2));
        List<Point> points = p.findIntersections(ray);
        if(points == null)
            return null;
        Vector v1,v2,v3;
        v1 = vertices.get(0).subtract(ray.getP0());
        v2 = vertices.get(1).subtract(ray.getP0());
        v3 = vertices.get(2).subtract(ray.getP0());

        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();

        double r1,r2,r3;
        r1 = ray.getDir().dotProduct(n1);
        r2 = ray.getDir().dotProduct(n2);
        r3 = ray.getDir().dotProduct(n3);
        if(r1 > 0 && r2 >0 && r3 > 0 || r1 < 0 && r2 < 0 && r3 < 0)
            return points;
        return null;
    }

    
}
