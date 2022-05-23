package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.*;
import static primitives.Util.*;

/**
 * Cylinder class represent a cylinder in space
 */
public class Cylinder extends Tube {

    private final double height;

    /**
     * constructor
     * 
     * @param axisRay ray of the cylinder
     * @param radius  radius of the cylinder
     * @param height  height of the cylinder
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    /**
     * getter for height
     * 
     * @return double, height of the cylinder
     */
    public double getHeight() {
        return height;
    }

    @Override
    public Vector getNormal(Point p) {
        Point p0 = axisRay.getP0();
        Vector dir = axisRay.getDir();
        Vector u;
        try {
            u = p.subtract(p0);
        } catch (IllegalArgumentException ignore) {
            return dir.scale(-1);
        }

        /// calculating the distance from the base point and the nearest point to the
        /// normal point
        // if the distance equals to 0 or the radius
        double cap = u.dotProduct(dir);
        if (isZero(cap)) // the point is on the lower base
            return dir.scale(-1);

        if (isZero(cap - height)) // the point is on the uppper base
            return dir;

        return super.getNormal(p); // if on the side
    }

    // bonus
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> res = new ArrayList<>();
        List<GeoPoint> lst = super.findGeoIntersectionsHelper(ray, maxDistance);
        if (lst != null)
            for (GeoPoint geoPoint : lst) {
                double distance = alignZero(geoPoint.point.subtract(axisRay.getP0()).dotProduct(axisRay.getDir()));
                if (distance > 0 && distance <= height)
                    res.add(geoPoint);
            }

        if (res.size() == 0)
            return null;
        return res;
    }

}
