package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * The Sphere class represents a geometric sphere object in 3D space.
 * It extends the RadialGeometry class and includes a center point.
 */
public class Sphere extends RadialGeometry {
    private final Point center;

    /**
     * Constructs a sphere object with the given radius and center point.
     *
     * @param radius The radius of the sphere.
     * @param center The center point of the sphere.
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    /**
     * Returns the center point of the sphere.
     *
     * @return The center point of the sphere.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Returns the normal vector to the surface of the sphere at the given point.
     *
     * @param point The point on the surface of the sphere.
     * @return the normal vector of the sphere at a specific point.
     */
    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }



    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        try {
            Vector vector = ray.getP0().subtract(center);
            double a = ray.getDir().dotProduct(ray.getDir());
            double b = vector.scale(2).dotProduct(ray.getDir());
            double c = vector.dotProduct(vector) - radius * radius;
            double discriminant = b * b - 4 * a * c;

            if (isZero(discriminant)) {
                return null; // ray is tangent to the sphere
            }

            if (discriminant < 0) {
                return null; // no intersection points
            }

            double t1 = alignZero((-b + Math.sqrt(discriminant)) / (2 * a));
            double t2 = alignZero((-b - Math.sqrt(discriminant)) / (2 * a));
            if(t1<0){
                if(t2<0){
                    return null;
                }else {
                    return List.of(new GeoPoint(this, ray.getPoint(t2)));
                }
            }else if(isZero(t1)){
                if(t2<0){
                    return null;
                }else {
                    return List.of(new GeoPoint(this, ray.getPoint(t2)));
                }
            }else{
                if(t2>0){
                    return List.of(new GeoPoint(this, ray.getPoint(t1)),new GeoPoint(this, ray.getPoint(t2)));
                }else {
                    return List.of(new GeoPoint(this, ray.getPoint(t1)));
                }
            }
        } catch (IllegalArgumentException e) {
            double a = ray.getDir().dotProduct(ray.getDir());
            double c = -radius * radius;
            double t1 = alignZero(-c / a);

            if (t1 > 0) {
                return List.of(new GeoPoint(this, ray.getPoint(t1))); // return the intersection point with real direction
            } else {
                return null; // no intersection points with real direction
            }
        }
    }
}
