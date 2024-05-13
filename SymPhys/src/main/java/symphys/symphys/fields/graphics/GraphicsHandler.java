package symphys.symphys.fields.graphics;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import symphys.symphys.fields.sim.Simulation;

public class GraphicsHandler {
    Stage stage;
    BorderPane mainPane;
    MainCanvas mainCanvas;
    LeftPane leftPane;
    Scene mainScene;
    Pane centerPane;

    public GraphicsHandler(Stage stage) {
        this.stage = stage;
        initStage();
    }

    private void initStage() {
        stage.setTitle("SymPhys - Fields");
        mainPane = new BorderPane();
        leftPane = new LeftPane();
        mainPane.setLeft(leftPane);
        mainCanvas = new MainCanvas();
        centerPane = new Pane(mainCanvas);
        mainPane.setCenter(centerPane);
        mainCanvas.widthProperty().bind(centerPane.widthProperty());
        mainCanvas.heightProperty().bind(centerPane.heightProperty());
        mainScene = new Scene(mainPane, 1200, 800);
        stage.setScene(mainScene);
        // mainCanvas.fillAquamarine();
        stage.show();
    }

    public void drawSimulation(Simulation simulation) {
        mainCanvas.drawSimulation(simulation);
    }
}
