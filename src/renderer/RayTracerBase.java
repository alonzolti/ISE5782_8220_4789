package renderer;
import scene.*;
import primitives.*;
/**
 * abstract class that trace the ray from the camera into the scene
 */
public abstract class RayTracerBase {
    protected Scene scene;

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
}
