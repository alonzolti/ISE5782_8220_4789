package unittests.geometries;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import geometries.*;
import primitives.*;
public class SphereTest {
    @Test
    void testGetNormal() {
        Sphere s1 = new Sphere(new Point(0, 0, 0), 1);

        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point(1, 0, 0);
        Vector v1 = s1.getMormal(p1);
        // TC01: Test that normal vector is righty
        assertEquals(new Vector(1, 0, 0), v1, 0.00001, "getNormal() wrong result ");

    }
}
