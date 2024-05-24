package symphys.symphys.pendulum;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.awt.geom.Point2D;

public class SimplePendulumBody extends PendulumBody{
    Circle bob;
    Line rod;

    SimplePendulumBody(){
        rod = new Line(250, 0, 250, 180);
        bob = new Circle(rod.getEndX(), rod.getEndY(), 20, Color.BROWN);
        elements.getChildren().addAll(rod, bob);
    }

    @Override
    public void adjustPosition(Point2D.Double position) {
        bob.setCenterX(250+position.x);
        bob.setCenterY(position.y);
        rod.setStartX(250);
        rod.setStartY(0);
        rod.setEndX(bob.getCenterX());
        rod.setEndY(bob.getCenterY());
    }
}
