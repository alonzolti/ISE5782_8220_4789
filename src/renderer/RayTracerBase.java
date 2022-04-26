package renderer;
import scene.*;
import primitives.*;
public abstract class RayTracerBase {
    protected Scene scene;

    public RayTracerBase(Scene scene)
    {
        this.scene = scene;
    }

    abstract public Color traceRay(Ray ray);
}
