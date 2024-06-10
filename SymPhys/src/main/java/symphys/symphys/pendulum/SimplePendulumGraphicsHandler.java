package symphys.symphys.pendulum;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.List;

public class SimplePendulumGraphicsHandler extends PendulumGraphicsHandler{
    Circle bob;
    Line line;
    SimplePendulumGraphicsHandler(PendulumModel model){
        super(model);
        bob = new Circle(0, 0, 20, Color.RED);
        line = new Line(0, 0, 0, 0);
    }
    @Override
    public void drawSimulation(){
        bob.setCenterX(model.bob.position.getX()+250); bob.setCenterY(model.bob.position.getY());
        line.setStartX(250); line.setStartY(0);
        line.setEndX(bob.getCenterX()); line.setEndY(bob.getCenterY());
    }
    @Override
    public List<Node> getObjects(){
        return List.of(line, bob);
    }
}
