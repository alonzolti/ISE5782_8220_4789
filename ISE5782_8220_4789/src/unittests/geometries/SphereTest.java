package unittests.geometries;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import geometries.*;
import primitives.*;

public class SphereTest {
    @Test
    void testGetNormal() {
        Sphere s1 = new Sphere(new Point(0, 0, 0), 1);

        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point(1, 0, 0);
        Vector v1 = s1.getNormal(p1);
        // TC01: Test that normal vector is righty
        assertEquals(new Vector(1, 0, 0), v1, "getNormal() wrong result ");

    }
}
