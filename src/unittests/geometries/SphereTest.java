package unittests.geometries;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import java.util.List;

import geometries.*;
import primitives.*;

public class SphereTest {
    @Test
    void testGetNormal() {
        Sphere sphere = new Sphere(new Point(0,0,0), 5);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Noraml to a point on the sphere
        assertEquals(new Vector(0,1,0), sphere.getNormal(new Point(0,5,0)),"Wrong Normal");
    }

    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(new Point (1, 0, 0),1d);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),
                   "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result = sphere.findIntersections(new Ray(new Point(-1, 0, 0),
                                                                new Vector(3, 1, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        result = sphere.findIntersections(new Ray(new Point(0.5, 0.5, 0),new Vector(3, 1, 0)));
        assertEquals(1, result.size(), "Wrong number of points(supposed to be 1 point)");
        assertEquals(List.of(p2),result, "Ray starts from the inside of the sphere" );
        
        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(1,1,0.5),new Vector(3, 1, 0))),"Ray starts after the sphere");
        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray(new Point(0.0651530771650466, 0.355051025721682, 0),new Vector(3, 1, 0)));
        assertEquals(1, result.size(), "Wrong number of points(supposed to be 1 point)");
        assertEquals(List.of(p2),result, "Ray starts at start of the sphere" );

        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(p2,new Vector(3, 1, 0))),"Ray start at end of the sphere");

        // **** Group: Ray's line goes through the center
        p1 = new Point(1,-1,0);
        p2 = new Point(1,1,0);
        // TC13: Ray starts before the sphere (2 points)
        result = sphere.findIntersections(new Ray(new Point(1,-2,0),new Vector(0,1,0)));
        assertEquals(2, result.size(), "Wrong number of points(suppoed to be 2)");
        assertEquals(List.of(p1,p2),result,"Ray starts before the sphere and cross the center");
        
        // TC14: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray(new Point(1,-1,0),new Vector(0,1,0)));
        assertEquals(1, result.size(),"Wrong number of points(supposed to be 1)");
        assertEquals(List.of(p2), result, "Ray starts at sphere and cross the center");
        
        // TC15: Ray starts inside (1 points)
        result = sphere.findIntersections(new Ray(new Point(1,-0.5,0),new Vector(0,1,0)));
        assertEquals(1, result.size(), "Wrong number of points(supposed to be 1)");
        assertEquals(List.of(p2), result, "Ray start inside and cross the center");
        
        // TC16: Ray starts at the center (1 points)
        result = sphere.findIntersections(new Ray(new Point(1,0,0),new Vector(0,1,0)));
        assertEquals(1,result.size(), "Wrong number of points(supposed to be 1)");
        assertEquals(List.of(p2), result, "Ray start at the center");
       
        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(1,1,0),new Vector(0,1,0))),"Ray starts and the sphere and goes outside and cross the center");
        
        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(1,4,0),new Vector(0, 1, 0))),"Ray starts after the sphere, and cross the center");
        
        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        p1 = new Point(1,0,1);
        // TC19: Ray starts before the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(1,2,1), new Vector(0,-1,0))),"Ray stats before the tangent point");
        
        // TC20: Ray starts at the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(1,0,1),new Vector(0,-1,0))),"Ray starts at the tangent point");
        
        // TC21: Ray starts after the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(1,-2,1),new Vector(0,-1,0))),"Ray starts after the tangent point");
        
        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull(sphere.findIntersections(new Ray(new Point(1,0,4),new Vector(0,-1,0))),"Ray is orthogonal to the ray from the center");
    }

}
