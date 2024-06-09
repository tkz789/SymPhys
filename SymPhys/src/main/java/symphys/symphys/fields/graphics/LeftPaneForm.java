package symphys.symphys.fields.graphics;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;

public class LeftPaneForm extends VBox implements Validable {
    int bodyId;
    public DoubleField xField, yField, vxField, vyField, massField, chargeField;
    public CheckBox xLocked, yLocked;
    private final Text invalidText = new Text("Invalid data.");

    LeftPaneForm(int bodyId) {
        super(5);
        this.bodyId = bodyId;
        xField = new DoubleField(this, "0");
        yField = new DoubleField(this, "0");
        vxField = new DoubleField(this, "0");
        vyField = new DoubleField(this, "0");
        massField = new DoubleField(this, "1");
        chargeField = new DoubleField(this, "0");
        xLocked = new CheckBox("locked X");
        yLocked = new CheckBox("locked Y");
        invalidText.setVisible(false);
        invalidText.setStyle("-fx-fill: red;");
        getChildren().addAll(new Separator(), new Label("Body "+(bodyId+1)+":"),
                new Label("x0 [m]:"), xField,
                new Label("y0 [m]:"), yField,
                new Label("vx0 [m/s]:"), vxField,
                new Label("vy0 [m/s]:"), vyField,
                new Label("Mass [kg]:"), massField,
                new Label("Charge [C]:"), chargeField,
                xLocked, yLocked, invalidText);
    }

    void lock() {
        set_da(true);
    }

    void unlock() {
        set_da(false);
    }

    private void set_da(boolean b) {
        xField.setDisable(b);
        yField.setDisable(b);
        vxField.setDisable(b);
        vyField.setDisable(b);
        massField.setDisable(b);
        chargeField.setDisable(b);
        xLocked.setDisable(b);
        yLocked.setDisable(b);
    }

    public void update_validity() {
        boolean valid=false;
        if (xField.isValid()) if (yField.isValid()) if (vxField.isValid()) if (vyField.isValid()) if (massField.isValid()) if (chargeField.isValid()) {
            valid = true;
        }
        invalidText.setVisible(!valid);
    }
}
