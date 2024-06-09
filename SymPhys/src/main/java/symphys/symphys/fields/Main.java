package symphys.symphys.fields;

import javafx.stage.Stage;
import symphys.symphys.fields.vm.FieldsMain;

public class Main {
    static FieldsMain fm;

    public static void start(Stage stage) {
        // Test2.start();
        m3(stage);
    }

    private static void m3(Stage stage) {
        FieldsMain.init(stage);
    }

    /*private static void test1() {
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
        public static boolean paused=true;
        static GraphicsHandler graphicsHandler;
        public static Simulation simulation = new Simulation(4);
        static Timeline timeline;
        static void start() {
             graphicsHandler = new GraphicsHandler(stage);
        }
        public static void startSimulation() {
            /*System.out.println("New simulation:");
            for (Body body : simulation.bodies) {
                System.out.println(body);
            }*//*
            timeline = new Timeline(new KeyFrame(Duration.seconds((simulation.dt)), e -> {
                graphicsHandler.drawSimulation(simulation);
                simulation.step();
                // simulation.dumpPositions();
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
            paused = false;
        }
        public static void pauseSimulation() {
            if ((timeline==null)||paused) return;
            timeline.pause();
            paused = true;
        }
        public static void unpauseSimulation() {
            if ((!paused)||(timeline==null)) return;
            timeline.play();
            paused = false;
        }
    }*/
}
