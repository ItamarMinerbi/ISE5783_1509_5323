package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Plane class
 */
class PlaneTests {

    /**
     * Test method for {@link geometries.Plane#Plane(Point, Point, Point)}.
     */
    @Test
    void testPlane() {
        // =============== Boundary Values Tests ==================

        // TC01: Test when two same points are given
        Point p1 = new Point(1, 0, 0);
        Point p2 = new Point(0, 1, 0);
        Point p3 = new Point(0, 1, 0);
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(p1, p2, p3),
                "ERROR: The plane constructor should throw an exception when 2 same points are given");

        // TC02: Test when the given points are on the same line
        Point p4 = new Point(1, 0, 0);
        Point p5 = new Point(2, 0, 0);
        Point p6 = new Point(3, 0, 0);
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(p4, p5, p6),
                "ERROR: The plane constructor should throw an exception when the given points are on the same line");
    }

    /**
     * Test method for {@link geometries.Plane#getNormal(Point)}.
     */
    @Test
    void testgetNormal() {
        Point p1 = new Point(0, 0, 1);
        Point p2 = new Point(0, 1, 0);
        Point p3 = new Point(1, 0, 0);

        Plane plane = new Plane(p1, p2, p3);
        // ============ Equivalence Partitions Tests ==============

        // TC01: There is a simple single test here
        Vector normal = new Vector(1, 1, 1).normalize();
        assertEquals(1,
                Math.abs(plane.getNormal(new Point(0, 1, 2))
                        .dotProduct(normal)),
                0.00001,
                "ERROR: Plane.getNormal() returns wrong normal");
    }
}