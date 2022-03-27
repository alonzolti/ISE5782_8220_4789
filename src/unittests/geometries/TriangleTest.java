package unittests.geometries;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import geometries.*;
import primitives.*;

public class TriangleTest {
    @Test
    void testGetNormal() {
    // ============ Equivalence Partitions Tests ==============

        Triangle t1 = new Triangle(new Point(0,5,0),new Point(-5,-5,0), new Point(-5,5,0));
        Point p1 = new Point(0,0,0);
        Vector normal = t1.getNormal(p1);
        assertTrue((new Vector(0, 0, 1).equals(normal) || new Vector(0, 0, -1).equals(normal)),
            "getNormal() wrong result");
    // =============== Boundary Values Tests ==================
//        Triangle t1 = new Triangle(new Point(0,5,0),new Point(0,5,0), new Point(-5,5,0));
//        Triangle t2 = new Triangle(new Point(0,0,0), new Point(0,1,0), new Point(0,2,0));
}
    @Test
    void testFindIntersections()
    {
        // ============ Equivalence Partitions Tests ==============
    }
}
