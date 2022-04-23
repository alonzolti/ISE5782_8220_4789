package unittests.geometries;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import geometries.*;
import primitives.*;

public class PlaneTest {

    @Test
    void testConstructorPoints()
    {
        // ============ Equivalence Partitions Tests ==============
        // TC01: trying build correct plane with 3 points
        try {
            new Plane(new Point(1, 1, 1),new Point(2, 2, 2),new Point(2, 5, 6));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct plane");
        }

        //TC02: first and second points are the same
        assertThrows(IllegalArgumentException.class, ()-> new Plane(new Point(1,2,3),new Point(1,2,3),new Point(2,5,6)),
            "Build a plane when the first and second points were the same");

        //TC03: first and third points are the same
        assertThrows(IllegalArgumentException.class, ()-> new Plane(new Point(1,2,3),new Point(2,2,2), new Point(1,2,3)),
            "Build a plane when the first and third points were the same");
        
        //TC04: second and third points are the same
        assertThrows(IllegalArgumentException.class, ()-> new Plane(new Point(2,2,2), new Point(1,2,3), new Point(1,2,3)),
            "Build a plane when the seocnd and third poins were the same");

        //TC05: all the points are on the same line
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1,1,1), new Point(2,2,2), new Point(3,3,3)),
            "Build a plane when all the points were on the same line");

    }
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Plane p1 = new Plane(new Point(1, 2, 3), new Point(2, 1.2, 4), new Point(3, 4, 5));
        Plane p2 = new Plane(new Point(1, 2, 3), new Vector(3, 4, 5));
        Vector v1 = p1.getNormal(new Point(1, 2, 3));
       //TC01: Test that getNormal of Plane made of 3 points works
       assertTrue((new Vector(-0.7071067811865476, 0.0, 0.7071067811865476).equals(v1) || 
       new Vector(0.7071067811865476, 0.0, -0.7071067811865476).equals(v1)), 
       "getNormal() wrong result ");

       //TC02: Test that getNormal of Plane made of point and a normal works
       assertEquals(new Vector(3, 4, 5).normalize(), p2.getNormal(new Point(1, 2, 3)),
               "getNormal() wrong result");
    }

    @Test
    void testFindIntersections()
    {
            
    }
}
