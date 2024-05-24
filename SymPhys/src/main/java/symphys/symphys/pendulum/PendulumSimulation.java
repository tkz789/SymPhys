package symphys.symphys.pendulum;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PendulumSimulation {
    static PendulumFactory factory;

    public static void startSimulation(Stage stage){
        VBox vbox=new VBox();
        vbox.setSpacing(30);
        Label pendulumChoice = new Label("Choose your pendulum!");
        Button simplePendulumButton = new Button("Simple pendulum"){
            @Override
            public void fire() {
                factory=new SimplePendulumFactory();
                animationSetup(stage);
            }
        };
        Button springPendulumButton = new Button("Spring pendulum"){
            @Override
            public void fire() {
                factory=new SpringPendulumFactory();
                animationSetup(stage);
            }
        };
        vbox.getChildren().addAll(pendulumChoice,  simplePendulumButton,springPendulumButton);

        Group choiceGroup = new Group(vbox);
        Scene choiceScene = new Scene(choiceGroup, 500, 300);
        stage.setScene(choiceScene);
        stage.show();
    }
    public static void animationSetup(Stage stage) {
        VBox box = new VBox();
        box.setSpacing(5);
        factory.showParameterChoice(box);
        Button backButton = new Button("Back"){
            @Override
            public void fire() {
                startSimulation(stage);
            }
        };
        Button startButton = new Button("Start animation"){
            @Override
            public void fire() {
                Pendulum pendulum = factory.createPendulum();
                PendulumAnimation animation = new PendulumAnimation(pendulum);
                Group animationRoot = new Group(pendulum.body.elements);
                animation.start();
                Button goBackButton = new Button("Back"){
                    @Override
                    public void fire() {
                        animation.stop();
                        animationSetup(stage);
                    }
                };
                Button pauseResumeButton = new Button("Pause"){
                    Slider rewindSlider=null;
                    @Override
                    public void fire() {
                        if(this.getText().equals("Pause"))
                        {
                            animation.stop();
                            rewindSlider=new Slider(0, (double) (animation.lastStop-animation.startTime)/100000000, (double) (animation.lastStop-animation.startTime)/100000000);
                            rewindSlider.valueProperty().addListener(
                                    (observable, oldValue, newValue)->
                                        pendulum.body.adjustPosition(pendulum.calculator.position(newValue.doubleValue()))
                            );
                            rewindSlider.setLayoutX(20);
                            rewindSlider.setLayoutY(20);
                            animationRoot.getChildren().add(rewindSlider);
                            this.setText("Resume");
                        }
                        else{
                            animation.start();
                            animationRoot.getChildren().remove(rewindSlider);
                            this.setText("Pause");
                        }
                    }
                };
                pauseResumeButton.setLayoutX(20); pauseResumeButton.setLayoutY(250);
                goBackButton.setLayoutX(20); goBackButton.setLayoutY(215);
                animationRoot.getChildren().addAll(pauseResumeButton, goBackButton);
                stage.setScene(new Scene(animationRoot, 500, 300));
                stage.show();
            }
        };
        box.getChildren().addAll(startButton, backButton);
        stage.setScene(new Scene(box, 500, 300));
        stage.show();
    }
}
