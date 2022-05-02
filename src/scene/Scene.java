package scene;

import java.util.LinkedList;
import java.util.List;

import geometries.Geometries;
import lighting.*;
import primitives.*;

/**
 * the class represent a scene
 * using builder design pattern
 */
public class Scene {
    public String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = new AmbientLight();
    public Geometries geometries = new Geometries();
    public List<LightSource> lights = new LinkedList<>();

    /**
     * constructor
     * 
     * @param name name of the scene
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * setter for background color
     * 
     * @param background color of the background
     * @return the object
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * setter for ambient Light
     * 
     * @param ambientLight the ligt
     * @return the object scene
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * setter for set of geometries
     * 
     * @param geometries set of geometries
     * @return the object scene
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * setter for lights field
     * 
     * @param lights set of lights
     * @return the object itself
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

    /**
     * bonus - not finished yet
     * the function parse xml file into scene object
     * 
     * @param path
     * @return
     */
    public Scene setXml(String path) {

        return this;
    }

}
