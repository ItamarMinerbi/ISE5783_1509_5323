package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Cylinder class
 */
class CylinderTests {

    /**
     * Test method for {@link geometries.Cylinder#getNormal(Point)}.
     */
    @Test
    void testgetNormal() {
        Cylinder cylinder = new Cylinder(1,
                new Ray(new Point(0, 0, 1), new Vector(0, 0, 1)),
                1);
        // ============ Equivalence Partitions Tests ==============

        // TC01: Simple test
        assertEquals(new Vector(0, 1, 0),
                cylinder.getNormal(new Point(0, 1.2, -0.8)),
                "ERROR: The normal vector to the side is wrong");

        // TC02: Test if the point is on the bottom base.
        assertEquals(new Vector(0, 0, -1),
                cylinder.getNormal(new Point(0, 0.1, 1)),
                "ERROR: The normal vector to the the top base is wrong");

        // TC03: Test if the point is on the top base.
        assertEquals(new Vector(0, 0, 1),
                cylinder.getNormal(new Point(0, 0.1, 2)),
                "ERROR: The normal vector to the bottom base is wrong");


        // =============== Boundary Values Tests ==================

        // TC10: Test if the point is in the center of the bottom base.
        assertEquals(new Vector(0, 0, -1),
                cylinder.getNormal(new Point(0, 0, 1)),
                "ERROR: The normal vector to the bottom base is wrong");

        // TC11: Test if the point is in the center of the top base
        assertEquals(new Vector(0, 0, 1),
                cylinder.getNormal(new Point(0, 0, 2)),
                "ERROR: The normal vector to the the top base is wrong");
    }

}