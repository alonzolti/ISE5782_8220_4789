package primitives;

/**
 * Point class represents a point in the space
 */
public class Point {
    protected final Double3 xyz;

    /**
     * Zero triad (0,0,0)
     */
    public static final Point ZERO = new Point(Double3.ZERO);

    /**
     * Constructs point with a triad of x/y/z coordinate values
     * 
     * @param xyz have 3 coordinates
     */
    Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * Constructs point with x/y/z coordinate values
     * 
     * @param x cordinate value
     * @param y coordinate value
     * @param z coordinate value
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * getter for x coordinate
     * 
     * @return x coordiante (double)
     */
    public double getX() {
        return xyz.d1;
    }

    /**
     * getter for y coordiante
     * 
     * @return y coordiante (double)
     */
    public double getY() {
        return xyz.d2;
    }

    /**
     * getter for z coordiante
     * 
     * @return z coordiante (double)
     */
    public double getZ() {
        return xyz.d3;
    }

    /**
     * subtraction between 2 points
     * 
     * @param p point
     * @return vector between this points. start from this to p
     */
    public Vector subtract(Point p) {
        return new Vector(xyz.subtract(p.xyz));
    }

    /**
     * add between point and a vector
     * 
     * @param v the vector
     * @return the result of adding the vector and the point
     */
    public Point add(Vector v) {
        return new Point(xyz.add(v.xyz));
    }

    /**
     * calculate the distance square between two points
     *
     * @param p the secodn point
     * @return distance square
     */
    public double distanceSquared(Point p) {
        double dx = p.xyz.d1 - xyz.d1;
        double dy = p.xyz.d2 - xyz.d2;
        double dz = p.xyz.d3 - xyz.d3;
        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * calculate the distance between two points.
     * using distanceSquared function
     * 
     * @param p second point
     * @return distance
     */
    public double distance(Point p) {
        return Math.sqrt(distanceSquared(p));
    }

    @Override
    public String toString() {
        return "Point: " + xyz.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || !(obj instanceof Point))
            return false;
        Point other = (Point)obj;
        return xyz.equals(other.xyz);
    }
}
