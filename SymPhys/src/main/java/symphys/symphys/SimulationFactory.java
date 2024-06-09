package symphys.symphys;

import javax.sound.sampled.Control;
import java.util.List;

public interface SimulationFactory{
    public SimulationModel getModel();
    public GraphicsHandler getGraphicsHandler();
    public List<Control> getControls();
}
