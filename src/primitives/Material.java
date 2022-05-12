package primitives;

/**
 * the class represent data about the material in the scene
 */
public class Material {
    /**
     * constant of diffuse
     */
    public Double3 kD = Double3.ZERO;
    /**
     * constant of specular
     */
    public Double3 kS = Double3.ZERO;
    /**
     * constant of transperancy
     */
    public Double3 kT = Double3.ZERO;
    /**
     * constant of reflection
     */
    public Double3 kR = Double3.ZERO;
    /**
     * constant of shininess
     */
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
     * setter for kD field
     * 
     * @param kD constant of diffuse
     * @return the object itself
     */
    public Material setKd(double kD) {
        this.kD = new Double3(kD);
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
     * setter for kS field
     * 
     * @param kS constant of specular
     * @return the object itself
     */
    public Material setKs(double kS) {
        this.kS = new Double3(kS);
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
     * setter for kR field
     * 
     * @param kR constant of reflection
     * @return the object itself
     */
    public Material setKr(double kR) {
        this.kR = new Double3(kR);
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
}
