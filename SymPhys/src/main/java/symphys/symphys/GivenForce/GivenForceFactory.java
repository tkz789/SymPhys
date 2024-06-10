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
    Label Force = new Label("Enter parameters to start.\n F_x =  a + b*x + c*v_y \n F_y = d + e*v_x + f*y");
    String [] strings = {"a", "b", "c", "d", "e", "f", "x", "y", "v_x", "v_y", "mass"};
    HashMap<String,Label> labels = new HashMap<>();
    HashMap<String,Slider> sliders = new HashMap<>();
    {
        for (String i: strings){
            if (i.equals("mass")){
                sliders.put(i, new Slider(0.001, 10, 1));
                labels.put(i, new Label(i + " 1"));
                continue;
            }
            sliders.put(i, new Slider(-1, 1, 0));
            labels.put(i, new Label(i + " 0"));
        }
    }
    List<Control> list = new ArrayList<>();
    {
        list.add(Force);
        for (String i: strings){
            list.add(labels.get(i));
            list.add(sliders.get(i));
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
