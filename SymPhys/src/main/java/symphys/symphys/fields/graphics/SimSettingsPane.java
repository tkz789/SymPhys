package symphys.symphys.fields.graphics;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SimSettingsPane extends TitledPane {
    CheckBox gravityOn = new CheckBox(), electrostaticsOn = new CheckBox();
    VBox vbox = new VBox(5);

    SimSettingsPane() {
        setText("General simulation settings");
        setContent(vbox);
        electrostaticsOn.setSelected(true);
        vbox.getChildren().addAll(
                new HBox(new Label("Simulate gravity: "), gravityOn),
                new HBox(new Label("Simulate electrostatics: "), electrostaticsOn)
        );
    }

    void setLocked(boolean b) {
        gravityOn.setDisable(b);
        electrostaticsOn.setDisable(b);
    }

    boolean getGravityOn() {return gravityOn.isSelected();}
    boolean getElectrostaticsOn() {return electrostaticsOn.isSelected();}
}
