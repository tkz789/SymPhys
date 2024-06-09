package symphys.symphys.fields.graphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import symphys.symphys.fields.sim.BodyState;
import symphys.symphys.fields.sim.Simulation;
import symphys.symphys.fields.vm.GBody;
import symphys.symphys.fields.vm.GSimState;

class MainCanvas extends Canvas {
    GraphicsContext context;

    MainCanvas() {
        super();
        context = getGraphicsContext2D();
    }

    private void fillCircle(double x, double y, double r) {
        context.fillOval(x-r, y-r, 2*r, 2*r);
    }

    void drawSimulation(GSimState gSimState) {
        context.setFill(Color.WHITE);
        context.fillRect(0, 0, getWidth(), getHeight());
        context.setFill(Color.BLACK);
        for (GBody gbody: gSimState.gBodies) {
            fillCircle(15*gbody.get_position().getX()+getWidth()/2, -15*gbody.get_position().getY()+getHeight()/2, 10);
        }
    }
}
