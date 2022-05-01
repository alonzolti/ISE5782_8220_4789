package unittests.geometries;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import geometries.*;
import primitives.*;

/**
 * unit tests for {@link geometries.Tube} class
 */
public class TubeTest {
    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point)}
     */
    @Test
    void testGetNormal() {
        Tube t1 = new Tube(new Ray(new Point(0, 0, 0), new Vector(1, 0, 0)), 1);

        // ============ Equivalence Partitions Tests on the sides ==============
        // TC01: Test that normal vector is righty
        assertEquals(new Vector(0, 1, 0), t1.getNormal(new Point(0.5, 1, 0)), "getNormal() wrong result ");

        // =============== Boundary Values Tests ==================
        // TC01: the point is in front of the axis ray point.
        assertEquals(new Vector(0, 1, 0), t1.getNormal(new Point(0, 1, 0)), "getNormal() wrong result ");
    }
}
