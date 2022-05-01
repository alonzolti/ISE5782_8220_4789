package renderer;

import java.util.List;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import scene.*;
import static primitives.Util.*;
import lighting.*;

/**
 * a simple class for trace the ray
 */
public class RayTracerBasic extends RayTracerBase {

    /**
     * constructor
     * 
     * @param scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> points = scene.geometries.findGeoIntersections(ray);
        if (points == null)
            return scene.background;
        return calcColor(ray.findClosestGeoPoint(points), ray);
    }

    /**
     * calculate the color of the point
     * 
     * @param p   the point
     * @param ray the ray
     * @return return the color of the point
     */
    private Color calcColor(GeoPoint p, Ray ray) {
        return scene.ambientLight.getIntensity().add(calcLocalEffects(p, ray));
    }

    /**
     * calculate the local effects of the lights on the point
     * @param p the point
     * @param ray the ray
     * @return return the color of the point with the local effects
     */
    private Color calcLocalEffects(GeoPoint p, Ray ray){
        Color color = p.geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = p.geometry.getNormal(p.point);
        double nv = alignZero(n.dotProduct(v));
        if(nv == 0)
            return color;
        Material material =  p.geometry.getMaterial();
        for(LightSource lightSource:scene.lights){
            Vector l = lightSource.getL(p.point);
            double nl = alignZero(n.dotProduct(l));
            if(nl * nv > 0){
                Color iL =  lightSource.getIntensity(p.point);
                color = color.add(iL.scale(calcDiffuse(material, nl)),
                    iL.scale(calcSpecular(material, n,l,nl,v)));
            }
        }
        return color;
    }

    /**
     * calculate the color of the point with diffuse
     * 
     * @param p   the point
     * @param ray the ray
     * @return return the color of the diffuse
     */
    private Double3 calcDiffuse(Material material, double nl) {
        return material.getKd().scale(Math.abs(nl));
    }

    /**
     * calculate the color of the point with specular
     * 
     * @param p   the point
     * @param ray the ray
     * @return return the color of the specular
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale((l.dotProduct(n) * 2)));
        return material.getKs().scale(Math.max(0,Math.pow(-v.dotProduct(r), material.getnShininess())));
    }
}
