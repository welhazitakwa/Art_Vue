package services.panieroeuvre;
import models.OeuvreArt;
import models.panieroeuvre;
import utils.MyDataBase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import services.Panier.PanierService;
import services.oeuvreArt.OeuvreArtService;
import models.Panier;
public class PanieroeuvreService implements Ipanieroeuvre{
    private Connection connection;


    public PanieroeuvreService()
    {
        connection = MyDataBase.getInstance().getConnection();
    }


    @Override
    public void ajouterOeuvreAuPanier(int id_panier, int id_oeuvre, int quantite) throws SQLException {
        String sqlExistence = "SELECT COUNT(*) FROM panieroeuvre WHERE id_panier = ? AND id_oeuvre = ?";
        try (PreparedStatement existStatement = connection.prepareStatement(sqlExistence)) {
            existStatement.setInt(1, id_panier);
            existStatement.setInt(2, id_oeuvre);
            try (ResultSet resultSet = existStatement.executeQuery()) {
                resultSet.next();
                int count = resultSet.getInt(1);
                if (count > 0) {
                    System.out.println("L'oeuvre avec l'ID " + id_oeuvre + " existe déjà dans le panier.");
                    return; // Sortir de la méthode si l'oeuvre existe déjà dans le panier
                }
            }
        }
        String sql = "INSERT INTO panieroeuvre (id_panier, id_oeuvre, quantite) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1,id_panier);
            preparedStatement.setInt(2,id_oeuvre);
            preparedStatement.setInt(3, quantite);
            preparedStatement.executeUpdate();
        }

        }

   /* public void modifierQuantiteOeuvreDansPanier(int id , int id_panier, int id_oeuvre, int nouvelleQuantite) throws SQLException
    {
        if (id <= 0) {
            System.out.println("Le panieroeuvre doit avoir un ID valide pour être modifiée.");
            return;
        }
        String sql = "UPDATE panieroeuvre SET quantite = ? WHERE id = ? AND id_panier = ? AND id_oeuvre = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, nouvelleQuantite);
            preparedStatement.setInt(2, id_panier);
            preparedStatement.setInt(3, id_oeuvre);
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
            System.out.println("Quantité de l'oeuvre dans le panier modifiée avec succès !");
        }
    }*/
    public void modifierQuantiteOeuvreDansPanier(int id, int id_panier, int id_oeuvre, int nouvelleQuantite) throws SQLException {
        if (id <= 0) {
            System.out.println("Le panieroeuvre doit avoir un ID valide pour être modifiée.");
            return;
        }

        String sql = "UPDATE panieroeuvre SET quantite = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, nouvelleQuantite);
            preparedStatement.setInt(2, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Aucun panieroeuvre avec l'ID " + id + " trouvé.");
            } else {
                System.out.println("Quantité de l'oeuvre dans le panier modifiée avec succès !");
            }
        }
    }

    public void supprimerOeuvreDuPanier(int id_panier, int id_oeuvre) throws SQLException {

        String sql = "DELETE FROM panieroeuvre WHERE id_panier = ? AND id_oeuvre = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_panier);
            preparedStatement.setInt(2, id_oeuvre);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Aucune oeuvre avec l'ID " + id_oeuvre + " n'a été trouvée dans le panier." + id_panier);
            } else {
                System.out.println("L'oeuvre avec l'ID " + id_oeuvre + " a été supprimée du panier" + id_panier + "avec succès !");
            }
        }
    }

        // Méthode pour récupérer les oeuvres d'un panier
        public List<OeuvreArt> getOeuvresDuPanier(int id_panier) throws SQLException {
            List<OeuvreArt> oeuvresDuPanier = new ArrayList<>();
            String sql = "SELECT o.* FROM panieroeuvre po JOIN oeuvreart o ON po.id_oeuvre = o.idOeuvreArt WHERE po.id_panier = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id_panier);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        // Créer une instance d'OeuvreArt pour chaque résultat de la requête
                        OeuvreArt oeuvre = new OeuvreArt();
                        oeuvre.setId(resultSet.getInt("idOeuvreArt"));
                        oeuvre.setTitre(resultSet.getString("titre"));
                        // Ajouter l'oeuvre à la liste des oeuvres du panier
                        oeuvresDuPanier.add(oeuvre);
                    }
                }
            }
            return oeuvresDuPanier;
        }
    public float calculerMontantTotal(int id_panier) throws SQLException {
        float montantTotal = 0;

        // Requête SQL pour récupérer les détails des œuvres ajoutées au panier
        String sql = "SELECT o.prixVente, po.quantite " +
                "FROM panieroeuvre po " +
                "JOIN oeuvreart o ON po.id_oeuvre = o.idOeuvreArt " +
                "WHERE po.id_panier = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_panier);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    float prixOeuvre = resultSet.getFloat("prixVente");
                    int quantite = resultSet.getInt("quantite");
                    montantTotal += prixOeuvre * quantite;
                }
            }
        }

        return montantTotal;
    }
}


