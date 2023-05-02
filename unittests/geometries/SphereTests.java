package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test units for geometries.Sphere class
 */
class SphereTests {

    /**
     * Test method for {@link geometries.Sphere#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        Sphere sphere = new Sphere(10,
                new Point(0, 0, 0));
        // ============ Equivalence Partitions Tests ==============

        // TC01: There is a simple single test here
        assertEquals(new Vector(1, 0, 0),
                sphere.getNormal(new Point(10, 0, 0)),
                "ERROR: Sphere.getNormal() returns wrong normal");
    }

    @Test
    void testFindIntersections() {
        Sphere sphere = new Sphere(1d, new Point(1, 0, 0));
        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),
                "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result = sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(3, 1, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));

        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        Ray ray = new Ray(new Point(1.5, 0, 0), new Vector(1, 0, 0));
        List<Point> expRes = List.of(new Point(2, 0, 0));
        List<Point> res = sphere.findIntersections(ray);

        assertEquals(1, res.size(), "Ray from inside sphere EP doesn't work.");

        assertEquals(res, expRes, "Ray from inside sphere EP doesn't work.");
        // TC04: Ray starts after the sphere (0 points)
        ray = new Ray(new Point(3, 0, 0), new Vector(1, 0, 0));

        assertNull(sphere.findIntersections(ray), "Ray in opposite dir of sphere EP doesn't work.");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        ray = new Ray(new Point(2, 0, 0), new Vector(-1, 0, 1));
        expRes = List.of(new Point(1, 0, 1));
        res = sphere.findIntersections(ray);

        assertEquals(1, res.size(), "Ray from the sphere inwards BVA doesn't work.");

        assertEquals(expRes, res, "Ray from the sphere inwards BVA doesn't work.");

        // TC12: Ray starts at sphere and goes outside (0 points)
        ray = new Ray(new Point(0, 0, 0), new Vector(-1, 0, -1));
        assertNull(sphere.findIntersections(ray), "Ray from the sphere outwards BVA doesn't work.");

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        ray = new Ray(new Point(-1, 0, 0), new Vector(5, 0, 0));
        expRes = List.of(new Point(2, 0, 0), new Point(0, 0, 0));
        res = sphere.findIntersections(ray);

        assertEquals(2, res.size(), "Ray through center 2 points BVA doesn't work.");

        assertEquals(expRes, res, "Ray through center 2 points BVA doesn't work.");
        // TC14: Ray starts at sphere and goes inside (1 points)
        ray = new Ray(new Point(2, 0, 0), new Vector(-4, 0, 0));
        expRes = List.of(new Point(0, 0, 0));
        res = sphere.findIntersections(ray);

        assertEquals(1, res.size(), "Ray on sphere through center inwards BVA doesn't work.");

        assertEquals(expRes, res, "Ray on sphere through center inwards BVA doesn't work.");

        // TC15: Ray starts inside (1 points)
        ray = new Ray(new Point(1.5, 0, 0), new Vector(1, 0, 0));
        expRes = List.of(new Point(2, 0, 0));
        res = sphere.findIntersections(ray);

        assertEquals(1, res.size(), "Ray in sphere through center BVA doesn't work.");

        assertEquals(expRes, res, "Ray in sphere through center BVA doesn't work.");
        // TC16: Ray starts at the center (1 points)
        ray = new Ray(new Point(1, 0, 0), new Vector(2, 0, 0));
        expRes = List.of(new Point(2, 0, 0));
        res = sphere.findIntersections(ray);

        assertEquals(1, res.size(), "Ray from center BVA doesn't work.");

        assertEquals(expRes, res, "Ray from center BVA doesn't work.");
        // TC17: Ray starts at sphere and goes outside (0 points)
        ray = new Ray(new Point(0, 0, 0), new Vector(-1, 0, 0));
        res = sphere.findIntersections(ray);

        assertNull(res, "Ray on sphere through center outwards BVA doesn't work.");

        // TC18: Ray starts after sphere (0 points)
        ray = new Ray(new Point(2, 0, 0), new Vector(1, 0, 0));
        res = sphere.findIntersections(ray);

        assertNull(res, "Ray out of sphere through center BVA doesn't work.");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        ray = new Ray(new Point(-1, 0, 1), new Vector(1, 0, 0));

        assertNull(sphere.findIntersections(ray), "Ray tangent to the sphere BVA doesn't work.");

        // TC20: Ray starts at the tangent point
        ray = new Ray(new Point(1, 0, 1), new Vector(-1, 0, 0));

        assertNull(sphere.findIntersections(ray), "Ray tangent to the sphere BVA doesn't work.");

        // TC21: Ray starts after the tangent point
        ray = new Ray(new Point(0, 0, 1), new Vector(-1, 0, 0));

        assertNull(sphere.findIntersections(ray), "Ray tangent to the sphere BVA doesn't work.");
        // **** Group: Special cases
        // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's
        // center line
        ray = new Ray(new Point(2, 0, 0), new Vector(0, 0, 1));
        assertNull(sphere.findIntersections(ray), "Ray outside orthogonal to sphere center line BVA doesn't work.");

    }
}