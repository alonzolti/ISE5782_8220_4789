package primitives;

import static primitives.Util.*;
import java.util.List;

/**
 * Ray class represents a ray in the space
 */
public class Ray {
    private final Point p0;
    private final Vector dir;

    /**
     * constructor, normalizez the vector before saving it
     * 
     * @param p0  point
     * @param dir vector
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * getter for p0 field
     * 
     * @return point p0
     */
    public Point getP0() {
        return p0;
    }

    /**
     * getter for dir field
     * 
     * @return vector
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * calculate spacific place on the ray
     * @param t the scalar
     * @return return point on the ray
     * @throws IllegalArgumentException if t is negative
     */
    public Point getPoint(double t){
        if(t < 0)
            throw new IllegalArgumentException("t cannot be negative");
        return isZero(t) ? p0 : p0.add(dir.scale(t));
    }

    /**
     *
     * @param list of point
     * @return return the closest point to the satart of the ray
     */
    public Point findClosestPoint(List<Point> points){
        Point closestPoint = null;
        double distance;
        if(!points.isEmpty()){
            distance = p0.distance(points.get(0));
            closestPoint = points.get(0);
            for(int i = 0; i < points.size(); i++){
                if(p0.distance(points.get(i)) < distance){
                    closestPoint = points.get(i);
                    distance = p0.distance(points.get(i));
                }
            }
        }
        return closestPoint;
    }


    @Override
    public String toString() {
        return "Ray: " + p0.toString() + '\n' + dir.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || !(obj instanceof Ray other))
            return false;
        return p0.equals(other.p0) && dir.equals(other.dir);
    }
}
