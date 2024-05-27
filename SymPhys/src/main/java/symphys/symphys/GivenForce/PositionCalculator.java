package symphys.symphys.GivenForce;

import java.awt.geom.Point2D;


public class PositionCalculator {

    private final double mass;
    private final Point2D.Double v0;
    private final Point2D.Double x0;
    private Point2D.Double vi;
    private Point2D.Double xi;

    TriFunction<Point2D.Double, Point2D.Double, Double, Point2D.Double> force;
    public PositionCalculator(TriFunction<Point2D.Double, Point2D.Double, Double, Point2D.Double> force, Point2D.Double v0, Point2D.Double x0, double mass){
        this.force = force;
        this.mass = mass;
        this.v0 = v0;
        this.x0 = x0;
    }
    private long first = 0;
    private static final double Dt = 0.001;
    private long last;
    public Point2D.Double getForce(Point2D.Double x, Point2D.Double v, Double t){
        return force.apply(x, v, t);
    }
    private Point2D.Double calculateVelocity(long t){
        Point2D.Double F = force.apply(xi, vi, (double)t/1000 - Dt/2);
        Point2D.Double a = new Point2D.Double(F.getX()/mass, F.getY()/mass);
        return new Point2D.Double(vi.getX() + a.getX() * Dt, vi.getY() + a.getY() * Dt);
    }
    public Point2D.Double getNextPosition(long l) {
        long time = l / 1000_000;
        if (first == 0) {
            first = time;
            last = time;
            xi = x0;
            vi = v0;
//            Fi = force.apply(x0, v0, 0.);
            return x0;
        }
        for (int i = 0; i < time - last; i++){
            Point2D.Double v = calculateVelocity(time-first);
            xi = new Point2D.Double(xi.getX() + (v.getX() + vi.getX())/2 * Dt,
                                    xi.getY() + (v.getY() + vi.getY())/2 * Dt);
            vi = v;
        }
        return xi;
    }
}
