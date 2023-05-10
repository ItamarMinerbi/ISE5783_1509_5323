package primitives;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTests {

    @Test
    void testFindClosestPoint() {
        Ray ray = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Test when the closest point is in the middle of the list

        assertEquals(new Point(1, 2, 0),
                ray.findClosestPoint(List.of(new Point(1, 1, 3), new Point(1, 2, 0), new Point(2, 2, 0))),
                "Test when the closest point is in the middle failed");

        // =============== Boundary Values Tests ==================

        // TC11: Test when the list is empty
        assertNull(ray.findClosestPoint(List.of()),
                "Test when the list is empty failed");

        // TC12: Test when the closest point is in the beginning of the list
        assertEquals(new Point(1, 2, 0),
                ray.findClosestPoint(List.of(new Point(1, 2, 0), new Point(1, 1, 3), new Point(2, 2, 0))),
                "Test when the closest point is in the beginning failed");

        // TC13: Test when the closest point is in the ending of the list
        assertEquals(new Point(1, 2, 0),
                ray.findClosestPoint(List.of(new Point(2, 2, 0), new Point(1, 1, 3), new Point(1, 2, 0))),
                "Test when the closest point is in the ending failed");
    }
}