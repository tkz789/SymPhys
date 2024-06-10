package symphys.symphys;

import javafx.scene.Node;

import java.util.List;

public interface GraphicsHandler<T extends SimulationModel> {
    public List<Node> getObjects();
    public void drawSimulation(T model);
}
