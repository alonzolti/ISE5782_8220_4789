package primitives;

/**
 * Point class represents a point in the space
 */
public class Point {
    final Double3 xyz;

    /**
     * constructor
     * 
     * @param xyz Double3 type, have 3 coordinates
     */
    Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * constructor
     * 
     * @param x, x cordinate
     * @param y, y coordinate
     * @param z, z coordinate
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
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
        if (obj == null)
            return false;
        if (!(obj instanceof Point))
            return false;
        Point other = (Point) obj;
        return xyz.equals(other.xyz);
    }
}
