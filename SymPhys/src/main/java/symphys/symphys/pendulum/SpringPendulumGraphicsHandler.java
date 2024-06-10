package symphys.symphys.pendulum;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpringPendulumGraphicsHandler extends PendulumGraphicsHandler{
    Line[] spring;
    Rectangle mass;
    int numCoils;
    SpringPendulumGraphicsHandler(PendulumModel model, int numCoils){
        super(model);
        this.numCoils = numCoils;
        spring = new Line[numCoils];
        for(int i=0; i<numCoils; i++){
            spring[i] = new Line();
        }
        mass = new Rectangle(0, 0,30, 30);
        mass.setFill(Color.CADETBLUE);
    }

    @Override
    public void drawSimulation() {
        int n = numCoils;
        mass.setX(model.bob.position.getX()+235);
        mass.setY(model.bob.position.getY());
        spring[0].setStartX(250); spring[0].setStartY(0); spring[0].setEndX(260); spring[0].setEndY(mass.getY()/n);
        spring[1].setStartX(260); spring[1].setStartY(mass.getY()/n); spring[1].setEndX(240); spring[1].setEndY(2*mass.getY()/n);
        for(int i=2;i<n-1;i++)
        {
            spring[i].setStartX(spring[i-1].getEndX()); spring[i].setStartY(spring[i-1].getEndY()); spring[i].setEndX(spring[i-1].getStartX()); spring[i].setEndY((i+1)*mass.getY()/n);
        }
        spring[n-1].setStartX(spring[n-2].getEndX()); spring[n-1].setStartY(spring[n-2].getEndY()); spring[n-1].setEndX(250); spring[n-1].setEndY(mass.getY());
    }

    @Override
    public List<Node> getObjects(){
        ArrayList<Node> objects = new ArrayList<>();
        objects.add(mass); objects.addAll(Arrays.asList(spring));
        return objects;
    }
}
