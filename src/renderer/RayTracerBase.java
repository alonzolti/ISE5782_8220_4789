package renderer;
import scene.*;

import java.util.List;

import primitives.*;
/**
 * abstract class that trace the ray from the camera into the scene
 */
public abstract class RayTracerBase {
    protected final Scene scene;

    /**
     * constructor
     * @param scene the scene
     */
    public RayTracerBase(Scene scene)
    {
        this.scene = scene;
    }
    /**
     * the function return the color of the first intersection point of the ray with the scene
     * @param ray the ray
     * @return color of the point
     */
    abstract public Color traceRay(Ray ray);
    /**
     * the function return the average color of the first intersection point of the rays with the scene
     * used for antialiasing
     * @param ray the ray
     * @return color of the point
     */
    abstract public Color traceRay(List<Ray> rays);
}
