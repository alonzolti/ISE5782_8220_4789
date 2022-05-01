package renderer;


import java.util.List;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import scene.*;
/**
 * a simple class for trace the ray
 */
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
        List<GeoPoint> points = scene.geometries.findGeoIntersections(ray);
        if(points == null)
            return scene.background;
        return calcColor(ray.findClosestGeoPoint(points));
    }

    /**
     * return the color in a specific point
     * @param p
     * @return
     */
    private Color calcColor(GeoPoint p){
        return scene.ambientLight.getIntensity().add(p.geometry.getEmission());
    }
}
