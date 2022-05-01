package geometries;

import primitives.Ray;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * the class represent a set of geometries shapes that can be intersectable by a
 * ray
 */
public class Geometries extends Intersectable {
    private List<Intersectable> geometries = new LinkedList<>();

    /**
     * constructor that get list of geometries shapes
     * 
     * @param geometries shapes
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * add a set of geometries shapes to the list
     * 
     * @param geometries shapes
     */
    public void add(Intersectable... geometries) {
        if (geometries.length != 0)
            Collections.addAll(this.geometries, geometries);
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> intersections = null;
        for (Intersectable geometry : geometries) {
            var temp = geometry.findGeoIntersections(ray);
            if (temp != null) {
                if (intersections == null)
                    intersections = new LinkedList<>(temp);
                else
                    intersections.addAll(temp);
            }
        }
        return intersections;
    }

}