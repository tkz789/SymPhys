package symphys.symphys.pendulum;

import java.awt.geom.Point2D;
import static java.lang.Math.*;


/**
 * Mathematical pendulum with small angle approximation
 */
public class SimplePendulumCalculator extends PendulumCalculator{

    double length, gravity, initialAngle, initialVelocity = 0;
    double omega;
    double damping, lambda;
    SimplePendulumCalculator(double length, double gravity, double initial_angle, double damping){
        this.gravity = gravity;
        this.length = length;
        this.initialAngle = initial_angle;
        this.damping = damping;
        omega = sqrt(gravity/length);
        lambda = sqrt(abs(damping * damping - omega * omega));
    }
    SimplePendulumCalculator(double length){
        this(length, 9.81, 0.1, 0);
    }

    public double angle(double time){
        /*
        return initialAngle * cos(omega * time) + initialVelocity  * sin(omega * time);
        */

        if (damping == omega) {
            return (initialAngle + (initialAngle * damping + initialVelocity) * time) * exp(-damping * time);
        } else if (damping > omega){
            double B = (initialVelocity + (damping - lambda) * initialAngle) / (-2 * lambda);
            double A = initialAngle - B;
            return exp(-damping * time) * (A * exp(lambda * time) + B * exp(-lambda * time));
        } else {
            double B = (initialVelocity + damping * initialAngle) / lambda;
            return exp(-damping * time) * (initialAngle * cos(lambda * time) + B * sin(lambda * time));
        }

    }

    public Point2D.Double position(double time){
        double theta = angle(time);
        double x = length * sin(theta);
        double y = length * cos(theta);
        return new Point2D.Double(x, y);
    }
}
