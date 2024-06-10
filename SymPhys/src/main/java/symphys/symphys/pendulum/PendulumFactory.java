package symphys.symphys.pendulum;

import javafx.scene.control.Control;
import symphys.symphys.GraphicsHandler;
import symphys.symphys.SimulationFactory;

import java.util.List;

public abstract class PendulumFactory implements SimulationFactory {
    public abstract PendulumModel getModel();
    public abstract List<Control> getControls();
    public abstract GraphicsHandler<PendulumModel> getGraphicsHandler();
}
