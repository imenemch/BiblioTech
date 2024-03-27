package biblioSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBdd {
    private final String url ="jdbc:mysql://localhost/bibliotech";
    private final String user = "root";
    private final String password = "";

    // Méthode pour établir une connexion à la base de données
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Méthode pour vérifier si un utilisateur existe dans la base de données
    public boolean checkUser(String email, String password) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
        ) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); //il retourne vrai si les identifs sont bons
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
 // Obtenir le rôle de l'utilisateur en fonction de l'email
    public String getUserRole(String email) {
        String role = null;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bibliotech", "root", "");
             PreparedStatement stmt = conn.prepareStatement("SELECT role FROM users WHERE email = ?")) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    role = rs.getString("role");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return role;
    }
    
    public static void main(String[] args) {
        // Exemple d'utilisation de la méthode checkUser
        UserBdd userBddInstance = new UserBdd();
        String email = "test@example.com";
        String password = "motdepasse";

        boolean userExists = userBddInstance.checkUser(email, password);
        if (userExists) {
            System.out.println("Utilisateur trouvé dans la base de données !");
        } else {
            System.out.println("Utilisateur non trouvé dans la base de données.");
        }
    }
}