package biblioSession;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;

public class RegisterPage extends JFrame implements ActionListener {
    private JLabel labelNom, labelPrenom, labelEmail, labelPassword;
    private JTextField textNom, textPrenom, textEmail;
    private JPasswordField textPassword;
    private JButton buttonRegister;

    public RegisterPage() {
        setTitle("Inscription");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        labelNom = new JLabel("Nom :");
        add(labelNom);
        textNom = new JTextField();
        add(textNom);

        labelPrenom = new JLabel("Prénom :");
        add(labelPrenom);
        textPrenom = new JTextField();
        add(textPrenom);

        labelEmail = new JLabel("Email :");
        add(labelEmail);
        textEmail = new JTextField();
        add(textEmail);

        labelPassword = new JLabel("Mot de passe :");
        add(labelPassword);
        textPassword = new JPasswordField();
        add(textPassword);

        buttonRegister = new JButton("S'inscrire");
        buttonRegister.addActionListener(this);
        add(buttonRegister);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonRegister) {
            // Récupérer les valeurs des champs de saisie
            String nom = textNom.getText();
            String prenom = textPrenom.getText();
            String email = textEmail.getText();
            String password = new String(textPassword.getPassword());

            // Enregistrer l'utilisateur dans la base de données avec le rôle "lecteur"
            registerUser(nom, prenom, email, password);
        }
    }

    //  enregistrer le user dans la bdd avec le rôle "client"
    private void registerUser(String nom, String prenom, String email, String password) {
    	 // Hasher le mdp
        String hashedPassword = PswdHash.hashPassword(password);
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bibliotech", "root", "");
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (nom, prenom, email, password, role) VALUES (?, ?, ?, ?, 'lecteur')")) {
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setString(3, email);
            stmt.setString(4, hashedPassword);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Bravo champion(ne)", "Welcome to BiblioTech !", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Erreur le s", "Sorry", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur le s", "Sorry" , JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new RegisterPage();
    }
}