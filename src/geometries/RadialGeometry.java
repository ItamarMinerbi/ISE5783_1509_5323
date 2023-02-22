package geometries;

public abstract class RadialGeometry implements Geometry {
    protected double radius;

    public RadialGeometry(double radius)
    {
        if(radius <= 0)
            throw new IllegalArgumentException("Radius must be positive");

        this.radius = radius;
    }

    public double getRadius()
    {
        return radius;
    }
}
