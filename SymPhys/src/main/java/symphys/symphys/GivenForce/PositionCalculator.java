package symphys.symphys.GivenForce;

import java.awt.geom.Point2D;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class PositionCalculator {
    int step = 0;
    public Point2D.Double getNextPosition(){
        long time = System.currentTimeMillis();
        return new Point2D.Double(sin(time*0.005), cos(time*0.005));
    }
}
