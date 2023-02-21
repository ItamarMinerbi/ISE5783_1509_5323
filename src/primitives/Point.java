package primitives;

public class Point {
     Double3 xyz;

    public Point(double x, double y, double z)
    {
        this.xyz = new Double3(x,y,z);
    }

    Point(Double3 _xyz)
    {
        this.xyz = new Double3(_xyz.d1,_xyz.d2,_xyz.d3);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Point)
        {
            return ((Point)obj).xyz.equals(this.xyz);
        }
        return false;
    }

    @Override
    public String toString()
    {
        return this.xyz.toString();
    }

    public Point add(Vector vec)
    {
        return new Point(this.xyz.add(vec.xyz));
    }

    public Vector subtract(Point point)
    {
        return new Vector(this.xyz.subtract(point.xyz));
    }

    public double distanceSquared(Point other)
    {
        double d1 = this.xyz.d1 - other.xyz.d1;
        double d2 = this.xyz.d2 - other.xyz.d2;
        double d3 = this.xyz.d3 - other.xyz.d3;

        return d1 * d1 + d2 * d2 + d3 * d3;
    }

    public double distance(Point other)
    {
        return Math.sqrt(distanceSquared(other));
    }
}
