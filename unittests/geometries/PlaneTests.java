package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import java.util.List;
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
    void testGetNormal() {
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

    @Test
    void testFindIntersections() {
        Plane plane = new Plane(new Point(-0.5, -0.5, 0), new Point(1, 0, 0), new Point(0, 1, 0));
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray intersects the plane.
        Ray ray = new Ray(new Point(1, 1, 1), new Vector(-1, 0, -1));
        List<Point> expRes = List.of(new Point(0, 1, 0));
        List<Point> res = plane.findIntersections(ray);

        assertEquals(1, res.size(), "Ray intersects the plane EP doesn't work.");

        assertEquals(res, expRes, "Ray intersects the plane EP doesn't work.");

        // TC02: Ray does not intersects the plane.
        ray = new Ray(new Point(1, 1, 1), new Vector(1, 1, 1));

        assertNull(plane.findIntersections(ray), "Ray does not intersects the plane EP doesn't work.");

        // =============== Boundary Values Tests ==================
        // ****Ray is the plane parallel to the plane
        // TC11: Ray is parallel and included in the plane.
        ray = new Ray(new Point(0, 1, 0), new Vector(1, 0, 0));

        assertNull(plane.findIntersections(ray), "Ray is parallel and included in the plane BVA doesn't work.");

        // TC12: Ray is parallel and not included in the plane.
        ray = new Ray(new Point(0, 1, 1), new Vector(1, 0, 0));

        assertNull(plane.findIntersections(ray), "Ray is parallel and not included in the plane BVA doesn't work.");

        // ****Group: Ray is orthogonal to the plane
        // TC11: Ray is parallel and included in the plane.
        ray = new Ray(new Point(0, 1, 0), new Vector(1, 0, 0));

        assertNull(plane.findIntersections(ray), "Ray is parallel and included in the plane BVA doesn't work.");

        // TC12: Ray is parallel and not included in the plane.
        ray = new Ray(new Point(0, 1, 1), new Vector(1, 0, 0));

        assertNull(plane.findIntersections(ray), "Ray is parallel and not included in the plane BVA doesn't work.");
        // **** Group:Ray is orthogonal to the plane
        // TC13: Ray is orthogonal to the plane and before the plane.
        ray = new Ray(new Point(0, 1, 1), new Vector(0, 0, -1));
        expRes = List.of(new Point(0, 1, 0));
        res = plane.findIntersections(ray);

        assertEquals(1, res.size(), "Ray is orthogonal to the plane and before the plane BVA doesn't work.");

        assertEquals(res, expRes, "Ray is orthogonal to the plane and before the plane BVA doesn't work.");

        // TC14: Ray is orthogonal to the plane and on the plane.
        ray = new Ray(new Point(0, 2, 0), new Vector(0, 0, -1));

        assertNull(plane.findIntersections(ray), "Ray is orthogonal to the plane and in the plane BVA doesn't work.");

        // TC15: Ray is orthogonal to the plane and after the plane.
        ray = new Ray(new Point(0, 2, -1), new Vector(0, 0, -1));

        assertNull(plane.findIntersections(ray),
                "Ray is orthogonal to the plane and after the plane BVA doesn't work.");

        // **** Group:Ray is neither orthogonal nor parallel to and ) begins at the
        // plane
        // TC16: Ray is neither orthogonal nor parallel to and begins at the plane
        ray = new Ray(new Point(0, 0, 0), new Vector(0, 1, 1));

        assertNull(plane.findIntersections(ray),
                "Ray is neither orthogonal nor parallel to and  begins at the plane BVA doesn't work.");

        // TC17: Ray begins in the same point which appears as the plane's reference
        // point.
        ray = new Ray(new Point(-0.5, -0.5, 0), new Vector(1, 1, 1));

        assertNull(plane.findIntersections(ray),
                "Ray begins in the same point which appears as the plane's reference point BVA doesn't work.");

    }
}