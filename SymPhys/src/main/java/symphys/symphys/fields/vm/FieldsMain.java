package symphys.symphys.fields.vm;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import symphys.symphys.fields.graphics.GraphicsHandler;
import symphys.symphys.fields.sim.Body;
import symphys.symphys.fields.sim.BodyState;
import symphys.symphys.fields.sim.Simulation;

import java.util.ArrayList;
import java.util.List;

public class FieldsMain {
    static Simulation simulation;
    private static Timeline timeline;
    public static boolean paused=true;

    public static void init(Stage stage) {
        GraphicsHandler.init(stage);
        init_simulation(new ArrayList<>());
    }

    public static void init_simulation(List<GBody> list) {
        int num_bodies = list.size();
        simulation = new Simulation(num_bodies);
        simulation.dt = 0.03;
        for (int i=0; i<num_bodies; ++i) {
            simulation.bodies[i] = new Body();
            simulation.bodies[i].mass = list.get(i).get_mass();
            simulation.bodies[i].charge = list.get(i).get_charge();
            simulation.bodies[i].movableY = list.get(i).get_moveableY();
            simulation.bodies[i].movableX = list.get(i).get_moveableX();
            simulation.states[i] = new BodyState(simulation.bodies[i]);
            simulation.states[i].position = list.get(i).get_position();
            simulation.states[i].velocity = list.get(i).get_velocity();
        }

        paused = true;
        timeline = new Timeline(new KeyFrame(Duration.seconds(simulation.dt), e -> {
            draw_simulation();
            simulation.step();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public static void draw_simulation() {
        GraphicsHandler.drawSimulation(new GSimState(simulation));
    }

    public static void play_simulation() {
        paused = false;
        timeline.play();
    }

    public static void pause_simulation() {
        paused = true;
        timeline.pause();
    }

    public static void setG(double G) {
        simulation.G = G;
    }

    public static void setK(double k) {
        simulation.k = k;
    }

}
