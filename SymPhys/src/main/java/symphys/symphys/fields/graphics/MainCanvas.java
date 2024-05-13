package symphys.symphys.fields.graphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import symphys.symphys.fields.sim.BodyState;
import symphys.symphys.fields.sim.Simulation;

class MainCanvas extends Canvas {
    GraphicsContext context;

    MainCanvas() {
        super();
        context = getGraphicsContext2D();
    }

    private void fillCircle(double x, double y, double r) {
        context.fillOval(x-r, y-r, 2*r, 2*r);
    }

    void drawSimulation(Simulation simulation) {
        context.setFill(Color.WHITE);
        context.fillRect(0, 0, getWidth(), getHeight());
        context.setFill(Color.BLACK);
        for (BodyState state : simulation.states) {
            fillCircle(15*state.position.getX()+getWidth()/2, -15*state.position.getY()+getHeight()/2, 10);
        }
    }
}
