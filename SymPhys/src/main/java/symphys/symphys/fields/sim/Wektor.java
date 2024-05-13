package symphys.symphys.fields.sim;

import static java.lang.Math.sqrt;

public class Wektor {
    private double x=0, y=0;
    Wektor() {}
    Wektor(double x, double y) {
        this.x = x;
        this.y = y;
    }
    Wektor add(Wektor w) {
        return new Wektor(x+w.x, y+w.y);
    }
    Wektor sub(Wektor w) {
        return new Wektor(x-w.x, y-w.y);
    }
    Wektor mul(double a) {
        return new Wektor(x*a, y*a);
    }

    Wektor normalize() {
        if ((x==0)&&(y==0)) throw new RuntimeException("Attempting to normalize a null vector.");
        return mul(1/length());
    }

    double length() {
        return sqrt(x*x+y*y);
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    Wektor castX() {
        return new Wektor(x, 0);
    }

    Wektor castY() {
        return new Wektor(0, y);
    }

    static double dist2(Wektor a, Wektor b) {
        return (a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y);
    }

    static double dist(Wektor a, Wektor b) {
        return sqrt(dist2(a, b));
    }

    public String toString() {
        return "("+x+", "+y+")";
    }
}
