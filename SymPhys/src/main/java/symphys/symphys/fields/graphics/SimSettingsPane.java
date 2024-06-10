package symphys.symphys.fields.graphics;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import symphys.symphys.fields.vm.FieldsMain;

public class SimSettingsPane extends TitledPane {
    CheckBox gravityOn = new CheckBox(), electrostaticsOn = new CheckBox();
    DoubleField scale = new DoubleField(null, "15");
    Button redrawFrame = new Button("Redraw frame");
    VBox vbox = new VBox(5);

    EventHandler<ActionEvent> redrawHandler = actionEvent -> {
        FieldsMain.draw_simulation();
    };

    SimSettingsPane() {
        setText("General simulation settings");
        setContent(vbox);
        electrostaticsOn.setSelected(true);
        redrawFrame.setOnAction(redrawHandler);
        vbox.getChildren().addAll(
                new HBox(new Label("Simulate gravity: "), gravityOn),
                new HBox(new Label("Simulate electrostatics: "), electrostaticsOn),
                new HBox(new Label("Scale: "), scale),
                redrawFrame
        );
    }

    void setLocked(boolean b) {
        gravityOn.setDisable(b);
        electrostaticsOn.setDisable(b);
        scale.setDisable(b);
        redrawFrame.setDisable(b);
    }

    double getScale() {
        if (scale.isValid()) return scale.getVal();
        return 15;
    }
    boolean getGravityOn() {return gravityOn.isSelected();}
    boolean getElectrostaticsOn() {return electrostaticsOn.isSelected();}
}
