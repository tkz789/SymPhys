package symphys.symphys.fields.graphics;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import symphys.symphys.fields.sim.Simulation;

public class TestGraphicsHandler {
    Stage stage;
    Scene scene;
    Group group;
    Circle[] circles;

    public TestGraphicsHandler(Stage stage) {
        this.stage = stage;
    }

    public void init() {
        stage.setTitle("Test Fields.");
        group = new Group();
        scene = new Scene(group, 800, 800, Color.WHITE);
        stage.setScene(scene);
        stage.show();
    }

    public void initCircles(int n) {
        circles = new Circle[n];
        for (int i=0; i<n; ++i) {
            circles[i] = new Circle(0, 0, 4, Color.BLACK);
            group.getChildren().add(circles[i]);
        }
    }

    public void displaySimState(Simulation simulation) {
        for (int i=0; i<simulation.states.length; ++i) {
            circles[i].setCenterX(simulation.states[i].position.getX()+400);
            circles[i].setCenterY(simulation.states[i].position.getX()+400);
        }
        stage.show();
    }
}
