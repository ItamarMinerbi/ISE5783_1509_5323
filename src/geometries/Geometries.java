package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {
    private List<Intersectable> intersectables;

    /**

     Constructs a Geometries object with the given list of Intersectable objects.
     @param intersectables The list of Intersectable objects to include in the collection.
     */
    public Geometries(List<Intersectable> intersectabels) {
        intersectables = intersectabels;
    }

    public Geometries(Intersectable... intersectables) {
        this.intersectables =  new LinkedList<Intersectable>();
        this.intersectables.addAll(Arrays.stream(intersectables).toList());
    }

    public Geometries() {
        this.intersectables = new LinkedList<Intersectable>();
    }

    public void add(Intersectable... intersectables) {
        this.intersectables.addAll(Arrays.stream(intersectables).toList());
    }

    /**

     Finds all intersection points between the given Ray and the collection of Intersectable objects.
     @param ray The Ray object to find intersection points with.
     @return A List of Point objects representing the intersection points, or null if there are no intersection points.
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> result = null; // starts at null - if no intersection points .
        for (Intersectable geometry : intersectables) {
            List<Point> intersections = geometry.findIntersections(ray);
            if (intersections != null) {
                if (result == null)
                    result = new LinkedList<Point>();
                result.addAll(intersections);
            }
        }
        return result;
    }

}

