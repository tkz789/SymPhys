package symphys.symphys.fields.graphics;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import symphys.symphys.fields.vm.FieldsMain;
import symphys.symphys.fields.vm.GBody;

import java.util.ArrayList;
import java.util.List;

class LeftPane extends ScrollPane {
    VBox wrapper, formWrapper;
    HBox control_buttons;
    Button startButton, pauseButton, stopButton, addFormButton;
    Separator lastSeparator = new Separator();

    private void lock_state_input() {
        for (Node form : formWrapper.getChildren())  {
            ((LeftPaneForm) form).lock();
        }
        GraphicsHandler.rightPane.setLocked(true);
    }

    private void unlock_state_input() {
        for (Node form : formWrapper.getChildren()) {
            ((LeftPaneForm) form).unlock();
        }
        GraphicsHandler.rightPane.setLocked(false);
    }

    private void pause_sim() {
        FieldsMain.pause_simulation();
        unlock_state_input();
        pauseButton.setText("Unpause");
    }

    private void play_sim() {
        lock_state_input();
        FieldsMain.play_simulation();
        pauseButton.setText("Pause");
    }

    private void init_sim() {
        List<GBody> list = new ArrayList<>();
        for (Node form : formWrapper.getChildren()) {
            try {
                list.add(new GBody((LeftPaneForm) form));
            } catch (NumberFormatException ignored) {}
        }
        FieldsMain.init_simulation(list);
        FieldsMain.setG(GraphicsHandler.rightPane.constantsPane.getG());
        FieldsMain.setK(GraphicsHandler.rightPane.constantsPane.getK());
        FieldsMain.setGravityOn(GraphicsHandler.rightPane.simSettingsPane.getGravityOn());
        FieldsMain.setElectrostaticsOn(GraphicsHandler.rightPane.simSettingsPane.getElectrostaticsOn());
        FieldsMain.draw_simulation();
    }

    private void reassignFormIds() {
        int id=1;
        for (Node node: formWrapper.getChildren()) {
            if (!(node instanceof LeftPaneForm)) continue;
            ((LeftPaneForm) node).updateLabel(id);
            ++id;
        }
    }

    void delForm(LeftPaneForm leftPaneForm) {
        formWrapper.getChildren().remove(leftPaneForm);
        reassignFormIds();
    }

    EventHandler<ActionEvent> startHandler = actionEvent -> {
        pause_sim();
        init_sim();
        play_sim();
    };

    EventHandler<ActionEvent> pauseHandler = actionEvent -> {
        if (FieldsMain.paused) {
            play_sim();
        } else {
            pause_sim();
        }
    };

    EventHandler<ActionEvent> stopHandler = actionEvent -> {
        pause_sim();
        init_sim();
    };

    EventHandler<ActionEvent> addFormHandler = actionEvent -> {
        formWrapper.getChildren().add(new LeftPaneForm(0));
        reassignFormIds();
    };

    LeftPane() {
        setMinWidth(250);
        wrapper = new VBox(10);
        formWrapper = new VBox(10);
        setContent(wrapper);
        startButton = new Button("Start");
        pauseButton = new Button("Pause");
        stopButton = new Button("Stop");
        addFormButton = new Button("Add body");
        control_buttons = new HBox(startButton, pauseButton, stopButton);
        wrapper.getChildren().addAll(control_buttons, formWrapper, lastSeparator, addFormButton);
        startButton.setOnAction(startHandler);
        pauseButton.setOnAction(pauseHandler);
        stopButton.setOnAction(stopHandler);
        addFormButton.setOnAction(addFormHandler);
        for (int i=0; i<4; ++i) {
            formWrapper.getChildren().add(new LeftPaneForm(i+1));
        }
    }
}

