package geometries;

import java.util.List;
import primitives.*;

/**
 * this interface represent a shape in space,
 * that can be intersected by a ray
 */
public interface Intersectable {
    /**
     * the function find all the intersection points between a ray and the geometric shape
     * @param ray, the ray that intersect the shape
     * @return list of all the points
     */
    public List<Point> findIntersections(Ray ray);
}
