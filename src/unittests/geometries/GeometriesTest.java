package unittests.geometries;
import geometries.*;
import primitives.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class GeometriesTest {
    @Test
    void testFindIntersections()
    {
        Geometries gr=new Geometries(
            new Plane(new Point(0,0,1),new Point(1,0,1),new Point(0,1,1)),
            new Triangle(new Point(1,1,4),new Point(1,2,4),new Point(2,1,4))
        );
        // ============ Equivalence Partitions Tests ==============
        // TC01: some of the shapes intersected with the ray
        assertEquals(1, gr.findIntersections(new Ray(new Point(1,1,2),new Vector(1,3,-2))).size(),"some of the shpae intersect");

        // =============== Boundary Values Tests ==================
        // TC11: empty list(supposed to return null)
        gr = new Geometries();
        assertNull(gr.findIntersections(new Ray(new Point(1,1,1),new Vector(1,0,0))),"empty list");

        // TC12: zero intersection(supposed to return null)
        gr.add(new Triangle(new Point(1,1,1), new Point(0.5, -1, 0), new Point(1.5, -1, 0)),
            new Sphere(new Point(1, 0, 0), 1),
            new Plane(new Point(3, 0, 0),new Vector(0, 0, 1)));
        assertNull(gr.findIntersections(new Ray(new Point(0,0,10),new Vector(1,0,0))),"zero intersections");

        // TC13: only one shape interset with the ray
        gr.add(new Sphere(new Point(0, 0, 3), 3));
        assertEquals(1,gr.findIntersections(new Ray(new Point(0, 0, 4), new Vector(0, 0, 1))).size(),"one shape intersect");

        // TC14: all the shape intersect
        gr = new Geometries(new Triangle(new Point(-1,-1,8),new Point(2,0,8), new Point(-1,1,8)), new Plane(new Point(0, 0, 7), new Vector(0, 0, 1)), new Sphere(new Point(0, 0, 3), 2) );
        assertEquals(4,gr.findIntersections(new Ray(new Point(0, 0, 0.5), new Vector(0, 0, 1))).size(),"all the shape intersect");

    }
}
