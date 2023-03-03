package primitives;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Unit tests for primitives.Point class
 */
class PointTests {

    /**
     * Test method for {@link primitives.Point#add(Vector)}.
     */
    @Test
    void add() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: adding a vector to a point
        Point point1 = new Point(1, 2, 3);
        Vector vector1 = new Vector(6, 5, 4);
        assertEquals(new Point(7, 7, 7),
                point1.add(vector1),
                "Point add does not work");

        // TC02: adding a vector of negative and doubles to a point
        Point point2 = new Point(1.9, -2.45, 3.994);
        Vector vector2 = new Vector(-6.784, 5.2, -4.1);
        assertEquals(new Point(-4.884, 2.75, -0.106),
                point2.add(vector2),
                "Point add does not work");
    }

    /**
     * Test method for {@link primitives.Point#subtract(Point)}.
     */
    @Test
    void subtract() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: subtract 2 regular points
        Point p1 = new Point(4, 5, 6);
        Point p2 = new Point(1, 2, 3);
        assertEquals(new Vector(3, 3, 3),
                p1.subtract(p2),
                "Point subtract does not work");

        // TC02: subtract the point from itself
        assertThrows(IllegalArgumentException.class,
                () -> p1.subtract(p1),
                "Should throw an exception because it creates vector 0");
    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(Point)}.
     */
    @Test
    void distanceSquared() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: distance squared between two different points
        Point point1 = new Point(-3.6, 100.21, -1.5);
        Point point2 = new Point(-74.64, 1.96, -54.13);
        assertEquals(17469.661,
                point1.distanceSquared(point2),
                0.00000001,
                "Distance squared between two different points is not correct");

        // TC02: distance squared between same points
        assertTrue(isZero(point1.distanceSquared(point1)),
                "Distance squared between same points is not 0");
    }

    /**
     * Test method for {@link primitives.Point#distance(Point)}.
     */
    @Test
    void distance() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: distance between two different points
        Point point1 = new Point(-3.6, 100.21, -1.5);
        Point point2 = new Point(-74.64, 1.96, -54.13);
        assertEquals(Math.sqrt(17469.661),
                point1.distance(point2),
                0.00000001,
                "Distance between two different points is not correct");

        // TC02: distance between same points
        assertTrue(isZero(point1.distance(point1)),
                "Distance between same points is not 0");
    }
}