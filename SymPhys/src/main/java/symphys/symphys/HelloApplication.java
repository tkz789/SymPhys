package symphys.symphys;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//import symphys.symphys.Doppler.Animate;
import javafx.util.Duration;
import symphys.symphys.GivenForce.Main;
import symphys.symphys.pendulum.PendulumModel;
import symphys.symphys.pendulum.PendulumSimulation;
import symphys.symphys.pendulum.SimplePendulumFactory;
import symphys.symphys.pendulum.SpringPendulumFactory;

import java.io.IOException;

public class HelloApplication extends Application {
    static SimulationFactory factory;
    static GraphicsHandler graphicsHandler;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("SymPhys");
        Label simOptionsLabel = new Label("Choose the simulation:");
        Button simplePendulumButton = new Button("Simple pendulum simulation"){
            public void fire(){
                factory = new SimplePendulumFactory();
                simulate(stage);
            }
        };
        Button springPendulumButton = new Button("Spring pendulum simulation"){
            public void fire(){
                factory = new SpringPendulumFactory();
                simulate(stage);
            }
        };
        Button electricFieldButton = new Button("Electric field simulation"){
            public void fire(){
                //factory = new SimplePendulumFactory();
                simulate(stage);
            }
        };
        Button givenForceButton = new Button("Given force simulation"){
            public void fire(){
                //factory = new GivenForceFactory();
                simulate(stage);
            }
        };
        VBox optionsBox = new VBox(10, simOptionsLabel, simplePendulumButton, springPendulumButton, electricFieldButton, givenForceButton);
        Scene scene = new Scene(optionsBox, 500, 300);
        stage.setScene(scene);
        stage.show();
    }
    public void simulate(Stage stage){
        VBox box = ControlsView.createVBox(factory.getControls());
        Group root = new Group(box);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds((0.01)), e -> {
            graphicsHandler.drawSimulation();
            graphicsHandler.model.step(0.01);
            // simulation.dumpPositions();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        Button startButton = new Button("Start"){
            @Override
            public void fire(){
                graphicsHandler = factory.getGraphicsHandler();
                root.getChildren().addAll(graphicsHandler.getObjects());
                timeline.play();
                setVisible(false);
            }
        };
        Button stopButton = new Button("Stop"){
            boolean paused = false;
            @Override
            public void fire(){
                if(!paused) {
                    timeline.pause();
                    paused = true;
                }
                else {
                    timeline.play();
                    paused = false;
                }
            }
        };
        Button backButton = new Button("Go back"){
            @Override
            public void fire(){
                try {
                    start(stage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        root.getChildren().addAll(startButton, stopButton, backButton);
        startButton.setVisible(true);
        startButton.setLayoutX(400);
        startButton.setLayoutY(150);
        stopButton.setLayoutX(400);
        stopButton.setLayoutY(180);
        backButton.setLayoutX(400);
        backButton.setLayoutY(260);
        Scene scene = new Scene(root, 500, 300);
        stage.setScene(scene);
        stage.show();
    }
}