package renderer;

import primitives.*;
import static primitives.Util.isZero;

/**
 * This class represents a camera in 3D space.
 */
public class Camera {
    private Point p0;
    private Vector vTo, vUp, vRight;
    private double height, width, distance;

    /**
     * Contractor for the camera object.
     *
     * @param p0  - Point to set the p0 of the camera.
     * @param vTo - Vector to set the vTo of the camera.
     * @param vUp - Vector to set the vUp of the camera.
     */
    public Camera(Point p0, Vector vTo, Vector vUp) {
        if (!isZero(vUp.dotProduct(vTo))) {
            throw new IllegalArgumentException("vUp and vTo must be vertical");
        }
        this.p0 = p0;
        this.vUp = vUp.normalize();
        this.vTo = vTo.normalize();
        this.vRight = vTo.crossProduct(vUp).normalize();
    }

    /**
     * The function returns the point of the camera
     *
     * @return point on the camera.
     */
    public Point getP0() {
        return p0;
    }

    /**
     * The function returns the vTo vector of the camera
     *
     * @return vTo of camera.
     */
    public Vector getVto() {
        return vTo;
    }

    /**
     * The function returns the vUp vector of the camera
     *
     * @return vUp of camera.
     */
    public Vector getVup() {
        return vUp;
    }

    /**
     * The function returns the vRight vector of the camera
     *
     * @return vRight of camera.
     */
    public Vector getVright() {
        return vRight;
    }

    /**
     * The function returns the height of the view plane.
     *
     * @return the height of the view plane.
     */
    public double getHeight() {
        return height;
    }

    /**
     * The function returns the width of the view plane.
     *
     * @return the width of the view plane.
     */
    public double getWidth() {
        return width;
    }

    /**
     * The function returns the distance between the camera and the view plane.
     *
     * @return the distance between the camera and the view plane.
     */
    public double getDistance() {
        return distance;
    }

    /**
     *  The function sets the size of the view plane.
     *
     * @param width  - The new width of the view plane
     * @param height - The new height of the view plane
     * @return the updated camera with the new updated values.
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     *  The function sets the distance between the camera and the view plane.
     *
     * @param distance - The new distance between the camera and the view plane
     * @return the updated camera with the new updated values.
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }


    /**

     Constructs a ray for a given pixel in the view plane.
     @param nX the number of pixels in the x-axis direction of the view plane
     @param nY the number of pixels in the y-axis direction of the view plane
     @param j the index of the pixel on the x-axis
     @param i the index of the pixel on the y-axis
     @return a Ray object for the given pixel
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        Point pc = p0.add(vTo.scale(distance));
        double rY = height / nY;
        double rX = width / nX;
        Point pIJ = pc;
        double jX = (j - (nX - 1d) / 2) * rX;
        if (!Util.isZero(jX)) {
            pIJ = pIJ.add(vRight.scale(jX));
        }
        double iY = -(i - (nY - 1d) / 2) * rY;
        if (!Util.isZero(iY)) {
            pIJ = pIJ.add(vUp.scale(iY));
        }
        Vector vIJ = pIJ.subtract(p0);
        return new Ray(p0, vIJ);
    }
}
