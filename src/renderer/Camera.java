package renderer;

import primitives.*;

import java.util.List;
import java.util.MissingResourceException;
import java.util.stream.IntStream;

import static primitives.Util.isZero;

/**
 * This class represents a camera in 3D space.
 */
public class Camera {
    private Point p0;
    private Vector vTo, vUp, vRight;
    private double height, width, distance;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;


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
     *  The function sets the imageWriter of the camera.
     *
     * @param imageWriter - The new imageWriter
     * @return the updated camera with the new updated values.
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     *  The function sets the rayTracer of the camera.
     *
     * @param rayTracer - The new rayTracer
     * @return the updated camera with the new updated values.
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }


    /**
     * The function creates a ray from the camera to a pixel and finds the ray's color.
     *
     * @param nX number of pixels on X axis in the view plane
     * @param nY number of pixels on Y axis in the view plane
     * @param j X coordinate of the pixel
     * @param i Y coordinate of the pixel
     * @return The color of the ray from the camera to the pixel
     */
    private Color castRay(int nX, int nY, int j, int i) {
        Ray ray = constructRay(nX, nY, j, i);
        return rayTracer.traceRay(ray);
    }

    /**
     * Renders the image pixel by pixel into the imageWriter
     */
    public Camera renderImage() {
        if (imageWriter == null)
            throw new MissingResourceException("Missing image writer object!", "ImageWriter", "");

        if (rayTracer == null)
            throw new MissingResourceException("Missing tracer object!", "RayTracerBase", "");

        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                Color color = castRay(nX, nY, j, i);
                imageWriter.writePixel(j, i, color);
            }
        }
        return this;
    }

    /**
     * Print a grid on the image
     *
     * @param interval The width & height of a grid cell in pixels
     * @param color The color of the grid
     */
    public void printGrid(int interval, Color color) {
        if (imageWriter == null)
            throw new MissingResourceException("Missing image writer object!", "ImageWriter", "");

        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                if (i % interval == 0 || j % interval == 0)
                    imageWriter.writePixel(i, j, color);
            }
        }
    }

    /**
     * Change the actual image file according to the imageWriter object
     */
    public void writeToImage() {
        if (imageWriter == null)
            throw new MissingResourceException("Missing image writer object!", "ImageWriter", "");

        imageWriter.writeToImage();
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
