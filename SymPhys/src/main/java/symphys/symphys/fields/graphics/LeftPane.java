package symphys.symphys.fields.graphics;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import symphys.symphys.fields.vm.FieldsMain;
import symphys.symphys.fields.vm.GBody;

import java.util.ArrayList;
import java.util.List;

class LeftPane extends ScrollPane {
    VBox wrapper;
    HBox control_buttons;
    Button startButton, pauseButton, stopButton;
    LeftPaneForm[] forms = new LeftPaneForm[4];

    private void lock_state_input() {
        for (LeftPaneForm form : forms)  {
            form.lock();
        }
    }

    private void unlock_state_input() {
        for (LeftPaneForm form : forms) {
            form.unlock();
        }
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
        for (LeftPaneForm form : forms) {
            try {
                list.add(new GBody(form));
            } catch (NumberFormatException ignored) {}
        }
        FieldsMain.init_simulation(list);
        FieldsMain.draw_simulation();
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

    LeftPane() {
        setMinWidth(250);
        wrapper = new VBox(10);
        setContent(wrapper);
        startButton = new Button("Start");
        pauseButton = new Button("Pause");
        stopButton = new Button("Stop");
        control_buttons = new HBox(startButton, pauseButton, stopButton);
        wrapper.getChildren().add(control_buttons);
        startButton.setOnAction(startHandler);
        pauseButton.setOnAction(pauseHandler);
        stopButton.setOnAction(stopHandler);
        for (int i=0; i<forms.length; ++i) {
            forms[i] = new LeftPaneForm(i);
            wrapper.getChildren().add(forms[i]);
        }
    }
}

