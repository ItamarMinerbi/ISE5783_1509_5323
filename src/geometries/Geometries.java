package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable {
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



    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> result = null; // starts at null - if no intersection points .
        for (Intersectable geometry : intersectables) {
            List<GeoPoint> intersections = geometry.findGeoIntersectionsHelper(ray);
            if (intersections != null) {
                if (result == null)
                    result = new LinkedList<GeoPoint>();
                result.addAll(intersections);
            }
        }
        return result;
    }
}

