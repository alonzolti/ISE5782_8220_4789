package primitives;

/**
 * the class represent data about the material in the scene
 */
public class Material {
    public Double3 kD = new Double3(0, 0, 0);
    public Double3 kS = new Double3(0, 0, 0);
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

    public Double3 getKd() {
        return kD;
    }

    public Double3 getKs() {
        return kS;
    }


    public int getnShininess() {
        return nShininess;
    }

    public void setnShininess(int nShininess) {
        this.nShininess = nShininess;
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
