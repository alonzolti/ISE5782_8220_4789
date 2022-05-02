package primitives;

/**
 * the class represent data about the material in the scene
 */
public class Material {
    public Double3 kD = new Double3(0, 0, 0);
    public Double3 kS = new Double3(0, 0, 0);
    public Double3 kT = new Double3(0, 0, 0);
    public Double3 kR = new Double3(0, 0, 0);
    public int nShininess = 0;

    /**
     * setter for kD field
     * 
     * @param kD constant of diffuse
     * @return the object itself
     */
    public Material setKd(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * setter for kS field
     * 
     * @param kS constant of specular
     * @return the object itself
     */
    public Material setKs(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * setter for kT field
     * 
     * @param kT constant of transparency
     * @return the object itself
     */
    public Material setKt(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * setter for kR field
     * 
     * @param kR constant of reflection
     * @return the object itself
     */
    public Material setKr(Double3 kR) {
        this.kR = kR;
        return this;
    }

    /**
     * setter for nShininess field
     * 
     * @param ns shininess
     * @return the object itself
     */
    public Material setShininess(int ns) {
        this.nShininess = ns;
        return this;
    }

    /**
     * getter for kD field
     * 
     * @return the constant of diffuse
     */
    public Double3 getKd() {
        return kD;
    }


    /**
     * getter for kS field
     * 
     * @return the constant of specular
     */
    public Double3 getKs() {
        return kS;
    }

    /**
     * getter for kT field
     * 
     * @return constant of transparency
     */
    public Double3 getKt() {
        return kT;
    }

    /**
     * getter for kR field
     * 
     * @return constant of reflection
     */
    public Double3 getKr() {
        return kR;
    }

    /**
     * getter for nShininess field
     * 
     * @return the shininess of the material
     */
    public int getnShininess() {
        return nShininess;
    }
}
