package geometries;

import primitives.Point;

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
}
