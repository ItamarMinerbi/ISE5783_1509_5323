package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test units for geometries.Tube class
 */
class TubeTests {

    /**
     * Test method for {@link geometries.Tube#getNormal(Point)}.
     */
    @Test
    void getNormal() {
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
}