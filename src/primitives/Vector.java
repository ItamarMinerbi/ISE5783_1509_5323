package primitives;

public class Vector extends Point {

    public Vector(double x, double y, double z)
    {
        super(x, y, z);
        if(new Double3(x,y,z).equals(Double3.ZERO))
        {
            throw new IllegalArgumentException("a vector cannot be zero");
        }
    }

    Vector(Double3 xyz)
    {
        super(xyz);
        if(xyz.equals(Double3.ZERO))
        {
            throw new IllegalArgumentException("a vector cannot be zero");
        }
    }

    public Vector add(Vector vec)
    {
        return new Vector(this.xyz.add(vec.xyz));
    }

    public Vector scale(double scalar)
    {
        return new Vector(this.xyz.scale(scalar));
    }

    public double dotProduct(Vector vec)
    {
        return (this.xyz.d1 * vec.xyz.d1) +
                (this.xyz.d2 * vec.xyz.d2) +
                (this.xyz.d3 * vec.xyz.d3);
    }

    public Vector crossProduct(Vector vec)
    {
        return new Vector((this.xyz.d2 * vec.xyz.d3) - (this.xyz.d3 * vec.xyz.d2),
                (this.xyz.d3 * vec.xyz.d1) - (this.xyz.d1 * vec.xyz.d3),
                (this.xyz.d1 * vec.xyz.d2) - (this.xyz.d2 * vec.xyz.d1));
    }

    public double lengthSquared()
    {
        return dotProduct(this);
    }

    public double length()
    {
        return Math.sqrt(lengthSquared());
    }

    public Vector normalize()
    {
       double len = length();
       if (len == 0)
       {
           throw new IllegalArgumentException("Vector 0 cannot be normalized.");
       }
       return scale(1 / len);
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj) return true;
        if(getClass() != obj.getClass()) return false;

        return ((Vector) obj).xyz.equals(this.xyz);
    }

    @Override
    public String toString()
    {
        return "-> " + this.xyz.toString();
    }
}
