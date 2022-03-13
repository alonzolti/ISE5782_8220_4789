package geometries;

import primitives.*;

/**
 * interface Geometry, represent shapes in space
 */
public interface Geometry {
    /**
     * the function calculate the normal vector to a point on the geometric shape
     * 
     * @param p point on the shape
     * @return normal vector
     */
    public Vector getNormal(Point p);
}
