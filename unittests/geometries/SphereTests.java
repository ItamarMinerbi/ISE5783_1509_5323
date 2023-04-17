package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test units for geometries.Sphere class
 */
class SphereTests {

    /**
     * Test method for {@link geometries.Sphere#getNormal(Point)}.
     */
    @Test
    void testgetNormal() {
        Sphere sphere = new Sphere(10,
                new Point(0, 0, 0));
        // ============ Equivalence Partitions Tests ==============

        // TC01: There is a simple single test here
        assertEquals(new Vector(1, 0, 0),
                sphere.getNormal(new Point(10, 0, 0)),
                "ERROR: Sphere.getNormal() returns wrong normal");
    }
}