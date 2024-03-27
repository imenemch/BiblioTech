package biblio_Gestion_Fonctions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class Recherche_Livre_By_Title {
    public static DefaultTableModel rechercherLivresParTitre(String titre) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Titre");
        model.addColumn("Genre");
        model.addColumn("Référence");
        model.addColumn("Disponibilité");
        model.addColumn("Date de publication");
        model.addColumn("Nombre de copies");
        model.addColumn("Auteur");

        // Connexion à la base de données et récupération des données
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotech", "root", "")) {
            String query = "SELECT livres.id_livre, livres.titre, livres.genre, livres.ref, livres.disponibilité, livres.date_pub, livres.nb_copie, auteurs.nom, auteurs.prenom " +
                           "FROM livres " +
                           "JOIN auteurs ON livres.id_auteur = auteurs.id_auteur " +
                           "WHERE livres.titre LIKE ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, "%" + titre + "%");
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    model.addRow(new Object[]{
                            resultSet.getInt("id_livre"),
                            resultSet.getString("titre"),
                            resultSet.getString("genre"),
                            resultSet.getString("ref"),
                            resultSet.getBoolean("disponibilité") ? "Disponible" : "Non disponible",
                            resultSet.getTimestamp("date_pub"),
                            resultSet.getInt("nb_copie"),
                            resultSet.getString("nom") + " " + resultSet.getString("prenom")
                    });
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return model;
    }
}