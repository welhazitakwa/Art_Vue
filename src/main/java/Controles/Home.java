package Controles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Home extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
         // FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/fxmlAdmin/CategoriePage.fxml"));
        //FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/fxmlAdmin/AdminDashboard.fxml"));
       // FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
       // FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/fxmlAdmin/PageExposition.fxml"));
       // FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/fxmlArtiste/PageVenteEnchere.fxml"));
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/fxmlClient/PageOffreEnchere.fxml"));

        try {
            Parent root = loader.load();
            primaryStage.setTitle("Ligin");
            //Scene scene = new Scene(root, 930,700);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
