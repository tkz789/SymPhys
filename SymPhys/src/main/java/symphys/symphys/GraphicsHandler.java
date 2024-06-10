package symphys.symphys;

import javafx.scene.Node;

import java.util.List;

public abstract class GraphicsHandler<T extends SimulationModel> {
    protected T model;
    public GraphicsHandler(T model){
        this.model = model;
    }
    public abstract List<Node> getObjects();
    public abstract void drawSimulation();
}
