package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import primitives.*;

/**
 * unit tests for {@link primitives.Vector} class
 */
public class VectorTest {
    /**
     * Test method for{@link primitives.Vector#add(primitives.Vector)}
     */
    @Test
    void testAdd() {
        // =============== Equivalence Partitions Tests =============
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -5, 7);
        Vector v3 = new Vector(-1, -2, -3);

        // TC01: test adding two vectors
        assertEquals(new Vector(-1, -3, 10), v1.add(v2), "wrong add result");

        // =============== Boundary Values Tests ==================
        // TC11: test adding two vectors that their result is vector zero
        assertThrows(IllegalArgumentException.class, () -> v1.add(v3), "creating zero vector");
    }

    /**
     * Test method for {@link primitives.Vector#CrossProduct(primitives.Vector)}
     */
    @Test
    void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(Util.isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(Util.isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}
     */
    @Test
    void testDotProduct() {
        Vector v1 = new Vector(3, 6, -19);
        Vector v2 = new Vector(-5, 8, 3);
        Vector v3 = new Vector(1, 0, 0);
        Vector v4 = new Vector(0, 0, 1);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test simple calculation of dot product
        assertEquals(-24, v1.dotProduct(v2), "wrong dotProduct result");

        // =============== Boundary Values Tests ==================
        // TC11: test, zero from dot product of orthogonal vectors
        assertEquals(0, v3.dotProduct(v4), "wrong dotProduct between orthogonal vectors");
    }

    /**
     * Test method for {@link primitives.Vector#length()}
     */
    @Test
    void testLength() {
        Vector v1 = new Vector(4, 4, 7);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test simple calculation of dot product
        assertEquals(9, v1.length(), "wrong length");
    }

    /**
     * Test method for {@link primitives.Vector#lengthSquared()}
     */
    @Test
    void testLengthSquared() {
        Vector v1 = new Vector(4, 4, 7);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test simple calculation of dot product
        assertEquals(81, v1.lengthSquared(), "wrong length squared");
    }

    /**
     * Test method for {@link primitives.Vector#normalize()}
     */
    @Test
    void testNormalize() {
        Vector v1 = new Vector(4, 4, 7);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test simple calculation of dot product
        assertEquals(new Vector(0.444444444444444444444444, 0.44444444444444444444444444, 0.77777777777777777777),
                v1.normalize(), "wrong normalization of the vector");
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}
     */
    @Test
    void testScale() {
        Vector v1 = new Vector(4, 7, -2);
        double scale1 = 2;
        double scale2 = 0;

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test simple calculation of scale
        assertEquals(new Vector(8, 14, -4), v1.scale(scale1), "wrong scale result");

        // =============== Boundary Values Tests ==================
        // TC11: test, zero vector
        assertThrows(IllegalArgumentException.class, () -> v1.scale(scale2), "scaling create zero vector");
    }

    /**
     * Test method for {@link primitives.Vector#subtract(primitives.Vector)}
     */
    @Test
    void testSubtract() {
        Vector v1 = new Vector(2, 2, 2);
        Vector v2 = new Vector(1, 2, 5);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test simple subtraction
        assertEquals(new Vector(1, 0, -3), v1.subtract(v2), "wrong substraction");

        // =============== Boundary Values Tests ==================
        // TC11: test, zero vector
        assertThrows(IllegalArgumentException.class, () -> v1.subtract(v1), "substractingi create vector zero");
    }
}
