package unittests.primitives;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import primitives.*;

public class PointTest {
    @Test
    void testAdd() {
        // =============== Equivalence Partitions Tests =============
        Point p1 = new Point(1, 2, 3);
        Vector v2 = new Vector(-2, -5, 7);

        // TC01: Test adding between a point and a vector
        assertEquals(new Point(-1, -3, 10), p1.add(v2));
    }

    @Test
    void testDistance() {
        // =============== Equivalence Partitions Tests =============
        Point p1 = new Point(2, 3, 4);
        Point p2 = new Point(4, -8, -6);

        // TC01: test distance between two points
        assertEquals(15, p1.distance(p2));

        // TC02: test distance between a point to itself
        assertEquals(0, p1.distance(p1));
    }

    @Test
    void testDistanceSquared() {
        // =============== Equivalence Partitions Tests =============
        Point p1 = new Point(2, 3, 4);
        Point p2 = new Point(4, -8, -6);

        // TC01: test distance between two points
        assertEquals(225, p1.distanceSquared(p2));

        // =============== Boundary Values Tests ==================
        // TC11: test distance between a point to itself
        assertEquals(0, p1.distance(p1));
    }

    @Test
    void testSubtract() {
        // =============== Equivalence Partitions Tests =============
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(-2, 4, 7);

        // TC01: test subtraction between two points
        assertEquals(new Vector(3, -2, -4), p1.subtract(p2));

        // =============== Boundary Values Tests ==================
        // TC11: vector zero
        assertThrows(IllegalArgumentException.class, () -> p1.subtract(p1));
    }
}