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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PendulumSimulation {
    public static void startSimulation(Stage stage){
        Slider lengthSlider=new Slider(50, 275, 175);
        Slider gravitySlider=new Slider(0, 100, 9.81);
        Slider initialAngleSlider=new Slider(0, 2*Math.PI, 1);
        Label lengthLabel=new Label("Rod length: 175,00");
        Label gravityLabel=new Label("Gravity: 9,81");
        Label initialAngleLabel=new Label("Initial angle: 1,00");
        VBox sliderBox=new VBox(10);

        lengthSlider.valueProperty().addListener(
                new ChangeListener<Number>() {

                    public void changed(ObservableValue<? extends Number > observable, Number oldValue, Number newValue)
                    {
                        lengthLabel.setText("Rod length: " + String.format("%.2f", newValue.doubleValue()));
                    }
                });
        gravitySlider.valueProperty().addListener(
                new ChangeListener<Number>() {

                    public void changed(ObservableValue<? extends Number > observable, Number oldValue, Number newValue)
                    {
                        gravityLabel.setText("Gravity: " + String.format("%.2f", newValue.doubleValue()));
                    }
                });
        initialAngleSlider.valueProperty().addListener(
                new ChangeListener<Number>() {

                    public void changed(ObservableValue<? extends Number > observable, Number oldValue, Number newValue)
                    {
                        initialAngleLabel.setText("Initial angle: " + String.format("%.2f", newValue.doubleValue()));
                    }
                });

        EventHandler<ActionEvent> startButtonHandler=(e) -> {
                PendulumAnimation animation = new PendulumAnimation(new SimplePendulum(lengthSlider.getValue(), gravitySlider.getValue(), initialAngleSlider.getValue()));
                Group animationRoot = new Group(animation.rod, animation.bob);
                animation.start();
                Scene animationScene=new Scene(animationRoot, 500, 300);
                stage.setScene(animationScene);
                stage.show();
        };
        Button startButton=new Button("Start animation");
        startButton.setOnAction(startButtonHandler);

        sliderBox.getChildren().addAll(lengthSlider, lengthLabel, gravitySlider, gravityLabel, initialAngleSlider, initialAngleLabel, startButton);

        Group root = new Group(sliderBox);
        Scene scene = new Scene(root, 500, 300);
        stage.setScene(scene);
        stage.show();
    }
}
