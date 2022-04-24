package unittests.geometries;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import geometries.*;
import primitives.*;

public class TriangleTest {
    @Test
    void testGetNormal() {
    // ============ Equivalence Partitions Tests ==============

        Triangle t1 = new Triangle(new Point(0,5,0),new Point(-5,-5,0), new Point(-5,5,0));
        Point p1 = new Point(0,0,0);
        Vector normal = t1.getNormal(p1);
        assertTrue((new Vector(0, 0, 1).equals(normal) || new Vector(0, 0, -1).equals(normal)),
            "getNormal() wrong result");
    // =============== Boundary Values Tests ==================
    //        Triangle t1 = new Triangle(new Point(0,5,0),new Point(0,5,0), new Point(-5,5,0));
    //        Triangle t2 = new Triangle(new Point(0,0,0), new Point(0,1,0), new Point(0,2,0));
}
    @Test
    void testFindIntersections()
    {
        Triangle t = new Triangle(new Point(1,1,0), new Point(1,2,0), new Point(2,1,0));
        // ============ Equivalence Partitions Tests ==============
        // TC01: intersect with the triangle(1 point)
        assertEquals(List.of(new Point(1.1,1.1,0)), t.findIntersections(new Ray(new Point(0,1,4), new Vector(1.1, 0.1, -4))),"ray intersect the plane");
        // TC02: don't intersect with the triangle, and against an edge(0 points)
        assertNull(t.findIntersections(new Ray(new Point(1,1,1),new Vector(-0.5,0.5,-1))),"ray intersect point against an edge");
        // TC03: don't intersect with the triangle, and against a vertex(0 points)
        assertNull(t.findIntersections(new Ray(new Point(1,1,1),new Vector(-0.5, -0.5,-1))),"ray intersect point against a vertex");
        
        // =============== Boundary Values Tests ==================
        // TC10: the point on edge(0 points)
        assertNull(t.findIntersections(new Ray(new Point(1,1,1),new Vector(0.5,0.5,-1))),"point on edge");
        // TC11: point on vertex(0 points)
        assertNull(t.findIntersections(new Ray(new Point(1,1,1),new Vector(0,0,-1))),"point on a vertex");
        // TC12: point on edge's continuation
        assertNull(t.findIntersections(new Ray(new Point(1,1,1),new Vector(-1,0,-1))),"point of edg'e continuation");
    }
}
