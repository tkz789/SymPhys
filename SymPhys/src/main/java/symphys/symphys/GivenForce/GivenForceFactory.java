package symphys.symphys.GivenForce;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import symphys.symphys.SimulationFactory;
import symphys.symphys.SimulationModel;

import javafx.scene.control.Control;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GivenForceFactory implements SimulationFactory {
    Label Force = new Label("Enter parameters to start.\n F_x =  a + bx + cy \n F_y = d + ex + fy");
    String [] strings = {"a", "b", "c", "d", "e", "f", "x", "y", "v_x", "v_y", "mass"};
//    TextField textFieldA = new TextField("0.0");
//    TextField textFieldB = new TextField("-0.000002");
//    TextField textFieldC = new TextField("0.0");
//    TextField textFieldD = new TextField("0.0");
//    TextField textFieldE = new TextField("0.0");
//    TextField textFieldF = new TextField("-0.000002");
//    TextField textFieldVx = new TextField("0.1");
//    TextField textFieldVy = new TextField("0.0");
//    TextField textFieldX = new TextField("0.0");
//    TextField textFieldY = new TextField("150.0");
    HashMap<String,Label> labels;
    {
        for (String i : strings){
            labels.put(i, new Label(i));
        }
    }
    HashMap<String,Slider> sliders;
    {
        for (String i: strings){
            sliders.put(i, new Slider(-1, 1, 0));
        }
    }
    List<Control> list = new ArrayList<>();
    {
        list.add(Force);
        for (String i: strings){
            list.add(labels.get(i));
            list.add(labels.get(i));
        }
    }
    @Override
    public GivenForceModel getModel() {
        HashMap<String, Double> parameters = new HashMap<>();
        for (String i: strings){
            parameters.put(i, sliders.get(i).getValue());
        }
        return new GivenForceModel(parameters);
    }

    @Override
    public GivenForceGraphicsHandler getGraphicsHandler() {
        return new GivenForceGraphicsHandler(getModel());
    }

    @Override
    public List<Control> getControls() {
        return list;
    }
}
