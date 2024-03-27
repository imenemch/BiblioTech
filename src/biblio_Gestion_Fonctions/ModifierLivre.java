package biblio_Gestion_Fonctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifierLivre extends JFrame {
    private int idLivre;

    public ModifierLivre(int idLivre) {
        this.idLivre = idLivre;

        setTitle("Modifier Livre");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JLabel label = new JLabel("Modification pour le livre avec l'ID : " + idLivre);
        panel.add(label);

        JButton button = new JButton("Valider les modifications");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implémentez ici la logique de modification du livre dans la base de données
                // Vous pouvez utiliser idLivre pour identifier le livre à modifier
                JOptionPane.showMessageDialog(null, "Modifications validées pour le livre avec l'ID : " + idLivre);
                dispose(); // Ferme la fenêtre après validation des modifications
            }
        });
        panel.add(button);

        add(panel);
        setVisible(true);
    }
}