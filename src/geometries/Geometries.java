package geometries;
 
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import primitives.*;

/**
 * This class represent a set of geometries shapes in space.
 */
public class Geometries implements Intersectable {

    private List<Intersectable> geometries;

    /**
     * default constructor
     */
    public Geometries(){
        this.geometries = new LinkedList<Intersectable>();
    }

    /**
     * constructor
     * @param geometries an array of the shape in the scene
     */
    public Geometries(Intersectable... geometries){
        this.geometries = new LinkedList<Intersectable>(Arrays.asList(geometries));
    }

    /**
     * the function adds array of shapes to the list
     */
    public void add(Intersectable... geometries){
        this.geometries.addAll(Arrays.asList(geometries));
    }

    @Override
    public List<Point> findIntersections(Ray ray){
        List<Point> intersections = null;
        for(Intersectable geometry: geometries)
        {
            List<Point> temp = geometry.findIntersections(ray);
            if(temp!=null)
            {
                if(intersections == null)
                    intersections = temp;
                else
                    intersections.addAll(temp);
            }
        }
        return intersections;
    }
}
