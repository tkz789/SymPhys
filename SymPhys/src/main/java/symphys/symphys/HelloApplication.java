package symphys.symphys;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//import symphys.symphys.Doppler.Animate;
import symphys.symphys.fields.Main;
import symphys.symphys.pendulum.PendulumAnimation;
import symphys.symphys.pendulum.PendulumSimulation;
import symphys.symphys.GivenForce.AnimationGivenForce;
import symphys.symphys.pendulum.SimplePendulum;

import java.io.IOException;

public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 620, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
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
            Main.start(stage)
        );
        givenForceButton.setOnAction((e) ->
            AnimationGivenForce.start(stage)
        );
        Scene scene = new Scene(optionsBox, 500, 300);
        stage.setScene(scene);
        stage.show();
//        Animate.startSym1(stage);
//        AnimationGivenForce.start(stage);
//        Main.start(stage);
//        PendulumSimulation.startSimulation(stage);
    }
}