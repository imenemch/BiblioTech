package biblio_Gestion_Fonctions;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;

public class SupprimerLivre extends JFrame {
    private JTextField idLivreField;

    public SupprimerLivre() {
        setTitle("Supprimer un livre");
        setSize(400, 150);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        idLivreField = new JTextField(20);
        inputPanel.add(new JLabel("ID du livre à supprimer:"));
        inputPanel.add(idLivreField);

        JButton deleteButton = new JButton("Supprimer");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer l'ID du livre à supprimer
                int idLivre = Integer.parseInt(idLivreField.getText());
                
                // Connexion à la base de données et suppression du livre
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotech", "root", "")) {
                    String query = "DELETE FROM livres WHERE id_livre = ?";
                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.setInt(1, idLivre);
                        int rowsDeleted = statement.executeUpdate();
                        if (rowsDeleted > 0) {
                            JOptionPane.showMessageDialog(SupprimerLivre.this, "Livre supprimé avec succès !");
                            dispose(); // Fermer la fenêtre après la suppression du livre
                        } else {
                            JOptionPane.showMessageDialog(SupprimerLivre.this, "Aucun livre trouvé avec cet ID.");
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SupprimerLivre.this, "Erreur lors de la suppression du livre !");
                }
            }
        });

        add(inputPanel, BorderLayout.CENTER);
        add(deleteButton, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SupprimerLivre supprimerLivre = new SupprimerLivre();
            supprimerLivre.setVisible(true);
        });
    }
}
