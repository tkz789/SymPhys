package symphys.symphys.pendulum;

import java.awt.geom.Point2D;

public abstract class Pendulum {
    public abstract double angle(double time);

    public abstract Point2D.Double position(double time);

}
