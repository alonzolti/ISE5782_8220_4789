package unittests.geometries;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import geometries.*;
import primitives.*;


public class CylinderTest {
    @Test
    void testGetNormal() {
        
        Cylinder c1 = new Cylinder(new Ray(new Point(0,0,0), new Vector(1,0,0)), 1, 1);
// ============ Equivalence Partitions Tests on the top bases ==============
        Point p1 = new Point(1, 0.5, 0.5);
        Vector v1 = c1.getNormal(p1);
        // TC01: Test that normal vector is righty
//        assertEquals(new Vector(1, 0, 0), v1, "getNormal() wrong result ");
        assertTrue((new Vector(1, 0, 0) == v1 || new Vector(-1, 0, 0) == v1), "getNormal() wrong result ");
        // =============== Boundary Values Tests ==================
        p1 = new Point(1, 0, 0);
        v1 = c1.getNormal(p1);
        // TC01: Test that normal vector is righty
//        assertEquals(new Vector(1, 0, 0), v1, "getNormal() wrong result ");
        assertTrue((new Vector(1, 0, 0) == v1 || new Vector(-1, 0, 0) == v1), "getNormal() wrong result ");

// ============ Equivalence Partitions Tests on the top bases ==============
        p1 = new Point(0, 0.5, 0.5);
        v1 = c1.getNormal(p1);
        // TC01: Test that normal vector is righty
        assertTrue((new Vector(1, 0, 0) == v1 || new Vector(-1, 0, 0) == v1), "getNormal() wrong result ");
//        assertEquals(new Vector(1, 0, 0), v1, "getNormal() wrong result ");
    // =============== Boundary Values Tests ==================
        p1 = new Point(0, 0, 0);
        v1 = c1.getNormal(p1);
        // TC01: Test that normal vector is righty
        assertTrue((new Vector(1, 0, 0) == v1 || new Vector(-1, 0, 0) == v1), "getNormal() wrong result ");
//        assertEquals(new Vector(1, 0, 0), v1, "getNormal() wrong result ");
// ============ Equivalence Partitions Tests on the sides ==============
        Point p2 = new Point(0.5, 1, 0);
        Vector v2 = c1.getNormal(p2);
        // TC01: Test that normal vector is righty
        assertTrue((new Vector(0, -1, 0) == v2 || new Vector(0, -1, 0) == v2), "getNormal() wrong result ");
//        assertEquals(new Vector(0, 1, 0), v2, "getNormal() wrong result ");
    }
}
