package Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {


    Pane root;
    Tile[][] tiles;

    private Parent createContent() {


        Pane root = new Pane();
        root.setPrefSize(800, 600);

        Button button = new Button("start");
        button.setTranslateX(50);
        button.setLayoutY(50);
        root.getChildren().add(button);
        button.setOnMouseClicked(mouseEvent -> {
            init(root);
        });


        return root;
    }

    void init(Pane root) {

        tiles = new Tile[17][17];
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                tiles[i][j] = new Tile(i, j);
            }
        }
        int x = 0, y = 0;
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {

                Tile tile = tiles[i][j];
                tile.setTranslateX(x);
                tile.setTranslateY(y);
                root.getChildren().add(tile);
                if (j % 2 == 0)
                    x += 50;
                else
                    x += 10;
            }
            x = 0;
            if (i % 2 == 0)
                y += 50;
            else
                y += 10;
        }

        Text turnText = new Text("yours turn");
        turnText.setFont(Font.font(15));
        turnText.setTranslateX(580);
        turnText.setTranslateY(35);
        root.getChildren().add(turnText);

        Text warningText = new Text("warning");
        warningText.setFont(Font.font(15));
        warningText.setTranslateX(580);
        warningText.setTranslateY(80);
        root.getChildren().add(warningText);

        tiles[0][8].setText("A");
        tiles[16][8].setText("B");

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Quoridor");
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    private class Tile extends StackPane {
        Rectangle boarder;
        Text text;

        public Tile(int i, int j) {
            if (i % 2 == 1 && j % 2 == 1) {
                boarder = new Rectangle(10, 10);
                boarder.setFill(Color.gray(0.3));
            } else if (i % 2 == 1) {
                boarder = new Rectangle(50, 10);
                boarder.setFill(Color.gray(0.7));
            } else if (j % 2 == 1) {
                boarder = new Rectangle(10, 50);
                boarder.setFill(Color.gray(0.7));
            } else {
                boarder = new Rectangle(50, 50);
                boarder.setFill(Color.web("D86868"));
            }

            //boarder.setFill(null);
            boarder.setStroke(Color.BLACK);

            text = new Text();
            //text.setFont();
            //text.setText("F");
            getChildren().addAll(boarder, text);
            setOnMouseClicked(event -> played(i, j));

        }
        void setText(String s) {
            text.setText(s);
        }

        void played(int i, int j) {
            System.out.println(i + ", " + j);
            boarder.setFill(Color.BLACK);
            //tiles[4][4].text.setText("A");
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
