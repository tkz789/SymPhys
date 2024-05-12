package symphys.symphys.GivenForce;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.awt.geom.Point2D;

public class AnimationGivenForce {
    public static void start(Stage stage){
        Group group = new Group();
        Circle circle = new Circle(200, 200, 20);
        group.getChildren().add(circle);
        Scene scene = new Scene(group, 400, 400);
        stage.setScene(scene);
        stage.show();
        AnimationTimer animation = new AnimationTimer() {
            final PositionCalculator a = new PositionCalculator();
            @Override
            public void handle(long l) {
                Point2D.Double newPosition = a.getNextPosition();
//                circle.setTranslateX(newPosition.getX()*100);
//                circle.setTranslateY(newPosition.getY()*100);
//                circle.translateXProperty();
//                circle.translateYProperty();
                circle.setCenterX(200 + newPosition.getX()*100);
                circle.setCenterY(200 + newPosition.getY()*100);
            }
        };
        animation.start();
    }
}
