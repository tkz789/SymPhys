package symphys.symphys.pendulum;

import javafx.animation.*;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class PendulumAnimation extends AnimationTimer{
    Circle bob;
    Line rod;
    Pendulum pendulum;
    PendulumAnimation(Pendulum pendulum){
        this.pendulum=pendulum;
        rod=new Line(250, 0, 250, 180);
        bob=new Circle(rod.getEndX(), rod.getEndY(), 20, Color.BROWN);
    }
    @Override
    public void handle(long nanoseconds) {
        double time = (double) nanoseconds/100000000;
        bob.setCenterX(250+pendulum.position(time).x);
        bob.setCenterY(pendulum.position(time).y);
        rod.setStartX(250);
        rod.setStartY(0);
        rod.setEndX(bob.getCenterX());
        rod.setEndY(bob.getCenterY());
    }
}
