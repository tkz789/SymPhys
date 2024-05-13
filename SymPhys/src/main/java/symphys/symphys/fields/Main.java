package symphys.symphys.fields;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import symphys.symphys.fields.graphics.TestGraphicsHandler;
import symphys.symphys.fields.sim.Simulation;
import symphys.symphys.fields.sim.Testy;

public class Main {
    public static void start(Stage stage) {
        TestGraphicsHandler testGraphicsHandler = new TestGraphicsHandler(stage);
        testGraphicsHandler.init();
        Simulation simulation = Testy.setuptest1();
        simulation.dt = 0.03;
        testGraphicsHandler.initCircles(simulation.states.length);
        testGraphicsHandler.displaySimState(simulation);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(simulation.dt), e -> {
            simulation.step();
            testGraphicsHandler.displaySimState(simulation);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
