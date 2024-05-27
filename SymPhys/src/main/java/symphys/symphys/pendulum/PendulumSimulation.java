package symphys.symphys.pendulum;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PendulumSimulation {
    public static void startSimulation(Stage stage) {
        Slider lengthSlider = new Slider(0.1, 2.75, 1.75);
        Slider gravitySlider = new Slider(0, 100, 9.81);
        Slider initialAngleSlider = new Slider(0, Math.PI / 2, 1);
        Label lengthLabel = new Label("Rod length: 1.75 m");
        Label gravityLabel = new Label("Gravity: 9.81 m/s^2");
        Label initialAngleLabel = new Label("Initial angle: 1.00 rad");
        VBox sliderBox = new VBox(10);

        lengthSlider.valueProperty().addListener(
                (observable, oldValue, newValue) -> lengthLabel.setText(String.format("Rod length: %.2f m", newValue.doubleValue())));
        gravitySlider.valueProperty().addListener(
                (observable, oldValue, newValue) -> gravityLabel.setText(String.format("Gravity: %.2f m/s^2", newValue.doubleValue())));
        initialAngleSlider.valueProperty().addListener(
                (observable, oldValue, newValue) -> initialAngleLabel.setText(String.format("Initial angle: %.2f rad", newValue.doubleValue())));

        EventHandler<ActionEvent> startButtonHandler = (e) -> {
            PendulumAnimation animation = new PendulumAnimation(new SimplePendulum(lengthSlider.getValue(), gravitySlider.getValue(), initialAngleSlider.getValue()));
            Group animationRoot = new Group(animation.rod, animation.bob);
            animation.start();
            Scene animationScene = new Scene(animationRoot, 500, 300);
            stage.setScene(animationScene);
            stage.show();
        };
        Button startButton = new Button("Start animation");
        startButton.setOnAction(startButtonHandler);
        AnchorPane anchorPane = new AnchorPane();

        sliderBox.getChildren().addAll(lengthSlider, lengthLabel, gravitySlider, gravityLabel, initialAngleSlider, initialAngleLabel, startButton);
        AnchorPane.setTopAnchor(sliderBox, 50.);
        AnchorPane.setLeftAnchor(sliderBox, 50.);
        anchorPane.getChildren().add(sliderBox);
        Group root = new Group(anchorPane);
        Scene scene = new Scene(root, 500, 300);
        stage.setScene(scene);
        stage.show();
    }
}
