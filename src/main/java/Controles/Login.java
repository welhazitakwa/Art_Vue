package Controles;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.utilisateur.UtilisateurService;

public class Login {
    @FXML
    private AnchorPane contentArea;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField mdpTextField;
    @FXML
    private Label labelError;

    @FXML
    void seConnecter(ActionEvent event) {
        UtilisateurService user1 = new UtilisateurService();
        int validLogin = 5;
        try {
            validLogin = user1.validateLogin(loginTextField.getText(), mdpTextField.getText());
          //  System.out.println(loginTextField.getText());
          //  System.out.println(mdpTextField.getText());
        } catch (SQLException e) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText(e.getMessage());
            alert2.show();
        }
        if (validLogin == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("c'est un admin");
            alert.show();
        } else if (validLogin == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("c'est un artiste");
            alert.show();
        }else if (validLogin == 2) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("c'est un client");
            alert.show();

        } else {
            labelError.setText("Merci de vérifier vos données !");
        }
    }
    @FXML
    void forgetBtn(ActionEvent event) {

    }

    @FXML
    void registerBtn(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Register.fxml"));
            Parent registerParent = loader.load();

            contentArea.getChildren().clear();  // Use clear() instead of removeAll()
            contentArea.getChildren().add(registerParent);
        } catch (IOException e) {
            e.printStackTrace();  // Handle the exception appropriately (log or show an error message)
        }

    }

    @FXML
    void initialize() {

    }

}
