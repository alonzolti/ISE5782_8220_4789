package primitives;

/**
 * Ray class represents a ray in the space
 */
public class Ray {
    final Point p0;
    final Vector dir;

    /**
     * constructor, normalizez the vector before saving it
     * @param p0 point
     * @param dir vector
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * getter for p0 field
     * @return point
     */
    public Point getP0() {
        return p0;
    }

    /**
     * getter for dir field
     * @return vector
     */
    public Vector getDir() {
        return dir;
    }

    @Override
    public String toString() {
        return "Ray: " + p0.toString() + '\n'+  dir.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dir == null) ? 0 : dir.hashCode());
        result = prime * result + ((p0 == null) ? 0 : p0.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ray other = (Ray) obj;
        if (dir == null) {
            if (other.dir != null)
                return false;
        } else if (!dir.equals(other.dir))
            return false;
        if (p0 == null) {
            if (other.p0 != null)
                return false;
        } else if (!p0.equals(other.p0))
            return false;
        return true;
    } 
}
