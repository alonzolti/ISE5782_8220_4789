package primitives;

/**
 * Vector class represents a vector in space
 */
public class Vector extends Point {

    /**
     * constructor with Double3 object
     * 
     * @param po, the coordinates of the vector
     */
    Vector(Double3 po) {
        super(po);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("zero vector/n");
    }

    /**
     * constructor with 3 doubles
     * 
     * @param x x coordinate
     * @param y y coordinate
     * @param z z coordinate
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("zero vector/n");
    }

    /**
     * add two vector together.
     * use function add in class Point
     * 
     * @param v the second vector
     * @return the result
     */
    public Vector add(Vector v) {
        return new Vector(xyz.add(v.xyz));
    }

    /**
     * multiplying a vector with scale paramter
     * 
     * @param sca scale paramter
     * @return the result
     */
    public Vector scale(double sca) {
        return new Vector(xyz.scale(sca));
    }

    /**
     * dot product between two vector
     * 
     * @param v second vector
     * @return the result of the dot product
     */
    public double dotProduct(Vector v) {
        double dotPX = xyz.d1 * v.xyz.d1;
        double dotPY = xyz.d2 * v.xyz.d2;
        double dotPZ = xyz.d3 * v.xyz.d3;
        return dotPX + dotPY + dotPZ;
    }

    /**
     * cross product between two vectors
     * 
     * @param v second vector
     * @return result
     */
    public Vector crossProduct(Vector v) {
        double cx = xyz.d2 * v.xyz.d3 - xyz.d3 * v.xyz.d2;
        double cy = xyz.d3 * v.xyz.d1 - xyz.d1 * v.xyz.d3;
        double cz = xyz.d1 * v.xyz.d2 - xyz.d2 * v.xyz.d1;
        return new Vector(cx, cy, cz);
    }

    /**
     * length sqaured of the vector
     * 
     * @return double, length squared of the vector
     */
    public double lengthSquared() {
        return xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3;
    }

    /**
     * calculate the length of the vector
     * use lengthSquared function in Vector
     * 
     * @return double, length of the vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * calculate the normalize vector
     * 
     * @return Vector, normalized vector in the same direction as the original vector
     */
    public Vector normalize() {
        return scale(1 / length());
    }

    @Override
    public String toString() {
        return "Vector: " + super.toString();
    }
    /**
     * multiplying the vector by const
     * 
     * @return Vector
     */
    public Vector sculMult(double skul){
        double dotPX = xyz.d1 * skul;
        double dotPY = xyz.d2 * skul;
        double dotPZ = xyz.d3 * skul;
        return new Vector(dotPX, dotPY, dotPZ);
    }
}
