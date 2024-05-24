package symphys.symphys.pendulum;

import javafx.scene.layout.VBox;

public abstract class PendulumFactory {
    public abstract Pendulum createPendulum();
    public abstract void showParameterChoice(VBox box);
}
