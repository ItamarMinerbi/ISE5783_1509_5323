package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Unit tests for primitives.Vector class
 */
class VectorTests {

    /**
     * Test method for {@link primitives.Vector#add(Vector)}.
     */
    @Test
    void testadd() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v = new Vector(1, 1, 1);
        // ============ Equivalence Partitions Tests ==============

        // TC01: Test the vector after adding 2 vectors
        assertEquals(new Vector(3, 8, 10),
                v1.add(new Vector(2, 6, 7)),
                "ERROR: add() does not work correctly");


        // =============== Boundary Values Tests ==================

        // TC10: adding to vector his opposite vector
        assertThrows(IllegalArgumentException.class,
                () -> v1.add(new Vector(-1, -2, -3)),
                "ERROR: Adding the opposite vector should throw an exception because it creates vector 0");

        // TC11: Test the vector after adding himself
        assertEquals(v1.scale(2),
                v1.add(v1),
                "ERROR: add() does not work correctly");

        // TC12: Test add vector to himself after scale
        assertEquals(new Vector(3, 3, 3),
                v.add(v.scale(2)),
                "ERROR: add() does not work correctly"); // (1,1,1) + (2,2,2)
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
    @Test
    void testscale() {
        Vector v1 = new Vector(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============

        // TC01: scaling vector with non-zero number
        assertEquals(new Vector(-2.5, -5, -7.5),
                v1.scale(-2.5),
                "ERROR: Scaling a vector with non-zero number does not work correctly");


        // =============== Boundary Values Tests ==================

        // TC10: Test the vector after scale his point with 0
        assertThrows(IllegalArgumentException.class,
                () -> v1.scale(0),
                "ERROR: Scaling a vector with zero should throw an exception because it creates vector 0");
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(Vector)}.
     */
    @Test
    void testdotProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);
        Vector v4 = new Vector(2, 4, 6);
        // ============ Equivalence Partitions Tests ==============

        // TC01: Test the value of dot product between two vectors with angle grater than 90d
        assertEquals(-28,
                v1.dotProduct(v2),
                0.00001,
                "ERROR: dotProduct() returns wrong result");

        // TC02: Test the value of dot product between two vectors with angle less than 90d
        assertEquals(28,
                v1.dotProduct(v4),
                0.00001,
                "ERROR: dotProduct() returns wrong result");


        // =============== Boundary Values Tests ==================

        // TC01: dot product between orthogonal vectors
        assertTrue(isZero(v1.dotProduct(v3)),
                "ERROR: dotProduct() for orthogonal vectors is not zero");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(Vector)}.
     */
    @Test
    void testcrossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(0, 3, -2);
        Vector v3 = new Vector(-2, -4, -6);
        Vector vr = v1.crossProduct(v2);
        // ============ Equivalence Partitions Tests ==============

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals(v1.length() * v2.length(),
                vr.length(),
                0.00001,
                "ERROR: crossProduct() returns wrong result");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)),
                "ERROR: crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)),
                "ERROR: crossProduct() result is not orthogonal to 2nd operand");


        // =============== Boundary Values Tests ==================

        // TC10: Test zero vector from cross-product of co-lined vectors
        assertThrows(IllegalArgumentException.class,
                () -> v1.crossProduct(v3),
                "ERROR: crossProduct() for parallel vectors does not throw an exception");
    }

    /**
     * Test method for {@link primitives.Vector#lengthSquared()}.
     */
    @Test
    void testlengthSquared() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -2, -1);
        // ============ Equivalence Partitions Tests ==============

        // TC01: Test that squared length of the vector
        assertEquals(14,
                v1.lengthSquared(),
                0.00001,
                "ERROR: lengthSquared() returns wrong result");

        // TC02: Test that squared length of the negative vector
        assertEquals(9,
                v2.lengthSquared(),
                0.00001,
                "ERROR: lengthSquared() returns wrong result");
    }

    /**
     * Test method for {@link primitives.Vector#length()}.
     */
    @Test
    void testlength() {
        Vector v1 = new Vector(2, 2, 1);
        Vector v2 = new Vector(-2, -2, -1);
        // ============ Equivalence Partitions Tests ==============

        // TC01: Test that length of the vector
        assertEquals(v1.length(),
                3,
                "ERROR: length() returns wrong result");

        // TC02: Test that length of the vector
        assertEquals(v2.length(),
                3,
                "ERROR: length() returns wrong result");
    }

    /**
     * Test method for {@link primitives.Vector#normalize()}.
     */
    @Test
    void testnormalize() {
        Vector v = new Vector(1, 2, 3).normalize();
        Vector v1 = new Vector(-1, -2, -3).normalize();
        // ============ Equivalence Partitions Tests ==============

        // TC01: Test that length of the normalized vector
        assertTrue(isZero(v.length() - 1),
                "ERROR: normalize() returns wrong result");

        // TC02: Test that length of the normalized vector
        assertTrue(isZero(v1.length() - 1),
                "ERROR: normalize() returns wrong result");
    }
}