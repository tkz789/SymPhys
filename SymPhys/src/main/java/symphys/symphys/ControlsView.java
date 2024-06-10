package symphys.symphys;

import javafx.scene.control.Control;
import javafx.scene.layout.VBox;

import java.util.List;

public class ControlsView {
    public static VBox createVBox(List<Control> controls) {
        VBox box = new VBox();
        box.getChildren().addAll(controls);
        box.setSpacing(10);
        return box;
    }
}
