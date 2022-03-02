package primitives;

/**
 * Vector class represents a vector in space
 */
public class Vector extends Point {

    /**
     * constructor with Double3 object
     * @param po, the coordinates of the vector
     */
    public Vector(Double3 po) {
        super(po);
        if(xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("zero vector/n");
    }

    /**
     * constructor with 3 doubles
     * @param x x coordinate
     * @param y y coordinate
     * @param z z coordinate
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if(xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("zero vector/n");
    }
    
    /**
     * add two vector together.
     * use function add in class point
     * @param v the second vector
     * @return the result
     */
    public Vector add(Vector v)
    {
        return new Vector(super.add(v).xyz);
    }

    /**
     * multiplying a vector with scale paramter
     * @param sca scale paramter
     * @return the result
     */
    public Vector scale(double sca)
    {
        return new Vector(xyz.scale(sca));
    }

    /**
     * dot product between two vector
     * @param v second vector
     * @return the result
     */
    public double dotProduct(Vector v)
    {
        Double3 dotP = xyz.product(v.xyz);
        return dotP.d1 + dotP.d2 + dotP.d3;
    }

    /**
     * cross product between two vectors
     * @param v second vector
     * @return result
     */
    public Vector crossProduct(Vector v)
    {
        double cx = xyz.d2 * v.xyz.d3 - xyz.d3 * v.xyz.d2;
        double cy = xyz.d3 * v.xyz.d1 - xyz.d1 * v.xyz.d3;
        double cz = xyz.d1 * v.xyz.d2 - xyz.d2 * v.xyz.d1; 

        return new Vector(new Double3(cx, cy, cz));
    }
    
    /**
     * length sqaured of the vector
     * @return length sqaured
     */
    public double lengthSquared()
    {
        return super.distanceSquared(new Point(Double3.ZERO));     
    }

    /**
     * calculate the length of the vector
     * use lengthSquared function in Vector
     * @return
     */
    public double length()
    {
        return Math.sqrt(lengthSquared());
    }
    
    /**
     * calculate the normalize vector
     * 
     * @return normalize vector
     */
    public Vector normalize()
    {
        return scale(1/length());
    }

    @Override
    public String toString() {
        return "Vector: " + super.toString();
    } 
}
