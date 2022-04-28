package geometries;

import java.util.List;
import primitives.*;

/**
 * this interface represent a shape in space,
 * that can be intersected by a ray
 */
public interface Intersectable {
    /**
     * Finds all the intersection points between a ray and the geometric shape
     * @param ray the ray that intersect the shape
     * @return list of all the points or null if there are no intersection points
     */
    public List<Point> findIntersections(Ray ray);
}
