package symphys.symphys;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.util.Duration;
import symphys.symphys.GivenForce.GivenForceFactory;
import symphys.symphys.fields.Main;
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
                Main.start(stage);
            }
        };
        Button givenForceButton = new Button("Given force simulation"){
            public void fire(){
                factory = new GivenForceFactory();
                simulate(stage);
            }
        };
        VBox optionsBox = new VBox(10, simOptionsLabel, simplePendulumButton, springPendulumButton, electricFieldButton, givenForceButton);
        AnchorPane.setTopAnchor(optionsBox, 10.);
        AnchorPane.setLeftAnchor(optionsBox, 10.);
        AnchorPane pane = new AnchorPane(optionsBox);
        Scene scene = new Scene(pane, 500, 300);
        stage.setScene(scene);
        stage.show();
    }

    public void simulate(Stage stage){
        VBox box = ControlsView.createVBox(factory.getControls());
        AnchorPane.setLeftAnchor(box, 10.);
        AnchorPane.setTopAnchor(box, 10.);
        AnchorPane anchorPane = new AnchorPane(box);

        Group root = new Group(anchorPane);

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

        HBox buttonBox = new HBox(10, startButton, stopButton, backButton);
        AnchorPane.setLeftAnchor(buttonBox, 10.);
        AnchorPane anchorPane2 = new AnchorPane(buttonBox);
        VBox rootBox = new VBox(10, anchorPane, anchorPane2);
        root.getChildren().add(rootBox);

        Scene scene = new Scene(root, 500, 300);

        rootBox.prefWidthProperty().bind(scene.widthProperty());
        rootBox.prefHeightProperty().bind(scene.heightProperty());

        stage.setScene(scene);
        stage.show();
    }
}
