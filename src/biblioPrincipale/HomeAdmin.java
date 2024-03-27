package biblioPrincipale;
import javax.swing.*;
import java.awt.*;

public class HomeAdmin extends JFrame {
    private JLabel welcomeLabel;
    private JButton logoutButton;

    public HomeAdmin() {
        setTitle("Page d'accueil Administrateur");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Création de la barre de navigation
        JMenuBar menuBar = new JMenuBar();

        //chemmin du logo
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/styleImage/logo.png"));

        // Redimensionner l'image 
        Image image = originalIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon logoIcon = new ImageIcon(image);

        // Création d'un JMenuItem avec le logo
        JMenuItem logoMenuItem = new JMenuItem(logoIcon);

        // Ajout du logo à la barre de navigation
        menuBar.add(logoMenuItem);
        
        // Ajout des autres éléments du menu à la barre de navigation
        JMenu listeUserMenu = new JMenu("Liste des users");
        JMenu catalogueAdminMenu = new JMenu("Catalogue");

        // Ajout des actions aux éléments du menu
        listeUserMenu.addActionListener(e -> redirectToListeUsers());
        catalogueAdminMenu.addActionListener(e -> redirectTocatalogueAdmin());

        // Ajout des éléments du menu à la barre de navigation
        menuBar.add(listeUserMenu);
        menuBar.add(catalogueAdminMenu);

        // Ajout de la barre de navigation à la fenêtre
        setJMenuBar(menuBar);

        welcomeLabel = new JLabel("Bienvenue, Administrateur !");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeLabel, BorderLayout.CENTER);

        logoutButton = new JButton("Déconnexion");
        logoutButton.addActionListener(e -> {
            dispose(); // Fermer la fenêtre de l'administrateur
            SwingUtilities.invokeLater(() -> new biblioSession.LoginPage()); // Ouvrir la page de connexion
        });
        add(logoutButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new HomeAdmin();
    }

    // Méthode pour rediriger vers la page de la liste des users
    private void redirectToListeUsers() {
        dispose(); // Fermer la fenêtre actuelle
        SwingUtilities.invokeLater(() -> new biblioPrincipale.biblio_Gestion_Admin.ListeUsers()); // Ouvrir la page de la liste des users
    }

    // Méthode pour rediriger vers la page des favoris
    private void redirectTocatalogueAdmin() {
        dispose(); // Fermer la fenêtre actuelle
        SwingUtilities.invokeLater(() -> new biblioPrincipale.biblio_Gestion_Admin.CatalogueAdmin()); // Ouvrir la page du catalogue des admins
    }
}