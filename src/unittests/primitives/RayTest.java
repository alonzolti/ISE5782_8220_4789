package unittests.primitives;


import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import primitives.*;

public class RayTest {
    @Test
    void testFindClosestPoint() {
        Ray ray = new Ray(new Point(0,0,0), new Vector(1,0,0));
        List<Point> points; 
            Point p1 = new Point(0.5,1,0);
            Point p2 = new Point(0,0.5,0);
            Point p3 = new Point(0,0.5,2);
        // =============== Equivalence Partitions Tests =============
        //TC01: The closest point is in the middle of the list
        points = new LinkedList<Point>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        assertEquals(p2, ray.findClosestPoint(points),"the point is in the middle isn't working");
        //================ Boundary Value Tests =====================
        //TC11: empty list
        points.clear();
        assertNull(ray.findClosestPoint(points),"boudary vslue - empty. is'nt working");
        //TC12: the closest point is the first
        points.clear();
        points.add(p2);
        points.add(p1);
        points.add(p3);
        assertEquals(p2, ray.findClosestPoint(points),"the point is in the first isn't working");
        //TC13: the closest point is the first
        points.clear();
        points.add(p1);
        points.add(p3);
        points.add(p2);
        assertEquals(p2, ray.findClosestPoint(points),"the point is in the last isn't working");
    }
}

