package Controles;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Commentaire;
import models.OeuvreArt;
import services.commentaire.CommentaireService;

public class CommentairesOeuvre {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BtnToAccueil;

    @FXML
    private Button BtnToOeuvreArt;

    @FXML
    private VBox commentLayout;

    @FXML
    private Button idBTNCategorie;

    @FXML
    private Button idBTNCategorie1;
    private OeuvreArt oeuvreArt;


    @FXML
    private ImageView imageOuevre;
    public void initData(OeuvreArt oeuvreArt) {
        this.oeuvreArt = oeuvreArt;
        System.out.println("l'oeuuvredfdfdfdf : "+oeuvreArt);
        CommentaireService comment1 = new CommentaireService();
        ImageView imgProfile = new ImageView(new Image(oeuvreArt.getImage()));

         imageOuevre.setImage(imgProfile.getImage());
        try {
            List<Commentaire> comments = comment1.getCommentsByOeuvre(32) ;
            System.out.println(comments);
            for (int i=0; i<comments.size();i++) {
                System.out.println(comments.size());
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxml/fxmlClient/CommentaireItem.fxml"));
                HBox hbox = fxmlLoader.load();
                CommentaireItem commentaireItem = fxmlLoader.getController();
                commentaireItem.setData(comments.get(i));
                commentLayout.getChildren().add(hbox);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void initialize() {

//        CommentaireService comment1 = new CommentaireService();
//        //ImageView imgProfile = new ImageView(new Image(oeuvreArt.getImage()));
//
//       // imageOuevre.setImage(imgProfile.getImage());
//        try {
//            List<Commentaire> comments = comment1.getCommentsByOeuvre(32) ;
//            System.out.println(comments);
//            for (int i=0; i<comments.size();i++) {
//                System.out.println(comments.size());
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(getClass().getResource("/fxml/fxmlClient/CommentaireItem.fxml"));
//                HBox hbox = fxmlLoader.load();
//                CommentaireItem commentaireItem = fxmlLoader.getController();
//                commentaireItem.setData(comments.get(i));
//                commentLayout.getChildren().add(hbox);
//            }
//        }
//          catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

}
