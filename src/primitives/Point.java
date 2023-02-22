package primitives;

public class Point {
    final Double3 xyz;

    public Point(double x, double y, double z)
    {
        this.xyz = new Double3(x, y, z);
    }

    Point(Double3 xyz)
    {
        this.xyz = new Double3(xyz.d1,xyz.d2,xyz.d3);
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
        double d1 = Util.alignZero(this.xyz.d1 - other.xyz.d1);
        double d2 = Util.alignZero(this.xyz.d2 - other.xyz.d2);
        double d3 = Util.alignZero(this.xyz.d3 - other.xyz.d3);

        // d^2 = x^2 + y^2 + z^2
        return d1 * d1 + d2 * d2 + d3 * d3;
    }

    public double distance(Point other)
    {
        // d = sqrt(x^2 + y^2 + z^2)
        return Util.alignZero(Math.sqrt(distanceSquared(other)));
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj) return true;
        if(getClass() != obj.getClass()) return false;

        return ((Point) obj).xyz.equals(this.xyz);
    }

    @Override
    public String toString()
    {
        return this.xyz.toString();
    }
}
