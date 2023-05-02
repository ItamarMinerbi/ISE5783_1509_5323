package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test units for geometries.Triangle class
 */
class TriangleTests {

    /**
     * Test method for {@link geometries.Triangle#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        Point p1 = new Point(1, 0, 0);
        Point p2 = new Point(0, 1, 0);
        Point p3 = new Point(0, 0, 0);

        Vector upVector = new Vector(0, 0, 1);
        Vector downVector = upVector.scale(-1);
        Triangle triangle = new Triangle(p1, p2, p3);
        // ============ Equivalence Partitions Tests ==============

        // TC01: There is a simple single test here
        Vector normal = triangle.getNormal(new Point(0.5, 0, 0));
        assertTrue(normal.equals(upVector) || normal.equals(downVector),
                "ERROR: Triangle.getNormal() returns wrong normal");
    }

    @Test
    void testFindIntersections() {
        Triangle tr = new Triangle(new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0));
        Vector v = new Vector(0, 0, 1);

        // ============ Equivalence Partitions Tests ==============

        // TC01: There is a simple single test here
        assertEquals(new Point(0.25, 0.25, 0), tr.findIntersections(new Ray(new Point(0.25, 0.25, -1), v)).get(0),
                "Bad Point to trinagle");// checks on the triangle

        // TC02: Test when the point is out of the triangle(against edge)
        assertNull(tr.findIntersections(new Ray(new Point(-1, 0.5, -1), v)), //
                "Test when the point is out of triangle (against edge) failed\n");

        // TC03: Test when the point is out of the triangle(against vertex)
        assertNull(tr.findIntersections(new Ray(new Point(-1, -1, -1), v)), //
                "Test when the point is out of triangle (against vertex) failed");

        // =============== Boundary Values Tests ==================

        // TC11: Test when the point is in the edge of the triangle
        assertNull(tr.findIntersections(new Ray(new Point(0, 0.5, 1), v)), //
                "Test when the point is in the edge of the triangle failed");

        // TC12: Test when the point is in the angle of the triangle
        assertNull(tr.findIntersections(new Ray(new Point(0, 0, 1), v)), //
                "Test when the point is in the angle of the triangle failed");

        // TC13: Test when the point is on edge's continuation
        assertNull(tr.findIntersections(new Ray(new Point(0, -1, 1), v)), //
                "Test when the point is on edge's continuation failed");

    }
}