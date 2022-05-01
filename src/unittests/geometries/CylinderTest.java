package unittests.geometries;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
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
}
