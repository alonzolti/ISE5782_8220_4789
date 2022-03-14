package unittests.primitives;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import primitives.*;
public class PointTest {
    @Test
    void testAdd() {
        //=============== Equivalence Partitions Tests =============
        Point p1 = new Point(1,2,3);
        Vector p2 = new Vector(-2,-5,7);

        // TC01: Test adding between a point and a vector
        assertEquals(p1.add(p2), new Point(-1,-3,10));
    }

    @Test
    void testDistance() {
        
    }

    @Test
    void testDistanceSquared() {

    }

    @Test
    void testSubtract() {

    }
}
