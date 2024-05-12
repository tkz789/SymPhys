package symphys.symphys.pendulum;

import java.awt.geom.Point2D;
import static java.lang.Math.*;


/**
 * Mathematical pendulum with small angle approximation
 */
public class SimplePendulum extends Pendulum{

    double length, gravity, initial_angle = 1, initial_velocity = 0;
    double omega;
    SimplePendulum(double length, double gravity){
        this.gravity = gravity;
        this.length = length;
        omega = sqrt(gravity/length);
    }
    SimplePendulum(double length){
        this(length, 9.81);
    }

    public double angle(double time){
        return initial_angle * cos(omega * time) + initial_velocity  * sin(omega * time);
    }

    public Point2D.Double position(double time){
        double theta = angle(time);
        double x = length * sin(theta);
        double y = length * cos(theta);
        return new Point2D.Double(x, y);
    }
}
