package symphys.symphys.pendulum;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

public class SpringPendulumFactory extends PendulumFactory {
    Slider lengthSlider=new Slider(50, 150, 100);
    Slider massSlider=new Slider(0, 100, 5);
    Slider springSlider=new Slider(0, 10, 1);
    Slider coilSlider=new Slider(3, 100, 40);
    Label lengthLabel=new Label("Spring length: 100,00 cm");
    Label massLabel=new Label("Mass: 5,00 kg");
    Label springLabel=new Label("Spring constant: 1,00");
    Label coilLabel=new Label("Coil count: 40");
    {
        coilSlider.setBlockIncrement(1);
    }

    public void showParameterChoice(VBox box){
        lengthSlider.valueProperty().addListener(
                (observable, oldValue, newValue) -> lengthLabel.setText(String.format("Rod length: %.2f cm", newValue.doubleValue())));
        massSlider.valueProperty().addListener(
                (observable, oldValue, newValue) -> massLabel.setText(String.format("Mass: %.2f kg", newValue.doubleValue())));
        springSlider.valueProperty().addListener(
                (observable, oldValue, newValue) -> springLabel.setText(String.format("Spring constant: %.2f", newValue.doubleValue())));
        coilSlider.valueProperty().addListener(
                (observable, oldValue, newValue) -> coilLabel.setText(String.format("Coil count: %d", newValue.intValue())));
        box.getChildren().addAll(lengthSlider, lengthLabel, massSlider, massLabel, springSlider, springLabel, coilSlider, coilLabel);
    }

    @Override
    public SpringPendulum createPendulum() {
        return new SpringPendulum(new SpringPendulumCalculator(lengthSlider.getValue(), massSlider.getValue(), springSlider.getValue()), new SpringPendulumBody((int)coilSlider.getValue()));
    }
}
