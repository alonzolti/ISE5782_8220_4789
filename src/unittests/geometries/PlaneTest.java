package unittests.geometries;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import geometries.*;
import primitives.*;

public class PlaneTest {

    @Test
    void testConstructorPoints(){
        // ============ Equivalence Partitions Tests ==============
        // TC01: trying build correct plane with 3 points
        assertDoesNotThrow(
            () -> new Plane(new Point(1, 1, 1),new Point(2, 2, 2),new Point(2, 5, 6)),
            "Failed constructing a correct plane");

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
        Plane p = new Plane(new Point(0,0,1),new Point(0,1,1),new Point(1,0,1));
        // ============ Equivalence Partitions Tests ==============
        // TC01: ray intersect the plane
        assertEquals(List.of(new Point(1,1,1)), p.findIntersections(new Ray(new Point(4,2,0),new Vector(-3,-1,1))),"Ray intersect the plane");

        //TC02: ray doesn't intersect the plane
        assertNull(p.findIntersections(new Ray(new Point(4,4,4),new Vector(5,5,5))),"Ray doesn't intersect the plane");
    
        // =============== Boundary Values Tests ==================
        // ****Group: ray parallel to the plane
        // TC10: ray include in the plane(0 points)
        assertNull(p.findIntersections(new Ray(new Point(1,1,1),new Vector(1,5,0))),"Ray parallel and include in the plane");
        // TC11: ray isn't include in the plane(0 points)
        assertNull(p.findIntersections(new Ray(new Point(2,2,2),new Vector(1,5,0))),"ray parallel and no include in the plane");

        // **** Group: ray is orthogonal to the plane
        // TC12: ray starts before the plane(1 point)
        assertEquals(List.of(new Point(3,4,1)), p.findIntersections(new Ray(new Point(3,4,0),new Vector(0,0,1))),"ray orthogonal and starts before the plane");
        // TC13: ray starts at the plane(0 points)
        assertNull(p.findIntersections(new Ray(new Point(3,4,1),new Vector(0,0,1))),"ray orthogonal and starts at the plane");
        // TC14: ray starts after the plane(0 points)
        assertNull(p.findIntersections(new Ray(new Point(3,4,2),new Vector(0,0,1))),"ray orthogonal and starts after the plane");

        // TC15: ray starts at the plane(0 points)
        assertNull(p.findIntersections(new Ray(new Point(1,2,1),new Vector(4,3,2))),"ray starts at the plane");

        // TC16: ray starts at the same points as the plane was created(0 points)
        assertNull(p.findIntersections(new Ray(new Point(0,0,1),new Vector(4,3,2))),"Ray starts at the same point as the plane was created");

    }
}
