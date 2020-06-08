package Model;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.application.Platform.exit;

public class Main extends Application {
    Tile[][] tiles;
    Text turnText;
    Text warningText;
    Text wallsNum;
    PlayWithFriend playWithFriend;
    PlayWithAI playWithAI;
    int x1, y1, x2, y2;
    int count = 0;
    Button friendButton;
    Button AIButton;
    Boolean playingWithAI = false;
    boolean a = true;


    public static void main(String[] args) {
        launch(args);
    }

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(800, 600);
        root.setBackground(new Background(new BackgroundFill(Color.web("#EEDEC0"), CornerRadii.EMPTY, Insets.EMPTY)));

        friendButton = new Button("PLAY WITH A FRIEND");
        friendButton.setPrefHeight(35);
        friendButton.setPrefWidth(140);
        friendButton.setTranslateX(330);
        friendButton.setTranslateY(230);
        root.getChildren().add(friendButton);

        AIButton = new Button("PLAY WITH AI");
        AIButton.setPrefHeight(35);
        AIButton.setPrefWidth(140);
        AIButton.setTranslateX(330);
        AIButton.setTranslateY(270);
        root.getChildren().add(AIButton);

        warningText = new Text("");
        turnText = new Text("your`s turn");
        wallsNum = new Text("number of walls");


        friendButton.setOnMouseClicked(mouseEvent -> {
            playWithFriend = new PlayWithFriend();
            init(root);
            startGame();

        });

