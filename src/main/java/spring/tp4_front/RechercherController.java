package spring.tp4_front;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import spring.tp4_front.Model.People;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class RechercherController {

    private Stage stage;

    @FXML
    private Button modifierButton;

    @FXML
    private TextField rechercheField;

    @FXML
    private Label lastname = new Label();
    @FXML
    private TextField lastnameField;
    @FXML
    private Label firstname = new Label();
    @FXML
    private TextField firstnameField;

    @FXML
    private Label address = new Label();
    @FXML
    private TextField addressField;

    @FXML
    private void switchSceneRetour(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PeopleFront.class.getResource("menu-view.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 350, 300));
        stage.show();
    }

    @FXML
    private void rechercher(ActionEvent actionEvent) throws IOException {


        URL url = new URL("http://localhost:8080/people/"+ rechercheField.getText());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");

        int status = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();


        if(!content.toString().equals("")){

            People people = People.json_to_People(content.toString());

            lastnameField.setText(people.getLastname());
            lastname.setVisible(true);
            lastnameField.setVisible(true);

            firstnameField.setText(people.getFirstname());
            firstname.setVisible(true);
            firstnameField.setVisible(true);

            addressField.setText(people.getAddress());
            address.setVisible(true);
            addressField.setVisible(true);

            modifierButton.setVisible(true);
            modifierButton.setDisable(false);
        } else {
            lastname.setVisible(false);
            lastnameField.setVisible(false);

            firstname.setVisible(false);
            firstnameField.setVisible(false);

            address.setVisible(false);
            addressField.setVisible(false);

            modifierButton.setVisible(false);
            modifierButton.setDisable(true);
        }

        con.disconnect();

    }

    @FXML
    private void modifier(ActionEvent actionEvent) throws IOException {
        if(!rechercheField.getText().equals("") &&
                !lastnameField.getText().equals("") &&
                !firstnameField.getText().equals("") &&
                !addressField.getText().equals("")) {

            People people = new People(Integer.parseInt(rechercheField.getText()),
                    lastnameField.getText(),
                    firstnameField.getText(),
                    addressField.getText());

            String json = People.people_to_JSON(people);

            URL url = new URL("http://localhost:8080/people");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
            osw.write(json);
            osw.flush();
            osw.close();
            os.close();

            int status = con.getResponseCode();

            System.out.println(json);

            con.disconnect();
        }
    }



}
