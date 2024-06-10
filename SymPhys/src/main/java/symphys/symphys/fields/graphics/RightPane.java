package symphys.symphys.fields.graphics;

import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;

public class RightPane extends ScrollPane {

    Accordion accordion;
    ConstantsPane constantsPane = new ConstantsPane();
    SimSettingsPane simSettingsPane = new SimSettingsPane();

    RightPane() {
        setMinWidth(250);
        setMaxWidth(250);
        accordion = new Accordion(simSettingsPane, constantsPane);
        setContent(accordion);
    }

    void setLocked(boolean b) {
        simSettingsPane.setLocked(b);
        constantsPane.setLocked(b);
    }
}
