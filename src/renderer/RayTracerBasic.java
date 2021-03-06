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

    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double INITIAL_K = 1.0;

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
        GeoPoint closestPoint = findClosestIntersecion(ray);
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
    }

    /** 
     * calculate the color of the point
     * 
     * @param p   the point the point
     * @param ray the ray that intersected the point
     * @return the color of the point
     */
    private Color calcColor(GeoPoint p, Ray ray) {
        return calcColor(p, ray, MAX_CALC_COLOR_LEVEL, new Double3(INITIAL_K))
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * calculate the color of the point.
     * 
     * @param p     the point
     * @param ray   the ray that intersect the point
     * @param level level of recursion
     * @param k     the power of the ray
     * @return the color of the point
     */
    private Color calcColor(GeoPoint p, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(p, ray, k);
        return 1 == level ? color : color.add(calcGlobalEffects(p, ray, level, k));
    }

    /**
     * calculate the local effects of the lights on the point
     * 
     * @param p   the point
     * @param ray the ray that intersect the point
     * @param k   the power of the ray
     * @return return the color of the point with the local effects
     */
    private Color calcLocalEffects(GeoPoint p, Ray ray, Double3 k) {
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
                Double3 ktr = transparency(p, lightSource, l, n, lightSource.getDistance(p.point));
                if (!(ktr.scale(k).lowerThan(MIN_CALC_COLOR_K))) {
                    Color iL = lightSource.getIntensity(p.point).scale(ktr);
                    color = color.add(iL.scale(calcDiffuse(material, nl)),
                            iL.scale(calcSpecular(material, n, l, nl, v)));
                }
            }
        }
        return color;
    }

    /**
     * calculate the global effects of the lights on the point such as reflections
     * and refractions.
     * 
     * @param p     the point
     * @param ray   the ray that intersect the point
     * @param level level of recursion
     * @param k     the power of the ray
     * @return the color of the point with the global effects
     */
    private Color calcGlobalEffects(GeoPoint p, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Vector n = p.geometry.getNormal(p.point);
        Material material = p.geometry.getMaterial();
        Vector v = ray.getDir();

        Double3 kkr = material.kR.scale(k);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K)) {
            color = calcGlobalEffect(constructReflectedRay(p.point, v, n), level, material.kR, kkr);
        }

        Double3 kkt = material.kT.scale(k);
        if (!kkt.lowerThan(MIN_CALC_COLOR_K)) {
            color = color.add(calcGlobalEffect(constructRefractedRay(p.point, v, n), level, material.kT, kkt));
        }
        return color;
    }

    /**
     * helper method for calcGlobalEffects
     * 
     * @param ray   the ray that intersect the point
     * @param level level of recursion
     * @param kx    the power of the ray
     * @param kkx   the power of the hit.
     * @return the color of the point with the global effects
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 kx, Double3 kkx) {
        GeoPoint p = findClosestIntersecion(ray);
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
        return material.kD.scale(Math.abs(nl));
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
        double minusVR = alignZero(-v.dotProduct(r));
        return minusVR <= 0 ? Double3.ZERO : material.kS.scale(Math.pow(minusVR, material.nShininess));
    }

    /**
     * check if the point g is unshaded
     * 
     * @deprecated please use transparent(...) method instead of this one
     * @param g           the point
     * @param lightSource the light source
     * @param l           the light vector
     * @param n           the normal vector
     * @param nv          the dot product of n and v
     * @return true if the point is unshaded
     */
    @SuppressWarnings("unused")
    @Deprecated
    private boolean unshaded(GeoPoint g, LightSource lightSource, Vector l, Vector n, double nv, double distance) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(new Ray(g.point, l.scale(-1), n),
                distance);
        return intersections == null;
    }

    /**
     * calculate the transparency of the point
     * 
     * @param p        the point
     * @param light    the light source
     * @param l        the light vector
     * @param n        the normal to the point
     * @param ray      the ray to the point
     * @param distance the distance between the point and the light source
     * @return the transparency of the point
     */
    private Double3 transparency(GeoPoint p, LightSource light, Vector l, Vector n, double distance) {

        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(new Ray(p.point, l.scale(-1), n),distance);
        if (intersections == null)
            return new Double3(1, 1, 1);

        Double3 ktr = new Double3(1, 1, 1);
        for (GeoPoint gp : intersections) {
            ktr = ktr.scale(gp.geometry.getMaterial().kT);
            if (ktr.equals(Double3.ZERO))
                return Double3.ZERO;
        }
        return ktr;
    }

    /**
     * construct the reflected ray
     * 
     * @param point the intersection point
     * @param v     the ray direction
     * @param n     noraml to the intersection point
     * @return the reflected ray
     */
    private Ray constructReflectedRay(Point point, Vector v, Vector n) {
        Vector r = v.subtract(n.scale(v.dotProduct(n) * 2)).normalize();
        return new Ray(point, r, n);
    }

    /**
     * construct the refracted ray
     * 
     * @param point the intersection point
     * @param v     the ray direction
     * @param n     normal to the intersection point
     * @return the refracted ray
     */
    private Ray constructRefractedRay(Point point, Vector v, Vector n) {
        return new Ray(point, v, n);
    }

    /**
     * find the closest intersection point to the ray
     * 
     * @param ray the ray
     * @return the closest intersection point
     */
    private GeoPoint findClosestIntersecion(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null)
            return null;
        return ray.findClosestGeoPoint(intersections);
    }
}
