package symphys.symphys;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import symphys.symphys.fields.Main;

public class HelloController {
    @FXML
    private Label welcomeText;
    Stage stage;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void onTestClick() {
        Main.start(stage);
    }
}