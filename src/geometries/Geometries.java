package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
/**
 * the class represent a set of geometries shapes that can be intersectable by a ray
 */
public class Geometries implements Intersectable {
    private List<Intersectable> geometries;

    /**
     * default constructor
     */
    public Geometries() {
        geometries = new LinkedList<>();
    }

    /**
     * constructor that get list of geometries shapes
     */
    public Geometries(Intersectable... geometries) {
        this.geometries = new LinkedList<>();
        Collections.addAll(this.geometries, geometries);

    }

    /**
     * the function add a set of geometries shapes to the list
     */
    public void add(Intersectable... geometries) {
        Collections.addAll(this.geometries, geometries);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> intersections = null;
        for (Intersectable geometry : geometries) {
            var Intersections = geometry.findIntersections(ray);
            if (Intersections != null) {
                if (intersections == null) {
                    intersections = new LinkedList<>();
                }
                intersections.addAll(Intersections);
            }
        }
        return intersections;
    }

}