package symphys.symphys.pendulum;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

public class SimplePendulumFactory extends PendulumFactory{

        Slider lengthSlider=new Slider(50, 275, 175);
        Slider gravitySlider=new Slider(0, 100, 9.81);
        Slider initialAngleSlider=new Slider(0, Math.PI/2, 1);
        Slider dampingSlider=new Slider(0, 0.1, 0);
        Label lengthLabel=new Label("Rod length: 175,00 cm");
        Label gravityLabel=new Label("Gravity: 9,81 m/s^2");
        Label initialAngleLabel=new Label("Initial angle: 1,00 rad");
        Label dampingLabel=new Label("No damping");

        public void showParameterChoice(VBox box){
            lengthSlider.valueProperty().addListener(
                    (observable, oldValue, newValue) -> lengthLabel.setText(String.format("Rod length: %.2f cm", newValue.doubleValue())));
            gravitySlider.valueProperty().addListener(
                    (observable, oldValue, newValue) -> gravityLabel.setText(String.format("Gravity: %.2f m/s^2", newValue.doubleValue())));
            initialAngleSlider.valueProperty().addListener(
                    (observable, oldValue, newValue) -> initialAngleLabel.setText(String.format("Initial angle: %.2f rad", newValue.doubleValue())));
            dampingSlider.valueProperty().addListener(
                    (observable, oldValue, newValue) -> {
                        if(newValue.doubleValue()==0)
                            dampingLabel.setText("No damping");
                        else
                            dampingLabel.setText(String.format("Dumping factor: %.3f", newValue.doubleValue()));
                    });
            box.getChildren().addAll(lengthSlider, lengthLabel, gravitySlider, gravityLabel, initialAngleSlider, initialAngleLabel, dampingSlider, dampingLabel);
        }

        @Override
        public SimplePendulum createPendulum() {
            return new SimplePendulum(new SimplePendulumCalculator(lengthSlider.getValue(), gravitySlider.getValue(), initialAngleSlider.getValue(), dampingSlider.getValue()), new SimplePendulumBody());
        }
}
