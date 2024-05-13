package symphys.symphys.GivenForce;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.awt.geom.Point2D;

public class AnimationGivenForce {
    public static void start(Stage stage) {
        Group group = new Group();
        Circle circle = new Circle(200, 200, 20);
        group.getChildren().add(circle);
        Scene scene = new Scene(group, 400, 400);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        AnimationTimer animation = new AnimationTimer() {
            final PositionCalculator a = new PositionCalculator((x,v,t) -> new Point2D.Double(-x.getX()*0.0001 - v.getX()*0.1,-x.getY()*0.0001 - v.getY()*0.1),
                    new Point2D.Double(2, 0),
                    new Point2D.Double(0, 150),
                    1);

            @Override
            public void handle(long l) {
                Point2D.Double newPosition = a.getNextPosition(l);
                circle.setCenterX(200 + newPosition.getX());
                circle.setCenterY(200 + newPosition.getY());
            }
        };
        animation.start();
    }
}
