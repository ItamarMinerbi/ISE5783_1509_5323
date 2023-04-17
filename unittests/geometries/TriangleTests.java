package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
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
    void testgetNormal() {
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
}