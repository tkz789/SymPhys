package symphys.symphys.fields.graphics;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MainPane extends Pane {
    private Set<MainPaneBody> bodies = new HashSet<>();
    private Set<DrawnBody> drawnBodies = new HashSet<>();
    private final Rectangle clip;
    public boolean allowBodyCropping = false;

    MainPane () {
        clip = new Rectangle(getWidth(), getHeight());
        setClip(clip);
        widthProperty().addListener((obs, oldVal, newVal) -> {clip.setWidth(newVal.doubleValue());});
        heightProperty().addListener((obs, oldVal, newVal) -> {clip.setHeight(newVal.doubleValue());});
    }

    static class DrawnBody extends Circle {
        MainPaneBody mpb;

        DrawnBody(MainPaneBody mainPaneBody) {
            super(mainPaneBody.getX(), mainPaneBody.getY(), 10, Color.BLACK);
            mpb = mainPaneBody;
            update();
        }

        void update() {
            setCenterX(mpb.getX());
            setCenterY(mpb.getY());
        }
    }

    void addBody(MainPaneBody mainPaneBody) {
        bodies.add(mainPaneBody);
        DrawnBody d = new DrawnBody(mainPaneBody);
        getChildren().add(d);
        drawnBodies.add(d);
    }

    void removeBody(MainPaneBody mainPaneBody) {
        bodies.remove(mainPaneBody);
        getChildren().removeIf((Node n) -> {if (!(n instanceof DrawnBody)) return false; return ((DrawnBody)n).mpb==mainPaneBody;});
    }

    void setBodies(Collection<MainPaneBody> collection) {
        getChildren().removeIf((n) -> n instanceof DrawnBody);
        bodies = new HashSet<>(collection);
        bodies.addAll(collection);
    }
}
