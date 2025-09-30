package klase;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/TODO.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 350);
        stage.setScene(scene);
        stage.setTitle("TODO APP");
        stage.setMinWidth(600);
        stage.setMinHeight(350);
        stage.setMaxHeight(400);
        stage.setMaxWidth(600);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
