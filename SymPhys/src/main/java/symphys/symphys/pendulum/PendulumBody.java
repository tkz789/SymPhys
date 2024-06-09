package symphys.symphys.pendulum;

import javafx.scene.Group;

import java.awt.geom.Point2D;

public abstract class PendulumBody {
    protected Group elements;
    public abstract void adjustPosition(Point2D.Double position);
    PendulumBody(){
        elements = new Group();
    }
}
