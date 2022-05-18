package unittests.geometries;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import geometries.*;
import primitives.*;

/**
 * unit tests for {@link geometries.Cylinder} class
 */
public class CylinderTest {
    /**
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}
     */
    @Test
    void testGetNormal() {
        Cylinder c = new Cylinder(new Ray(new Point(0, 0, 0), new Vector(1, 0, 0)), 1, 1);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test normal to a point on the size of the cylinder
        assertEquals(new Vector(0, 1, 0), c.getNormal(new Point(0.5, 1, 0)),
                "wrong result on the side of the cylinder");

        // TC02: Test normal on the bottom base
        assertEquals(new Vector(-1, 0, 0), c.getNormal(new Point(0, 0.5, 0.5)),
                "wrong result on the bottom base of the cylinder");

        // TC03: Test normal on the top base
        assertEquals(new Vector(1, 0, 0), c.getNormal(new Point(1, 0.5, 0.5)),
                "wrong result on the top base of the cylinder");

        // =============== Boundary Values Tests ==================
        // Tc11: Test normal from the center of the top base
        assertEquals(new Vector(1, 0, 0), c.getNormal(new Point(1, 0, 0)),
                "wrong result at the center of the top base");

        // Tc12: Test normal from the center of the bottom base
        assertEquals(new Vector(-1, 0, 0), c.getNormal(new Point(0, 0, 0)),
                "wrong result at the center of the bottom base");
    }
 
    /**
     * Test method for {@link geometries.Cylinder#findIntersections(Ray)}
     */
    @Test
    public void testFindIntersections() {
        Cylinder cylinder = new Cylinder(new Ray(new Point(0,1,1),new Vector(0,1,3)),2.5,3);
        Ray ray;
        // ============ Equivalence Partitions Tests ==============
        //TC01: Ray from outside intersect 2 sides(2 point)
        ray = new Ray(new Point(0,-4,2),new Vector(0,8,0));
        List<Point> result = cylinder.findIntersections(ray);
        assertEquals(result.size(),2,"wrong number of points");
        assertEquals(result,List.of(new Point(0,-1.5,2),new Point(0,3.5,2))
                ,"wrong intersect points");

        //TC02: Ray from outside under the base intersect also sides(2 points)
        result = cylinder.findIntersections(new Ray(new Point(0,-4,0),new Vector(0,8,2)));
        assertEquals(result.size(),2,"wrong number of points");
        assertEquals(result,List.of(new Point(0,0,1),new Point(0,3.5,1.75)),
                "wrong intersect points");

        //TC03: Ray from outside under the base intersect just the base(2 point)
        result = cylinder.findIntersections(new Ray(new Point(0,-1,0),new Vector(0,1,3)));
        assertEquals(result.size(),2,"wrong number of points");
        assertEquals(result,List.of(new Point(0,-2/3d,1),new Point(0,0,3)),"wrong intersect point\\s");

        assertNull(cylinder.findIntersections(new Ray(new Point(0,4,1),new Vector(0,1,3)))
                ,"wrong intersect point");

        // =============== Boundary Values Tests ==================
        //**** Group: ray from inside
        //TC10: Ray from inside intersect one base(1 point)
        assertEquals(cylinder.findIntersections(new Ray(new Point(0,1,2),new Vector(0,-1,-1))),
                List.of(new Point(0,0,1)),"wrong intersect point from inside to base");

        //TC11: Ray from inside intersect the side(1 point)
        assertEquals(cylinder.findIntersections(new Ray(new Point(0,1,2),new Vector(-2.5,0,0))),
                List.of(new Point(-2.5,1,2)),"wrong intersect point from inside to side");

        //**** Group: ray on tangent
        //TC12: Ray on tangent(0 point)
        assertNull(cylinder.findIntersections(new Ray(new Point(2.5,1,1),new Vector(0,1,3))),
                "wrong intersect point");
        //TC13: Ray on ths base(0 point)
        assertNull(cylinder.findIntersections(new Ray(new Point(2.5,1,1),new Vector(2,0,0))),
                "wrong intersect point");
    }
}
