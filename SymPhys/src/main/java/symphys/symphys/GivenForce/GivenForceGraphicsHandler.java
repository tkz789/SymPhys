package symphys.symphys.GivenForce;

import javafx.scene.Node;
import javafx.scene.shape.Circle;
import symphys.symphys.GraphicsHandler;
import symphys.symphys.SimulationModel;
import symphys.symphys.numerical.Wektor;

import java.util.ArrayList;
import java.util.List;

public class GivenForceGraphicsHandler extends GraphicsHandler<GivenForceModel> {

    GivenForceGraphicsHandler(GivenForceModel model){
        super(model);
        Wektor position = model.getPositions();
        nodes.add(new Circle(position.getX() + 100, position.getY() + 100, 10));
    }
    List<Node> nodes = new ArrayList<>();
    @Override
    public List<Node> getObjects() {
        return nodes;
    }

    @Override
    public void drawSimulation() {
        model.step(0.01);
        ((Circle) nodes.get(0)).setCenterX(model.state.position.getX() + nodes.get(0).getScene().getWidth()/2);
        ((Circle) nodes.get(0)).setCenterY(model.state.position.getY() + nodes.get(0).getScene().getHeight()/2);
    }
}
