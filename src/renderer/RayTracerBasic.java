package renderer;


import java.util.List;

import primitives.*;
import scene.*;

public class RayTracerBasic extends RayTracerBase {

    /**
     * constructor
     * @param scene
     */
    public RayTracerBasic(Scene scene)
    {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        List<Point> points = scene.geometries.findIntersections(ray);
        if(points == null)
            return scene.background;
        return calcColor(ray.findClosestPoint(points));
    }

    /**
     * return the color in a specific point
     * @param p
     * @return
     */
    private Color calcColor(Point p){
        return scene.ambientLight.getIntensity();
    }
}
