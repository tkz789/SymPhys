package symphys.symphys.fields.graphics;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import symphys.symphys.fields.sim.Simulation;
import symphys.symphys.fields.vm.GSimState;

public class GraphicsHandler {
    static Stage stage;
    static BorderPane mainPane;
    static MainCanvas mainCanvas;
    static LeftPane leftPane;
    static Scene mainScene;
    static Pane centerPane;
    static RightPane rightPane;

    /*public GraphicsHandler(Stage stage) {
        this.stage = stage;
        initStage();
    }*/

    public static void init(Stage stage) {
        GraphicsHandler.stage = stage;
        initStage();
    }

    private static void initStage() {
        stage.setTitle("SymPhys - Fields");
        mainPane = new BorderPane();
        leftPane = new LeftPane();
        mainPane.setLeft(leftPane);
        rightPane = new RightPane();
        mainPane.setRight(rightPane);
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

    public static void drawSimulation(GSimState simulation) {
        mainCanvas.drawSimulation(simulation);
    }
}
