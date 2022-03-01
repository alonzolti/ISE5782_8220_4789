package primitives;

public class Point {
    Double3 xyz;

    /**
     * constructor 
     * @param xyz Double3 type, have 3 coordinates
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }
    
    /**
     * constructor
     * @param x, x cordinate
     * @param y, y coordinate
     * @param z, z coordinate
     */
    public Point(double x,double y, double z)
    {
        xyz = new Double3(x,y,z);
    }

    /**
     * substraction between 2 points 
     * @param second point
     * @return vector between this points. start from this to p
     */
    public Vector subtract(Point p)
    {
        return new Vector(xyz.subtract(p.xyz));
    }

    /**
     * add between point and a vector
     * @param v the vector
     * @return the result of adding the vector and the point
     */
    public Point add(Vector v)
    {
        return new Point(xyz.add(v.xyz));
    }

    /**
     * culculate the distance square between two points
     * @param p the secodn point
     * @return distance square
     */
    public double distanceSquared(Point p)
    {
        double x = p.xyz.d1 - xyz.d1;
        double y = p.xyz.d2 - xyz.d2;
        double z = p.xyz.d3 - xyz.d3;
        return x*x + y*y + z*z;
    }

    /**
     * culculate the distance between two points.
     * using distanceSquared function
     * @param p second point
     * @return distance
     */
    public double distance(Point p)
    {
        return Math.sqrt(distanceSquared(p));
    }


    @Override
    public String toString() {
        return "Point - " + xyz.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((xyz == null) ? 0 : xyz.hashCode());
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
        Point other = (Point) obj;
        if (xyz == null) {
            if (other.xyz != null)
                return false;
        } else if (!xyz.equals(other.xyz))
            return false;
        return true;
    }

    


    
}
