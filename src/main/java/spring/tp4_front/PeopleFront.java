package spring.tp4_front;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PeopleFront extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PeopleFront.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 350, 300);
        stage.setTitle("People Front");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}