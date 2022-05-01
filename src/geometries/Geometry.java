package geometries;

import primitives.*;

/**
 * abstract class Geometry, represent shapes in space
 * using builder design pattern
 */
public abstract class Geometry extends Intersectable {
    protected Color emission = Color.BLACK;
    private Material material = new Material();

    /**
     * getter for emission field
     * 
     * @return emission
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * setter for emission field
     * 
     * @param e emission
     * @return this object
     */
    public Geometry setEmission(Color e) {
        this.emission = e;
        return this;
    }

    /**
     * getter for material field
     * 
     * @return material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * setter for material field
     * 
     * @param material material
     * @return the object itself
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * the function calculate the normal vector to a point on the geometric shape
     * 
     * @param p point on the shape
     * @return normal vector
     */
    public abstract Vector getNormal(Point p);
}
