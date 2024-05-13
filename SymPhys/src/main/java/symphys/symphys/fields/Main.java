package symphys.symphys.fields;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import symphys.symphys.fields.graphics.GraphicsHandler;
import symphys.symphys.fields.graphics.TestGraphicsHandler;
import symphys.symphys.fields.sim.Body;
import symphys.symphys.fields.sim.Simulation;
import symphys.symphys.fields.sim.Testy;

public class Main {
    static Stage stage;

    public static void start(Stage stage) {
        Main.stage = stage;
        Test2.start();
    }

    private static void test1() {
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


    public static class Test2 {
        static GraphicsHandler graphicsHandler;
        public static Simulation simulation = new Simulation(4);
        static Timeline timeline;
        static void start() {
             graphicsHandler = new GraphicsHandler(stage);
        }
        public static void startSimulation() {
            System.out.println("New simulation:");
            for (Body body : simulation.bodies) {
                System.out.println(body);
            }
            timeline = new Timeline(new KeyFrame(Duration.seconds((simulation.dt)), e -> {
                graphicsHandler.drawSimulation(simulation);
                simulation.step();
                // simulation.dumpPositions();
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }
        public static void pauseSimulation() {
            if (timeline==null) return;
            timeline.stop();
        }
    }
}
