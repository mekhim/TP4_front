package spring.tp4_front;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class SupprimerController {

    private Stage stage;

    @FXML
    private TextField suppressionField;

    @FXML
    private void supprimer(ActionEvent actionEvent) throws IOException {
        URL url = new URL("http://localhost:8080/people/"+ suppressionField.getText());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("DELETE");
        int status = con.getResponseCode();
        con.disconnect();
    }

    @FXML
    private void switchSceneRetour(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PeopleFront.class.getResource("menu-view.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 350, 300));
        stage.show();
    }

}
