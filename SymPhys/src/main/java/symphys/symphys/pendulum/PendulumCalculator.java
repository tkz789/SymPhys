package symphys.symphys.pendulum;

import java.awt.geom.Point2D;

public abstract class PendulumCalculator {
    public abstract Point2D.Double position(double time);
}