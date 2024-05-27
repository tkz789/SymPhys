package symphys.symphys;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//import symphys.symphys.Doppler.Animate;
import symphys.symphys.GivenForce.Main;
import symphys.symphys.pendulum.PendulumSimulation;

import java.io.IOException;

public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("SymPhys");
        Label simOptionsLabel = new Label("Choose the simulation:");
        Button pendulumButton = new Button("Pendulum simulation");
        Button electricFieldButton = new Button("Electric field simulation");
        Button givenForceButton = new Button("Given force simulation");
        VBox optionsBox = new VBox(10, simOptionsLabel  , pendulumButton, electricFieldButton, givenForceButton);
        pendulumButton.setOnAction((e) ->
            PendulumSimulation.startSimulation(stage)
        );
        electricFieldButton.setOnAction((e) ->
            symphys.symphys.fields.Main.start(stage)
        );
        givenForceButton.setOnAction((e) ->
            Main.start(stage)
        );
        Scene scene = new Scene(optionsBox, 500, 300);
        stage.setScene(scene);
        stage.show();
    }
}