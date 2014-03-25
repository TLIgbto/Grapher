package grapher.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Méthode pour l'ajout d'une fonction au grapher
 */
public class AddFunction extends AbstractAction {

    private Grapher grapher;
    private LeftPane l;

    public AddFunction(LeftPane l) {
        this.l = l;
        this.grapher = l.grapher;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String expression = JOptionPane.showInputDialog("Nouvelle Expression :");
            if (!expression.isEmpty()) {
                grapher.add(expression);
                // Ici de la grosse merde !!
                l.refresh();
            }
        } catch (Exception ex) {
        }
    }
}
