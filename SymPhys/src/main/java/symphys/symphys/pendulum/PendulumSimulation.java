package symphys.symphys.pendulum;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PendulumSimulation {
    public static void startSimulation(Stage stage){
        PendulumAnimation animation = new PendulumAnimation(new SimplePendulum(200, 9.81));
        Group root = new Group(animation.rod, animation.bob);
        Scene scene = new Scene(root, 500, 300);
        stage.setScene(scene);
        stage.show();
        animation.start();
    }
}
