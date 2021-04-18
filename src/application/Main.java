package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("UI.fxml")));
        primaryStage.setTitle("Tamagotchi");
        primaryStage.setScene(new Scene(root, 420, 420));
        primaryStage.getScene().getStylesheets().clear();
        primaryStage.getScene().getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("style/style.css")).toExternalForm());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
