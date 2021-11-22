package spring.tp4_front;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    private Stage stage;

    @FXML
    private void switchSceneRechercher(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PeopleFront.class.getResource("rechercher-view.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 350, 300));
        stage.show();
    }

    @FXML
    private void switchSceneSupprimer(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PeopleFront.class.getResource("supprimer-view.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 350, 300));
        stage.show();
    }

    @FXML
    private void switchSceneAjouter(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PeopleFront.class.getResource("ajouter-view.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 350, 300));
        stage.show();
    }

    @FXML
    private void exit(ActionEvent actionEvent) throws IOException {
        Platform.exit();
        System.exit(0);
    }
}