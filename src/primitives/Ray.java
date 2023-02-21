package primitives;

public class Ray {
    Point p0;
    Vector dir;

    public Ray(Point p0, Vector dir)
    {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Ray)
        {
            Ray other = (Ray)obj;
            return (p0.equals(other.p0) && dir.equals(other.dir));
        }
        return false;
    }
}
