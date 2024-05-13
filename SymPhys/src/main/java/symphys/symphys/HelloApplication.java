package symphys.symphys;

import javafx.application.Application;
import javafx.stage.Stage;
import symphys.symphys.Doppler.Animate;
import symphys.symphys.fields.Main;
import symphys.symphys.pendulum.PendulumSimulation;
import symphys.symphys.GivenForce.AnimationGivenForce;

import java.io.IOException;

public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 620, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
        stage.setTitle("SymPhys");
//        Animate.startSym1(stage);
//        AnimationGivenForce.start(stage);
//        Main.start(stage);
        PendulumSimulation.startSimulation(stage);
    }
}