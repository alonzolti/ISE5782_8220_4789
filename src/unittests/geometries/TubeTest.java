package unittests.geometries;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import geometries.*;
import primitives.*;

public class TubeTest {
    @Test
    void testGetNormal() {
        Tube t1 = new Tube(new Ray(new Point(0,0,0), new Vector(1,0,0)), 1);
// ============ Equivalence Partitions Tests on the sides ==============
        Point p2 = new Point(0.5, 1, 0);
        Vector v2 = t1.getNormal(p2);
        // TC01: Test that normal vector is righty
        assertEquals(new Vector(0, 1, 0), v2, "getNormal() wrong result ");
    // =============== Boundary Values Tests ==================
        p2 = new Point(0.5, 1, 0);
        v2 = t1.getNormal(p2);
        // TC01: bva the point is in front of the axis ray point.
        assertEquals(new Vector(0, 1, 0), v2, "getNormal() wrong result ");
    }
}
