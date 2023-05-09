import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import primitives.*;
import renderer.*;
import geometries.*;
import org.junit.jupiter.api.Test;

class IntegrationTests {

    static Vector vTo = new Vector(0, 0, -1);
    static Vector vUp = new Vector(0, 1, 0);
    Camera camera;

    /**
     * Help function, set camera
     *
     * @param p0 is the camera location
     */
    void setCamera(Point p0) {
        camera = new Camera(p0, vTo, vUp).setVPDistance(1).setVPSize(3, 3);
    }

    /**
     * Help function, finds the amount of intersection points
     *
     * @param geometry the geometry
     * @param nX
     * @param nY
     * @return the amount of intersection points
     */
    int countIntersections(Intersectable geometry, int nX, int nY) {
        List<Point> l1;
        int size = 0;
        for (int i = 0; i < nX; ++i) {
            for (int j = 0; j < nY; ++j) {
                l1 = geometry.findIntersections(camera.constructRay(nX, nY, j, i));
                if (l1 != null) {
                    size += l1.size();
                }
            }
        }
        return size;
    }

    /**
     * Integration test for the following methods:
     *
     * {@link Camera#constructRay(int, int, int, int)}
     * {@link geometries.Sphere#findIntersections(primitives.Ray)}
     *
     */
    @Test
    void testCameraSphereIntersections() {
        camera = new Camera(new Point(0, 0, 0),
                new Vector(0, 0, -1),
                new Vector(0, 1, 0)).setVPDistance(1).setVPSize(3,
                3);
        // TC01: Simple test(2)
        setCamera(new Point(0, 0, 0));
        assertEquals(2,
                countIntersections(new Sphere(1, new Point(0, 0, -3)), 3, 3),
                "Simple test(2) failed");

        // TC02: Test when there are intersection points in front of the plane(18)
        setCamera(new Point(0, 0, 0.5));
        assertEquals(18,
                countIntersections(new Sphere(2.5, new Point(0, 0, -2.5)), 3, 3),
                "Test when their are intersection points in front of the plane(18) failed");

        // TC03: Test when there are intersection points in front of the plane(10)
        assertEquals(10,
                countIntersections(new Sphere( 2, new Point(0, 0, -2)), 3, 3),
                "Test when their are intersection points in front of the plane(10) failed");

        // TC04: Test when the camera is in the body(9)
        assertEquals(9,
                countIntersections(new Sphere( 4, new Point(0, 0, -1)), 3, 3),
                "Test when the camera is in the body(9) failed");

        // TC06: Test when the body is behind the camera(0)
        assertEquals(0,
                countIntersections(new Sphere( 0.5, new Point(0, 0, 1)), 3, 3),
                "Test when the the body is behind the camera(0) failed");
    }

    /**
     * Test method for {@link Camera#constructRay(int, int, int, int)}.
     * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
     */
    @Test
    void testCameraPlaneIntersections() {
        setCamera(new Point(0, 0, -0.5));
        Point p0 = new Point(0, 0, -5);

        // TC01: Simple test(9)
        assertEquals(9,
                countIntersections(new Plane(p0, new Vector(1, 0, 2)), 3, 3),
                "Simple test(9) failed");

        // TC02: Test when the plane is parallel to the view plane(9)
        assertEquals(9,
                countIntersections(new Plane(p0, new Vector(0, 0, -1)), 3, 3),
                "Test when the plane is parallel to the view plane(9) failed");

        // TC03: Test when there are 6 intersection points(6)
        assertEquals(6,
                countIntersections(new Plane(p0, new Vector(1, 0, -1)), 3, 3),
                "Tilted plane, 6 intersection points test not working");
    }

    /**
     * Test method for {@link Camera#constructRay(int, int, int, int)}.
     * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
     */
    @Test
    void testCameraTriangleIntersections() {
        setCamera(new Point(0, 0, 0.5));

        // TC01: One intersection point test
        assertEquals(1,
                countIntersections(new Triangle(new Point(0, 1, -2),
                        new Point(-1, -1, -2), new Point(1, -1, -2)), 3, 3),
                "One intersection point test failed");

        // TC02: Simple test(2)
        assertEquals(2,
                countIntersections(new Triangle(new Point(0, 20, -2),
                        new Point(1, -1, -2), new Point(-1, -1, -2)), 3, 3),
                "Simple test(2) failed");
    }
}
