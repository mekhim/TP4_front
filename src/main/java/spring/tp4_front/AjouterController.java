package spring.tp4_front;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import spring.tp4_front.Model.People;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class AjouterController {

    private Stage stage;

    @FXML
    private TextField lastnameField;

    @FXML
    private TextField firstnameField;

    @FXML
    private TextField addressField;

    @FXML
    private void ajouter(ActionEvent actionEvent) throws IOException {
        if( !lastnameField.getText().equals("") &&
            !firstnameField.getText().equals("") &&
            !addressField.getText().equals("")) {

            People people = new People();
            people.setLastname(lastnameField.getText());
            people.setFirstname(firstnameField.getText());
            people.setAddress(addressField.getText());

            String json = People.people_to_JSON(people);

            System.out.println(json);

            URL url = new URL("http://localhost:8080/people");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
            osw.write(json);
            osw.flush();
            osw.close();
            os.close();

            int status = con.getResponseCode();

            con.disconnect();
        }
    }
    @FXML
    private void switchSceneRetour(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PeopleFront.class.getResource("menu-view.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 350, 300));
        stage.show();
    }
}
