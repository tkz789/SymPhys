package symphys.symphys.pendulum;

import symphys.symphys.numerical.Wektor;

import java.awt.geom.Point2D;

public abstract class PendulumCalculator {
    public abstract Wektor position(double time);
}