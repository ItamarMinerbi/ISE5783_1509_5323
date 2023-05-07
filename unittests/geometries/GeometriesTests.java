package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTests {

    @Test
    void testFindIntersections() {
        Ray ray = new Ray(new Point(1, 0, 0), new Vector(-1, 0, 0));

        // ============ Equivalence Partitions Tests ==============
        // TC01: Some of the geometries intersect.
        Geometries geometries = new Geometries(
                new Sphere(1, new Point(0, 0.5, 0.5)), // 2 intersection points
                new Triangle(new Point(-0.5, -0.5, 0), new Point(0, 1, 0), new Point(1, 0, 0)),
                new Plane(new Point(-0.5, -0.5, 2), new Point(0, 1, 2), new Point(1, 0, 2)));

        assertEquals(2, geometries.findIntersections(ray).size(), "Some of the geometries intersect doesn't work.");

        // =============== Boundary Values Tests ==================
        // TC11: Empty geometries set.
        geometries = new Geometries(new LinkedList<Intersectable>());

        assertNull(geometries.findIntersections(ray), "Empty geometries set doesn't work.");

        // TC12: None of the geometries intersects.
        geometries = new Geometries(new Sphere(1, new Point(0, 0.5, 4)),
                new Triangle(new Point(-0.5, -0.5, -2), new Point(0, 1, -2), new Point(1, 0, -2)),
                new Plane(new Point(-0.5, -0.5, 2), new Point(0, 1, 2), new Point(1, 0, 2)));

        assertNull(geometries.findIntersections(ray), "None of the geometries intersects doesn't work.");

        // TC13: One geometry in the set intersects.
        geometries = new Geometries(new Sphere(1, new Point(0, 0.5, 0.5)),
                new Triangle(new Point(-0.5, -0.5, -4), new Point(0, 1, -4), new Point(1, 0, -4)),
                new Plane(new Point(-0.5, -0.5, 4), new Point(0, 1, 4), new Point(1, 0, 4)));

        assertEquals(2, geometries.findIntersections(ray).size(), "One geometry in the set intersects doesn't work.");

        // TC14: All geometry in the set intersects.
        geometries = new Geometries(new Sphere(1, new Point(0, 0.5, 0.5)), // 2 intersection points
                new Triangle(new Point(0.5, 0.5, -1), new Point(-1, -1, -1), new Point(-0.5, 0.5, 1)),
                new Plane(new Point(0.5, 0.5, -1), new Point(-1, -1, -1), new Point(-0.5, 0.5, 1)));

        assertEquals(4, geometries.findIntersections(ray).size(), "All geometry in the set intersects doesn't work.");
    }
}