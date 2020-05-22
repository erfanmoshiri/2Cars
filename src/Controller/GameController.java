package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class GameController {

    @FXML
    public AnchorPane board;

    @FXML
    public Button startButton;
    public Rectangle cell00;
    public Rectangle cell01;
    public Rectangle cell02;
    public Rectangle cell03;
    public Rectangle cell04;
    public Rectangle cell05;
    public Rectangle cell06;
    public Rectangle cell07;
    public Rectangle cell08;

    Rectangle[] rectangles;
    void init() {
        rectangles = new Rectangle[9];

            rectangles[1] = cell00;
    }



    @FXML AnchorPane row1;
    @FXML AnchorPane row2;
    @FXML AnchorPane row3;
    @FXML AnchorPane row4;
    @FXML AnchorPane row5;
    @FXML AnchorPane row6;
    @FXML AnchorPane row7;
    @FXML AnchorPane row8;
    @FXML AnchorPane row9;

    @FXML Rectangle cell10;



    public void startButtonPressed(ActionEvent event) {

        init();
        //rectangles[1].setVisible(false);
        rectangles[1].setFill(new Color(0, 1, 0, 1));

    }
}
