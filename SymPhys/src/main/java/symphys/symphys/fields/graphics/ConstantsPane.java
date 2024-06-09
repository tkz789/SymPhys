package symphys.symphys.fields.graphics;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import symphys.symphys.fields.sim.Physics;
import javafx.event.EventHandler;

class ConstantsPane extends TitledPane {
    VBox vbox = new VBox(5);
    DoubleField gField = new DoubleField(null, Double.toString(Physics.G));
    DoubleField kField = new DoubleField(null, Double.toString(Physics.k));
    CheckBox customG = new CheckBox(), customK = new CheckBox();
    final Text infoText = new Text("Select checkboxes to make the simulation use the custom values.");
    private boolean locked=false;

    EventHandler<ActionEvent> gCheckboxHandler = actionEvent -> {gField.setDisable(locked || !customG.isSelected());};
    EventHandler<ActionEvent> kCheckboxHandler = actionEvent -> {kField.setDisable(locked || !customK.isSelected());};

    ConstantsPane () {
        setText("Physical constants");
        setContent(vbox);
        infoText.setWrappingWidth(220);
        infoText.setFont(new Font(12));
        customG.setOnAction(gCheckboxHandler);
        customK.setOnAction(kCheckboxHandler);
        gField.setDisable(true);
        kField.setDisable(true);
        vbox.getChildren().addAll(
                new Label("G [N*m^2*kg^-2]:"),
                new HBox(5, customG, gField),
                new Label("k [N*m^2*C^-2]:"),
                new HBox(5, customK, kField),
                infoText
        );
    }

    void setLocked(boolean b) {
        locked = b;
        customG.setDisable(b);
        customK.setDisable(b);
        gField.setDisable(b || (!customG.isSelected()));
        kField.setDisable(b || (!customK.isSelected()));
    }

    public double getG() {
        if (!customG.isSelected()) return Physics.G;
        if (!gField.isValid()) return Physics.G;
        return gField.getVal();
    }

    public double getK() {
        if (!customK.isSelected()) return Physics.k;
        if (!kField.isValid()) return Physics.k;
        return kField.getVal();
    }
}
