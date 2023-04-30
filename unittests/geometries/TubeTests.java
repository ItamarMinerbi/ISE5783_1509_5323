package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import java.util.List;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test units for geometries.Tube class
 */
class TubeTests {

    /**
     * Test method for {@link geometries.Tube#getNormal(Point)}.
     */
    @Test
    void testgetNormal() {
        Tube tube = new Tube(1,
                new Ray(new Point(0, 0, 0), new Vector(1, 0, 0)));
        Point p1 = new Point(5, 1, 0);
        Point p2 = new Point(0, 0, 1);
        // ============ Equivalence Partitions Tests ==============

        // TC01: Simple test
        assertEquals(new Vector(0, 1, 0),
                tube.getNormal(p1),
                "ERROR: Tube.getNormal() returns wrong normal");


        // =============== Boundary Values Tests ==================

        // TC10: Test for vertical point
        assertEquals(new Vector(0, 0, 1),
                tube.getNormal(p2),
                "ERROR: Tube.getNormal() returns wrong normal for vertical point");
    }

    /*@Test
    void testFindIntersections() {
        Vector v1 = new Vector(1, 0, 0);
        Point p1 = new Point(-0.8660254037844382, 0.5, 3.1339745962155616);
        Point p2 = new Point(0.8660254037844382, 0.5, 4.866025403784437);
        Point p3 = new Point(1, 0, 2);
        Point p4 = new Point(1, 0, 1);
        Tube tube = new Tube(1d, new Ray(new Point(0, 0, 1), new Vector(0, 0, 1)));
        // =============== Equivalence Partitions Tests ==================

        // TC01: The ray crosses the tube in 2 points
        List<Point> intersections = tube.findIntersections(new Ray(new Point(-2, 0.5, 2), new Vector(1, 0, 1)));
        // If null
        assertNotNull(intersections, "Returns null");
        // Wrong amount
        assertEquals(2, intersections.size(), "Should give 2 points");
        // Wrong points
        assertEquals(p1, intersections.get(0),"Wrong points");
        assertEquals(p2, intersections.get(1), "Wrong points");

        // TC02: The ray starts on the tube and crosses it (1 intersection point)
        intersections = tube.findIntersections(new Ray(p1, new Vector(1, 0, 1)));
        // If null
        assertNotNull(intersections, "Returns null");
        // Wrong amount
        assertEquals(1, intersections.size(), "Should give only one point");
        // Wrong point
        assertEquals(p2, intersections.get(0), "Wrong point");

        // TC04: The ray starts in the tube (1 intersection point)
        intersections = tube.findIntersections(new Ray(new Point(0, 0.5, 2), new Vector(1, 0, 1)));
        // If null
        assertNotNull(intersections, "Returns null");
        // Wrong amount
        assertEquals(1, intersections.size(), "Should give only one point");
        // Wrong point
        assertEquals(new Point(0.8660254037844382, 0.5, 2.866025403784437), intersections.get(0), "Wrong point");

        // TC03: No intersection points
        assertNull(tube.findIntersections(new Ray(new Point(3, 3, 3), new Vector(1, 0, 1))), "Should be null");

        // TC05: The ray starts on the tube (no intersection points)
        assertNull(tube.findIntersections(new Ray(new Point(1, 0.5, 2), new Vector(1, 0, 1))), "Should be null");

        // TC06: The opposite of the ray's direction is to the tube (no intersection
        // points)
        assertNull(tube.findIntersections(new Ray(new Point(2, 0.5, 2), new Vector(1, 0, 1))), "Should be null");

        // =============== Boundary Values Tests ==================

        // When the ray is perpendicular to the tube and goes through it's center
        // TC11: The ray crosses the tube (2 intersection points)
        intersections = tube.findIntersections(new Ray(new Point(-2, 0, 2), v1));
        // If null
        assertNotNull(intersections, "Returns null");
        // Wrong amount
        assertEquals(2, intersections.size(), "Should give 2 points");
        // Wrong points
        if (intersections.get(0).getX() > intersections.get(1).getX())
            intersections = List.of(intersections.get(1), intersections.get(0));
        assertEquals(List.of(new Point(-1, 0, 2), p3), intersections, "Wrong points");

        // TC12: The ray starts on the tube and crosses it (1 intersection point)
        intersections = tube.findIntersections(new Ray(new Point(-1, 0, 2), v1));
        // If null
        assertNotNull(intersections, "Returns null");
        // Wrong amount
        assertEquals(1, intersections.size(), "Should give only one point");
        // Wrong point
        assertEquals(p3, intersections.get(0), "Wrong point");

        // TC13: The ray starts before tube's axis ray (1 intersection point)
        intersections = tube.findIntersections(new Ray(new Point(-0.5, 0, 2), v1));
        // If null
        assertNotNull(intersections, "Returns null");
        // Wrong amount
        assertEquals(1, intersections.size(), "Should give only one point");
        // Wrong point
        assertEquals(p3, intersections.get(0), "Wrong point");

        // TC14: The ray starts on the tube's axis ray (1 intersection point)
        intersections = tube.findIntersections(new Ray(new Point(0, 0, 2), v1));
        // If null
        assertNotNull(intersections, "Returns null");
        // Wrong amount
        assertEquals(1, intersections.size(), "Should give only one point");
        // Wrong points
        assertEquals(p3, intersections.get(0), "Wrong point");

        // TC15: The ray starts after the tube's axis ray (1 intersection point)
        intersections = tube.findIntersections(new Ray(new Point(0.5, 0, 2), v1));
        // If null
        assertNotNull(intersections, "Returns null");
        // Wrong amount
        assertEquals(1, intersections.size(), "Should give only one point");
        // Wrong point
        assertEquals(p3, intersections.get(0), "Wrong point");

        // TC16: The ray starts after the tube (no intersection points)
        assertNull(tube.findIntersections(new Ray(new Point(2, 0, 2), v1)), "Should be null");

        // When the ray is tangent to the tube (no intersection points)
        // TC21: The ray starts before the tube
        assertNull(tube.findIntersections(new Ray(new Point(-2, 1, 2), v1)), "Should be null");

        // TC22: The ray starts on the tube
        assertNull(tube.findIntersections(new Ray(new Point(1, 1, 2), v1)), "Should be null");

        // TC23: The ray starts after the tube
        assertNull(tube.findIntersections(new Ray(new Point(2, 1, 2), v1)), "Should be null");

        // When the ray is perpendicular to the tube (but doesn't go through
        // it's center)
        // TC31: The ray crosses the tube (2 intersection points)
        intersections = tube.findIntersections(new Ray(new Point(-2, 0.5, 2), v1));
        // If null
        assertNotNull(intersections, "Returns null");
        // Wrong amount
        assertEquals(2, intersections.size(), "Should give 2 points");
        // Wrong points
        if (intersections.get(0).getX() > intersections.get(1).getX())
            intersections = List.of(intersections.get(1), intersections.get(0));
        assertEquals(List.of(new Point(-0.8660254037844382, 0.5, 2), new Point(0.8660254037844382, 0.5, 2)),
                intersections, "Wrong points");

        // TC32: No intersections
        assertNull(tube.findIntersections(new Ray(new Point(-2, 3, 2), v1)), "Should be null");

        // TC33: The ray starts on the tube and crosses it (1 intersection point)
        intersections = tube.findIntersections(new Ray(p1, v1));
        // If null
        assertNotNull(intersections, "Returns null");
        // Wrong amount
        assertEquals(1, intersections.size(), "Should give only one point");
        // Wrong point
        assertEquals(*//* p1? *//*new Point(0.8660254037844382, 0.5, 3.1339745962155616), intersections.get(0),
                "Wrong point");

        // TC34: The ray starts on the tube but doesn't crosses it (0 intersection
        // points)
        assertNull(tube.findIntersections(new Ray(new Point(1, 0.5, 2), v1)), "Should be null");

        // TC35: The ray starts in the tube (1 intersection point)
        intersections = tube.findIntersections(new Ray(new Point(0, 0.5, 2), v1));
        // If null
        assertNotNull(intersections, "Returns null");
        // Wrong amount
        assertEquals(1, intersections.size(), "Should give only one point");
        // Wrong point
        assertEquals(new Point(0.8660254037844382, 0.5, 2), intersections.get(0), "Wrong point");

        // TC36: The ray starts after the tube (0 intersection points)
        assertNull(tube.findIntersections(new Ray(new Point(2, 0.5, 2), v1)), "Should be null");

        // When the ray's p0 to the tube's p0 is perpendicular to the tube's axis
        // + The ray is perpendicular to the tube's axis
        // TC401: The ray is tangent (0 intersection points)
        assertNull(tube.findIntersections(new Ray(new Point(0, 1, 1), v1)), "Should be null");

        // TC402: The ray starts on the tube and directed away from the center point (0
        // intersection points)
        assertNull(tube.findIntersections(new Ray(p4, v1)), "Should be null");

        // TC403: The ray starts on the tube and directed outside (0 intersection
        // points)
        assertNull(tube.findIntersections(new Ray(p4, new Vector(1, 1, 0))), "Should be null");

        // TC404: The ray starts on the tube and directed inside (1 intersection point)
        intersections = tube.findIntersections(new Ray(p4, new Vector(-1, 1, 0)));
        // If null
        assertNotNull(intersections, "Returns null");
        // Wrong amount
        assertEquals(1, intersections.size(), "Should give only one point");
        // Wrong point
        assertEquals(new Point(0, 1, 1), intersections.get(0), "Wrong point");

        // TC405: The ray starts on the tube and directed towards the center (1
        // intersection point)
        assertNull(tube.findIntersections(new Ray(p4, v1)), "Should be null");

        // TC406: The ray starts in the tube and directed away from the center (1
        // intersection point)
        intersections = tube.findIntersections(new Ray(new Point(0.5, 0, 1), new Vector(-1, 0, 0)));
        // If null
        assertNotNull(intersections, "Returns null");
        // Wrong amount
        assertEquals(1, intersections.size(), "Should give only one point");
        // Wrong point
        assertEquals(new Point(-1, 0, 1), intersections.get(0), "Wrong point");

        // TC407: The ray starts in the tube, before the center (1 intersection point)
        intersections = tube.findIntersections(new Ray(new Point(-0.5, 0.5, 1), v1));
        // If null
        assertNotNull(intersections, "Returns null");
        // Wrong amount
        assertEquals(1, intersections.size(), "Should give only one point");
        // Wrong point
        assertEquals(new Point(0.8660254037844386, 0.5, 1), intersections.get(0), "Wrong point");

        // TC408: The ray starts in the tube, after the center (1 intersection point)
        intersections = tube.findIntersections(new Ray(new Point(0.5, 0.5, 1), v1));
        // If null
        assertNotNull(intersections, "Returns null");
        // Wrong amount
        assertEquals(1, intersections.size(), "Should give only one point");
        // Wrong point
        assertEquals(new Point(0.8660254037844386, 0.5, 1), intersections.get(0), "Wrong point");

        // TC409: No intersections
        assertNull(tube.findIntersections(new Ray(new Point(-2, 2, 1), new Vector(0, 1, 1))), "Should be null");

        // TC4010: The ray starts before the tube (2 intersection points)
        intersections = tube.findIntersections(new Ray(new Point(-2, 0.5, 1), v1));
        // If null
        assertNotNull(intersections, "Returns null");
        // Wrong amount
        assertEquals(2, intersections.size(), "Should give 2 points");
        // Wrong points
        if (intersections.get(0).getX() > intersections.get(1).getX())
            intersections = List.of(intersections.get(1), intersections.get(0));
        assertEquals(List.of(new Point(-0.8660254037844386, 0.5, 1), new Point(0.8660254037844386, 0.5, 1)),
                intersections, "Wrong point");

        // TC4011: The ray starts after the tube (0 intersection points)
        assertNull(tube.findIntersections(new Ray(new Point(2, 0.5, 1), v1)), "Should be null");

        // TC4012: The ray starts before the tube and crosses it's center (2
        // intersection points)
        intersections = tube.findIntersections(new Ray(new Point(-2, 0, 1), v1));
        // If null
        assertNotNull(intersections, "Returns null");
        // Wrong amount
        assertEquals(2, intersections.size(), "Should give 2 points");
        // Wrong points
        if (intersections.get(0).getX() > intersections.get(1).getX())
            intersections = List.of(intersections.get(1), intersections.get(0));
        assertEquals(List.of(new Point(-1, 0, 1), new Point(1, 0, 1)), intersections, "Wrong point");

        // TC4013: The ray starts after the tube and "crosses" it's center (0
        // intersection points)
        assertNull(tube.findIntersections(new Ray(new Point(2, 0, 1), v1)), "Should be null");

        // + The ray is parallel to the tube
        // TC411: The ray is outside the tube
        assertNull(tube.findIntersections(new Ray(new Point(2, 0, 2), new Vector(0, 0, 1))), "Should be null");

        // TC412: The ray is on the tube
        assertNull(tube.findIntersections(new Ray(new Point(-1, 0, 2), new Vector(0, 0, 1))), "Should be null");

        // TC413: The ray is in the tube
        assertNull(tube.findIntersections(new Ray(new Point(-0.5, 0, 2), new Vector(0, 0, 1))), "Should be null");

        // TC414: The ray is on the tube's axis
        assertNull(tube.findIntersections(new Ray(new Point(0, 0, 2), new Vector(0, 0, 1))), "Should be null");

        // TC415: The ray is outside the tube, facing back
        assertNull(tube.findIntersections(new Ray(new Point(2, 0, 2), new Vector(0, 0, -1))), "Should be null");

        // TC416: The ray is on the tube, facing back
        assertNull(tube.findIntersections(new Ray(new Point(-1, 0, 2), new Vector(0, 0, -1))), "Should be null");

        // TC417: The ray is in the tube, facing back
        assertNull(tube.findIntersections(new Ray(new Point(-0.5, 0, 2), new Vector(0, 0, -1))), "Should be null");

        // TC418: The ray starts in the origin point and perpendicular to the tube
        intersections = tube.findIntersections(new Ray(new Point(0, 0, 1), v1));
        // If null
        assertNotNull(intersections, "Returns null");
        // Wrong amount
        assertEquals(1, intersections.size(), "Should give only one point");
        // Wrong point
        assertEquals(new Point(1, 0, 1), intersections.get(0), "Wrong point");

        // TC419: The ray starts in the origin point and neither perpendicular or
        // parallel to the tube
        intersections = tube.findIntersections(new Ray(new Point(0, 0, 1), new Vector(1, 0, 1)));
        // If null
        assertNotNull(intersections, "Returns null");
        // Wrong amount
        assertEquals(1, intersections.size(), "Should give only one point");
        // Wrong point
        assertEquals(new Point(1, 0, 2), intersections.get(0), "Wrong point");

        // TC4110: The ray starts outside, hits on circle (2 intersection points)
        intersections = tube.findIntersections(new Ray(new Point(-2, 0, 0), new Vector(1, 0, 1)));
        // If null
        assertNotNull(intersections, "Returns null");
        // Wrong amount
        assertEquals(2, intersections.size(), "Should give 2 points");
        // Wrong points
        if (intersections.get(0).getX() > intersections.get(1).getX())
            intersections = List.of(intersections.get(1), intersections.get(0));
        assertEquals(List.of(new Point(-1, 0, 1), new Point(1, 0, 3)), intersections, "Wrong point");

        // TC4111: The ray starts outside, hits origin point (2 intersection point2)
        intersections = tube.findIntersections(new Ray(new Point(-2, 0, -1), new Vector(1, 0, 1)));
        // If null
        assertNotNull(intersections, "Returns null");
        // Wrong amount
        assertEquals(2, intersections.size(), "Should give 2 points");
        // Wrong points
        if (intersections.get(0).getX() > intersections.get(1).getX())
            intersections = List.of(intersections.get(1), intersections.get(0));
        assertEquals(List.of(new Point(-1, 0, 0), new Point(1, 0, 2)), intersections, "Wrong point");
    }*/
}