        AIButton.setOnMouseClicked(mouseEvent -> {
            playWithAI = new PlayWithAI();
            playingWithAI = true;
            init(root);
            startGame();

        });
        return root;
    }

    void init(Pane root) {
        root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
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
                tile.setTranslateX(20 + x);
                tile.setTranslateY(35 + y);
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
        warningText.setTranslateY(125);
        root.getChildren().add(warningText);

        wallsNum.setFont(Font.font(15));
        wallsNum.setTranslateX(580);
        wallsNum.setTranslateY(80);
        root.getChildren().add(wallsNum);

        tiles[0][8].setText("A");
        tiles[16][8].setText("B");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Quoridor");
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.setResizable(false);
        primaryStage.show();
        if (!a) {
            System.out.println("AAAAAAAAAAAAAAAAAAAAAa");
        }
    }

    private void startGame() {
        if (playingWithAI) {
            playWithAI.rand();
            showTurn();
            if (playWithAI.turnA) {
                AITurn();
            }
        } else {
            playWithFriend.rand();
            showTurn();
        }
    }

    void showTurn() {
        if (playingWithAI) {
            if (playWithAI.turnB) {
                turnText.setText("It's B Turn");
                wallsNum.setText("Remaining Walls: " + playWithAI.wallB);
            } else {
                turnText.setText("It's AI Turn");
                wallsNum.setText("Remaining Walls: " + playWithAI.wallA);
            }
            // show board state?

        } else {
            if (playWithFriend.turnB) {
                turnText.setText("It's B Turn");
                wallsNum.setText("Remaining Walls: " + playWithFriend.wallB);
            } else {
                turnText.setText("It's A Turn");
                wallsNum.setText("Remaining Walls: " + playWithFriend.wallA);
            }
            playWithFriend.showBoardState();
        }
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

            setOnMouseClicked(event -> {
                if (!playingWithAI){
//                    warningText.setText("AAAAAAAAAAAa");
                    friendPlayed(i, j);

                }
                else
                    AIPlayed(i, j);
            });
        }

        void setText(String s) {
            text.setText(s);
        }
    }

    void friendPlayed(int i, int j) {
        if ((i % 2 == 1 || j % 2 == 1) && (i % 2 == 0 || j % 2 == 0)) {
            count++;
            if (count == 1) {
                x1 = i;
                y1 = j;
                warningText.setText("Pick Second Wall");
            } else {
                x2 = i;
                y2 = j;
            }

            //System.out.println(i + ", " + j);
            String warn = "";

            if (count == 2) {

                //warningText.setText("pick ");
                warn = playWithFriend.puttingWall(x1, y1, x2, y2);
                //warningText.setText("pick 1");
                if (warn.equals("invalidWall") || warn.equals("wallExists") || warn.equals("noWalls,only move")) {
                    warningText.setText(warn);
                    count = 0;
                } else if (warn.equals("ok")) {
                    warningText.setText("Wall Placed");
                    //System.out.println(x1 + ", " + y1);
                    tiles[x1][y1].boarder.setFill(Color.BLACK);
                    //System.out.println(x2 + ", " + y2);
                    tiles[x2][y2].boarder.setFill(Color.BLACK);
                    count = 0;
                }
                showTurn();
            }
        } else if ((i % 2 == 0 && j % 2 == 0)) {
            int x, y;
            char c;
            if (playWithFriend.turnA) {
                x = playWithFriend.game.posA.x;
                y = playWithFriend.game.posA.y;
                c = 'A';
            } else {
                x = playWithFriend.game.posB.x;
                y = playWithFriend.game.posB.y;
                c = 'B';
            }
            String war = playWithFriend.move(i, j);
            if (war.equals("ok")) {
                tiles[x][y].text.setText("");
                tiles[i][j].text.setText(c + "");
                if (playWithFriend.goalState()) {
                    if (playWithFriend.turnA) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("PLAYER 'A' IS WINNER!");
                        alert.showAndWait();
                        exit();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("PLAYER 'B' IS WINNER!");
                        alert.showAndWait();
                        exit();
                    }
                } else {
                    playWithFriend.updateTurn();
                    showTurn();
                }
            } else if (war.equals("false")) {
                warningText.setText("invalid Cell, have another go!");
            }
        }
    }

    void AIPlayed(int i, int j) {

        if ((i % 2 == 1 || j % 2 == 1) && (i % 2 == 0 || j % 2 == 0) && playWithAI.turnB) {
            count++;
            if (count == 1) {
                x1 = i;
                y1 = j;
                warningText.setText("Pick Second Wall");
            } else {
                x2 = i;
                y2 = j;
            }
            String warn = "";

            if (count == 2) {

                warn = playWithAI.puttingWall(x1, y1, x2, y2);
                if (warn.equals("invalidWall") || warn.equals("wallExists") || warn.equals("noWalls,only move")) {
                    warningText.setText(warn);
                    count = 0;
                } else if (warn.equals("ok")) {
                    tiles[x1][y1].boarder.setFill(Color.BLACK);
                    tiles[x2][y2].boarder.setFill(Color.BLACK);
                    warningText.setText("Wall Placed");
                    count = 0;

                    playWithAI.updateTurn();
                    a = false;
                    AITurn();
                }
            }
        } else if ((i % 2 == 0 && j % 2 == 0) && playWithAI.turnB) {
            int x, y;
            char c;
            x = playWithAI.game.posB.x;
            y = playWithAI.game.posB.y;
            c = 'B';

            String war = playWithAI.move(i, j);
            if (war.equals("ok")) {
                tiles[x][y].text.setText("");
                tiles[i][j].text.setText(c + "");

                showWinner();

                playWithAI.updateTurn();
                AITurn();


            } else if (war.equals("false")) {
                warningText.setText("invalid Cell, have another go!");
            }
        }
    }

    void showWinner() {
        if (playWithAI.goalState()) {
            if (playWithAI.turnA) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("PLAYER 'A' IS WINNER!");
                alert.showAndWait();
                exit();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("PLAYER 'B' IS WINNER!");
                alert.showAndWait();
                exit();
            }
        }
    }

    void AITurn() {

        showTurn();
        warningText.setText("wait for your opponent");
        Object[] o = playWithAI.AITurn();
        if ((boolean) o[0]) {
            Point prev = (Point) o[1];
            Point now = (Point) o[2];
            tiles[prev.x][prev.y].setText("");
            tiles[now.x][now.y].setText("A");

            showWinner();

        } else {
            Point prev = (Point) o[1];
            Point now = (Point) o[2];
            tiles[prev.x][prev.y].boarder.setFill(Color.BLACK);
            tiles[now.x][now.y].boarder.setFill(Color.BLACK);
        }
        warningText.setText("");
        playWithAI.updateTurn();
        showTurn();
    }
}