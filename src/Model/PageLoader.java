package Model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PageLoader {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    private static final int WIDTH = 712;
    private static final int HEIGHT = 600;

    public static void initStage(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("Quoridor");
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setMaxHeight(HEIGHT);
        stage.setMaxWidth(WIDTH);
        //stage.getIcons().add(new Image(Paths.get("").toUri().toString()));
    }

    public void load(String URL) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(URL));
        stage.setScene(new Scene(root, WIDTH, HEIGHT));
        stage.show();
    }
    public void load(String URL, Object controller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(URL));
        fxmlLoader.setController(controller);
        fxmlLoader.load();
    }
}
