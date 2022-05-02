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

    private static final double DELTA = 0.1;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;

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
        GeoPoint closestPoint = ray.findClosestGeoPoint(scene.geometries.findGeoIntersections(ray));
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
    }

    private Color calcColor(GeoPoint p, Ray ray) {
        return calcColor(p, ray, MAX_CALC_COLOR_LEVEL, MIN_CALC_COLOR_K)
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * calculate the color of the point
     * 
     * @param p   the point
     * @param ray the ray
     * @return return the color of the point
     */
    private Color calcColor(GeoPoint p, Ray ray, int level, double k) {
        Color color = calcLocalEffects(p, ray,k);
        return 1 == level ? color : color.add(calcGlobalEffects(p, ray, level, k));
    }

    /**
     * calculate the local effects of the lights on the point
     * 
     * @param p   the point
     * @param ray the ray
     * @return return the color of the point with the local effects
     */
    private Color calcLocalEffects(GeoPoint p, Ray ray,double k) {
        Color color = p.geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = p.geometry.getNormal(p.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return color;
        Material material = p.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(p.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) {
                double ktr = transparency(p, lightSource,l,n,ray);
                if(ktr * k > MIN_CALC_COLOR_K) {
                    Color iL = lightSource.getIntensity(p.point);
                    color = color.add(iL.scale(calcDiffuse(material, nl)),
                            iL.scale(calcSpecular(material, n, l, nl, v)));
                }
            }
        }
        return color;
    }

    private Color calcGlobalEffects(GeoPoint p, Ray ray, int level, double k) {
        Color color = Color.BLACK;
        Material material = p.geometry.getMaterial();
        double kr = material.kR.getD1();
        double kkr = k * kr;
        Vector v = ray.getDir();
        Vector n = p.geometry.getNormal(p.point);
        if (kkr > MIN_CALC_COLOR_K) {
            color = color.add(calcGlobalEffect(constructReflectedRay(p.point, v, n), level, material.kR.getD1(), kkr));
        }

        double kt = material.kT.getD1();
        double kkt = k * kt;
        if (kkt > MIN_CALC_COLOR_K)
            color = color.add(calcGlobalEffect(constructRefractedRay(p.point, v, n), level, material.kT.getD1(), kkt));

        return color;
    }

    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint p = ray.findClosestGeoPoint(scene.geometries.findGeoIntersections(ray));
        return (p == null ? scene.background : calcColor(p, ray, level - 1, kkx)).scale(kx);
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
        return material.getKs().scale(Math.max(0, Math.pow(-v.dotProduct(r), material.getnShininess())));
    }

    /**
     * check if the point g is unshaded
     * 
     * @param g           the point
     * @param lightSource the light source
     * @param l           the light vector
     * @param n           the normal vector
     * @param nv          the dot product of n and v
     * @return true if the point is unshaded
     */
    private boolean unshaded(GeoPoint g, LightSource lightSource, Vector l, Vector n, double nv) {
        Vector lightDirection = l.scale(-1);
        Vector delVector = n.scale(nv < 0 ? DELTA : -DELTA);
        Point point = g.point.add(delVector);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null)
            return true;
        GeoPoint closestPoint = lightRay.findClosestGeoPoint(intersections);
        return lightSource.getDistance(g.point) < alignZero(point.distance(closestPoint.point));
    }

    private double transparency(GeoPoint p, LightSource light, Vector l, Vector n,Ray ray){
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if(intersections == null)
            return 1.0;
        double kt = p.geometry.getMaterial().getKt().getD1();
        double ktr = 1.0;
        for(GeoPoint g: intersections){
            if(g.point.distance(p.point) < light.getDistance(p.point))
                ktr *= kt;
            if(isZero(ktr))
                return 0;
        }
        return ktr;
    }

    private Ray constructReflectedRay(Point point, Vector v,Vector n){
        return null;
    }

    private Ray constructRefractedRay(Point point, Vector v, Vector n){

        return null;
    }
}
