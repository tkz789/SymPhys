package symphys.symphys;

import javafx.scene.control.Control;
import java.util.List;

public interface SimulationFactory{
    public SimulationModel getModel();
    public GraphicsHandler getGraphicsHandler();
    public List<Control> getControls();
}
