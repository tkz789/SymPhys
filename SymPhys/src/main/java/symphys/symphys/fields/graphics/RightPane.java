package symphys.symphys.fields.graphics;

import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;

public class RightPane extends ScrollPane {

    Accordion accordion;
    ConstantsPane constantsPane = new ConstantsPane();

    RightPane() {
        setMinWidth(250);
        setMaxWidth(250);
        accordion = new Accordion(constantsPane);
        setContent(accordion);
    }

    void setLocked(boolean b) {
        constantsPane.setLocked(b);
    }
}
