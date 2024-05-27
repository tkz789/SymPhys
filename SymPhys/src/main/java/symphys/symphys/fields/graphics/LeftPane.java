package symphys.symphys.fields.graphics;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import symphys.symphys.fields.Main;
import symphys.symphys.fields.sim.Body;
import symphys.symphys.fields.sim.BodyState;
import symphys.symphys.fields.sim.Simulation;
import symphys.symphys.numerical.Wektor;

class LeftPane extends ScrollPane {
    VBox wrapper;
    Button startButton, pauseButton;
    LeftPaneForm[] forms = new LeftPaneForm[4];

    EventHandler<ActionEvent> startHandler = actionEvent -> {
        // new simulation
        Simulation simulation = new Simulation(forms.length);
        for (int i=0; i<forms.length; ++i) {
            simulation.bodies[i] = new Body();
            simulation.states[i] = new BodyState(simulation.bodies[i]);
            simulation.bodies[i].mass = Double.parseDouble(forms[i].massField.getText());
            simulation.bodies[i].charge = Double.parseDouble(forms[i].chargeField.getText());
            simulation.states[i].position = new Wektor(Double.parseDouble(forms[i].xField.getText()), Double.parseDouble(forms[i].yField.getText()));
            simulation.states[i].velocity = new Wektor(Double.parseDouble(forms[i].vxField.getText()), Double.parseDouble(forms[i].vyField.getText()));
            if (forms[i].xLocked.isSelected()) simulation.bodies[i].movableX = false;
            if (forms[i].yLocked.isSelected()) simulation.bodies[i].movableY = false;
        }
        Main.Test2.pauseSimulation();
        Main.Test2.simulation = simulation;
        Main.Test2.startSimulation();
    };

    EventHandler<ActionEvent> pauseHandler = actionEvent -> Main.Test2.pauseSimulation();

    LeftPane() {
        setMinWidth(250);
        wrapper = new VBox(10);
        setContent(wrapper);
        startButton = new Button("Start");
        pauseButton = new Button("Pause");
        wrapper.getChildren().add(startButton);
        wrapper.getChildren().add(pauseButton);
        startButton.setOnAction(startHandler);
        pauseButton.setOnAction(pauseHandler);
        for (int i=0; i<forms.length; ++i) {
            forms[i] = new LeftPaneForm(i);
            wrapper.getChildren().add(forms[i]);
        }
    }
}

class LeftPaneForm extends VBox {
    int bodyId;
    TextField xField, yField, vxField, vyField, massField, chargeField;
    CheckBox xLocked, yLocked;

    LeftPaneForm(int bodyId) {
        super(5);
        this.bodyId = bodyId;
        xField = new TextField("0");
        yField = new TextField("0");
        vxField = new TextField("0");
        vyField = new TextField("0");
        massField = new TextField("1");
        chargeField = new TextField("0");
        xLocked = new CheckBox("locked X");
        yLocked = new CheckBox("locked Y");
        getChildren().addAll(new Separator(), new Label("Body "+(bodyId+1)+":"),
                new Label("x0 [m]:"), xField,
                new Label("y0 [m]:"), yField,
                new Label("vx0 [m/s]:"), vxField,
                new Label("vy0 [m/s]:"), vyField,
                new Label("Mass [kg]:"), massField,
                new Label("Charge [C]:"), chargeField,
                xLocked, yLocked);
    }
}