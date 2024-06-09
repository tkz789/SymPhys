package symphys.symphys.fields.graphics;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class DoubleField extends TextField {
    LeftPaneForm parent;

    DoubleField (LeftPaneForm parent, String s) {
        super(s);
        this.parent = parent;
        init();
    }

    private void init() {
        textProperty().addListener((observableValue, s, t1) -> {
            try {
                getVal();
                setStyle("");
            } catch (NumberFormatException e) {
                setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            }
            parent.update_validity();
        });
    }

    public double getVal() {
        return Double.parseDouble(getText());
    }

    public boolean isValid() {
        try {
            getVal();
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
