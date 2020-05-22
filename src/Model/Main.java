package Model;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.application.Platform.exit;


public class Main extends Application {


    Pane root;
    Tile[][] tiles;
    Text turnText;
    Text warningText;
    Play play;
    int x1, y1, x2, y2;
    int count = 0;

    public static void main(String[] args) {
        launch(args);

    }

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(800, 600);

        Button button = new Button("start");
        button.setTranslateX(50);
        button.setLayoutY(50);
        root.getChildren().add(button);

        warningText = new Text("warning");
        turnText = new Text("yours turn");
        play = new Play();

        button.setOnMouseClicked(mouseEvent -> {
            init(root);
            startGame();

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


        turnText.setFont(Font.font(15));
        turnText.setTranslateX(580);
        turnText.setTranslateY(35);
        root.getChildren().add(turnText);


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

    private void startGame() {
        play.rand();

        showTurn();
    }

    void showTurn() {
        if (play.turnB)
            turnText.setText("B turn");
        else
            turnText.setText("A turn");
        play.showState();
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

            boarder.setStroke(Color.BLACK);

            text = new Text();
            getChildren().addAll(boarder, text);
            setOnMouseClicked(event -> played(i, j));

        }

        void setText(String s) {
            text.setText(s);
        }

        void played(int i, int j) {

            if ((i % 2 == 1 || j % 2 == 1) && (i % 2 == 0 || j % 2 == 0)) {
                count++;
                if (count == 1) {
                    x1 = i;
                    y1 = j;
                    warningText.setText("pick second wall");
                } else {
                    x2 = i;
                    y2 = j;
                }

                System.out.println(i + ", " + j);

                String warn = "";
                if (count == 2) {

                    warningText.setText("pick ");
                    warn = play.puttingWall(play.turnA, x1, y1, x2, y2);
                    warningText.setText("pick 1");
                    if (warn.equals("invalidWall") || warn.equals("wallExists") || warn.equals("noWalls")) {
                        warningText.setText(warn);
                        count = 0;
                    } else if (warn.equals("ok")) {
                        warningText.setText("wall placed");

                        System.out.println(x1 + ", " + y1);
                        tiles[x1][y1].boarder.setFill(Color.BLACK);
                        System.out.println(x2 + ", " + y2);
                        tiles[x2][y2].boarder.setFill(Color.BLACK);
                        count = 0;
                    }
                    System.out.println(play.turnA);
                    System.out.println(play.turnB);

                    showTurn();
                }
            } else if ((i % 2 == 0 && j % 2 == 0)) {

                int x, y;
                char c;
                if (play.turnA) {
                    x = play.game.posA.x;
                    y = play.game.posA.y;
                    c = 'A';
                } else {
                    x = play.game.posB.x;
                    y = play.game.posB.y;
                    c = 'B';
                }
                String war = play.move(play.turnA, i, j);
                if (war.equals("ok")) {

                    tiles[x][y].text.setText("");
                    tiles[i][j].text.setText(c + "");
                    if (play.goalState()) {
                        if (play.turnA) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("PLAYER 'A' IS THE WINNER!");
                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("PLAYER B' IS THE WINNER!");
                            alert.showAndWait();
                            exit();
                        }
                    } else {
                        play.updateTurn();
                        showTurn();
                    }

                } else if (war.equals("false")) {
                    warningText.setText("invalid Cell, have another go!");
                }
            }

        }
    }
}
