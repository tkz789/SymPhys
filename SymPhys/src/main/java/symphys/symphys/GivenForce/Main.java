package symphys.symphys.GivenForce;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import symphys.symphys.numerical.Wektor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;

public class Main {
    public static void start(Stage stage) {
        Group group = new Group();


        Label Force = new Label("Enter parameters to start.\n F_x =  a + bx + cy \n F_y = d + ex + fy");



        Label labelA = new Label("a:");
        Label labelB = new Label("b:");
        Label labelC = new Label("c:");
        Label labelD = new Label("d:");
        Label labelE = new Label("e:");
        Label labelF = new Label("f:");
        Label labelVx = new Label("v_x:");
        Label labelVy = new Label("v_y:");
        Label labelX = new Label("x:");
        Label labelY = new Label("y:");


        TextField textFieldA = new TextField("0.0");
        TextField textFieldB = new TextField("-0.000002");
        TextField textFieldC = new TextField("0.0");
        TextField textFieldD = new TextField("0.0");
        TextField textFieldE = new TextField("0.0");
        TextField textFieldF = new TextField("-0.000002");
        TextField textFieldVx = new TextField("0.1");
        TextField textFieldVy = new TextField("0.0");
        TextField textFieldX = new TextField("0.0");
        TextField textFieldY = new TextField("150.0");


        Button calculateButton = new Button("Calculate");
        Button returnButton = new Button("Return");



        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        gridPane.add(Force, 1,0);
        gridPane.add(labelA, 0, 1);
        gridPane.add(textFieldA, 1, 1);
        gridPane.add(labelB, 0, 2);
        gridPane.add(textFieldB, 1, 2);
        gridPane.add(labelC, 0, 3);
        gridPane.add(textFieldC, 1, 3);
        gridPane.add(labelD, 0, 4);
        gridPane.add(textFieldD, 1, 4);
        gridPane.add(labelE, 0, 5);
        gridPane.add(textFieldE, 1, 5);
        gridPane.add(labelF, 0, 6);
        gridPane.add(textFieldF, 1, 6);
        gridPane.add(labelVx, 0, 7);
        gridPane.add(textFieldVx, 1, 7);
        gridPane.add(labelVy, 0, 8);
        gridPane.add(textFieldVy, 1, 8);
        gridPane.add(labelX, 0, 9);
        gridPane.add(textFieldX, 1, 9);
        gridPane.add(labelY, 0, 10);
        gridPane.add(textFieldY, 1, 10);

        gridPane.add(calculateButton, 0, 11, 2, 1);

        Scene scene = new Scene(gridPane, 300, 600);
        stage.setScene(scene);
        stage.show();
        calculateButton.setOnAction(e -> {
            double a = Double.parseDouble(textFieldA.getText());
            double b = Double.parseDouble(textFieldB.getText());
            double c = Double.parseDouble(textFieldC.getText());
            double d = Double.parseDouble(textFieldD.getText());
            double E = Double.parseDouble(textFieldE.getText());
            double f = Double.parseDouble(textFieldF.getText());
            double vx = Double.parseDouble(textFieldVx.getText());
            double vy = Double.parseDouble(textFieldVy.getText());
            double x = Double.parseDouble(textFieldX.getText());
            double y = Double.parseDouble(textFieldY.getText());


            Circle circle = new Circle(200, 200, 20);
            group.getChildren().add(circle);
            group.getChildren().add(returnButton);
            Scene scene2 = new Scene(group, 400, 400);
            stage.setScene(scene2);
            stage.show();
//            stage.setResizable(false);
            AnimationTimer animation = new AnimationTimer() {
                final PositionCalculator positionCalculator = new PositionCalculator(
//                    state -> new Wektor(-state.position.getX()*0.00001 - state.velocity.getX()*0.01,-state.position.getY()*0.00001 - state.velocity.getY()*0.01),
//                        state -> new Wektor(-state.position.getX()*0.000002 ,-state.position.getY()*0.000002),
                        state -> new Wektor(a + b * state.position.getX() + c * state.position.getY(),
                                d + E * state.position.getX() + f * state.position.getY()),
                        new Wektor(vx, vy),
                        new Wektor(x, y),
                        0,
                        1);

                @Override
                public void handle(long l) {
                    Wektor newPosition = positionCalculator.getNextPosition(l);
                    circle.setCenterX(200 + newPosition.getX());
                    circle.setCenterY(200 + newPosition.getY());
                }
            };
            animation.start();

        });
        returnButton.setOnAction(e -> {
            stage.setScene(scene);
            stage.show();
        });


    }
}
