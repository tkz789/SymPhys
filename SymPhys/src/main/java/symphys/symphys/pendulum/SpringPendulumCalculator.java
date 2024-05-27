package symphys.symphys.pendulum;

import java.awt.geom.Point2D;

public class SpringPendulumCalculator extends PendulumCalculator{
    double mass;
    double spring;
    double length;
    double initialVelocity=0;
    SpringPendulumCalculator(double length, double mass, double spring){
        this.length=length; this.mass=mass; this.spring=spring;
    }

    SpringPendulumCalculator(){
        this(125, 20, 1);
    }

    @Override
    public Point2D.Double position(double time) {
        double omega = Math.sqrt(spring/mass);
        double y = length*Math.cos(omega * time) + initialVelocity*Math.sin(omega * time);
        return new Point2D.Double(0, y+length);
    }
}
