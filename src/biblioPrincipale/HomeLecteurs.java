package biblioPrincipale;

import java.awt.BorderLayout;
import javax.swing.*;

public class HomeLecteurs extends JFrame {
    private JLabel welcomeLabel;
    private JButton logoutButton;

    public HomeLecteurs() {
        setTitle("Page d'accueil Lecteurs");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Création de la barre de navigation
        JMenuBar menuBar = new JMenuBar();

        // Ajout des éléments du menu à la barre de navigation
        JMenu accueilMenu = new JMenu("Accueil");
        JMenu favorisMenu = new JMenu("Mes favoris");
        JMenu catalogueMenuLecteur = new JMenu("Catalogue");
        JMenu empruntsMenu = new JMenu("Mes emprunts");

        // Modification des déclarations des éléments de menu
        JMenuItem accueilMenuItem = new JMenuItem("Accueil");
        JMenuItem favorisMenuItem = new JMenuItem("Mes favoris");
        JMenuItem catalogueMenuItem = new JMenuItem("Catalogue");
        JMenuItem empruntsMenuItem = new JMenuItem("Mes emprunts");

        // Ajout des actions aux éléments de menu
        accueilMenuItem.addActionListener(e -> redirectToAccueil());
        favorisMenuItem.addActionListener(e -> redirectToFavoris());
        catalogueMenuItem.addActionListener(e -> redirectTocatalogueLecteur());
        empruntsMenuItem.addActionListener(e -> redirectToempruntsMenu());

        // Ajout des éléments de menu à la barre de navigation
        accueilMenu.add(accueilMenuItem);
        favorisMenu.add(favorisMenuItem);
        catalogueMenuLecteur.add(catalogueMenuItem);
        empruntsMenu.add(empruntsMenuItem);

        menuBar.add(accueilMenu);
        menuBar.add(favorisMenu);
        menuBar.add(catalogueMenuLecteur);
        menuBar.add(empruntsMenu);

        // Ajout de la barre de navigation à la fenêtre
        setJMenuBar(menuBar);

        welcomeLabel = new JLabel("Bienvenue, lecteur !");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeLabel, BorderLayout.CENTER);

        logoutButton = new JButton("Déconnexion");
        logoutButton.addActionListener(e -> {
            dispose(); // Fermer la fenêtre de lecteurs
            SwingUtilities.invokeLater(() -> new biblioSession.LoginPage()); // rediriger vers la page connexion
        });
        add(logoutButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new HomeLecteurs();
    }

    // Méthodes de redirection
    private void redirectToAccueil() {
        // Implémentez la redirection vers la page d'accueil si nécessaire
    }

    private void redirectToFavoris() {
        // Implémentez la redirection vers la page des favoris si nécessaire
    }

    private void redirectTocatalogueLecteur() {
        dispose(); 
        SwingUtilities.invokeLater(() -> new biblio_Gestion_Lecteur.CatalogueLecteur()); // Ouvrir la page des catalogue des lecteurs
    }

    private void redirectToempruntsMenu() {
        // Implémentez la redirection vers la page des emprunts si nécessaire
    }
}
