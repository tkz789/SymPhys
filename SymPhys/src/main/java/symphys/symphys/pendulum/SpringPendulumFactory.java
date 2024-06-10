package symphys.symphys.pendulum;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import symphys.symphys.GraphicsHandler;

import java.util.ArrayList;
import java.util.List;

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

    public List<Control> getControls(){
        lengthSlider.valueProperty().addListener(
                (observable, oldValue, newValue) -> lengthLabel.setText(String.format("Rod length: %.2f cm", newValue.doubleValue())));
        massSlider.valueProperty().addListener(
                (observable, oldValue, newValue) -> massLabel.setText(String.format("Mass: %.2f kg", newValue.doubleValue())));
        springSlider.valueProperty().addListener(
                (observable, oldValue, newValue) -> springLabel.setText(String.format("Spring constant: %.2f", newValue.doubleValue())));
        coilSlider.valueProperty().addListener(
                (observable, oldValue, newValue) -> coilLabel.setText(String.format("Coil count: %d", newValue.intValue())));
        ArrayList<Control> list = new ArrayList<>();
        list.add(lengthSlider); list.add(lengthLabel); list.add(massSlider); list.add(massLabel); list.add(springSlider);  list.add(springLabel); list.add(coilSlider); list.add(coilLabel);
        return list;
    }

    @Override
    public PendulumModel getModel() {
        return new PendulumModel(new SpringPendulumCalculator(lengthSlider.getValue(), massSlider.getValue(), springSlider.getValue()));
    }

    @Override
    public GraphicsHandler getGraphicsHandler(){
        return new SpringPendulumGraphicsHandler(getModel(), (int)coilSlider.getValue());
    }
}
