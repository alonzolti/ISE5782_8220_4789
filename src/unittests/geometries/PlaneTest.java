package unittests.geometries;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import geometries.*;
import primitives.*;

public class PlaneTest {
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
