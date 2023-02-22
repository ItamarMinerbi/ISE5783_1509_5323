package primitives;

public class Ray {
    final Point p0;
    final Vector dir;

    public Ray(Point p0, Vector dir)
    {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    public Point getP0()
    {
        return p0;
    }

    public Vector getDir()
    {
        return dir;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj) return true;
        if(getClass() != obj.getClass()) return false;

        Ray otherRay = (Ray) obj;
        return otherRay.p0.equals(this.p0) &&
               otherRay.dir.equals(this.dir);
    }

    @Override
    public String toString()
    {
        return "[" + p0 + ", " + dir + "]";
    }
}
