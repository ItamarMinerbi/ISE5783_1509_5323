package  primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void add() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: adding a vector to a point
        Point point1 = new Point(1,2,3);
        Vector vector1 = new Vector(6,5,4);

        assertEquals(new Point(7,7,7), point1.add(vector1), "point add does not work.");

        // TC02: adding a vector of negative and doubles to a point
        Point point2 = new Point(1.9,-2.45,3.994);
        Vector vector2 = new Vector(-6.784,5.2,-4.1);

        assertEquals(new Point(-4.884,2.75,-0.106), point2.add(vector2), "point add does not work.");
    }

    @Test
    void subtract() {
    }

    @Test
    void distanceSquared() {
    }

    @Test
    void distance() {
    }

    @Test
    void testEquals() {
    }
}