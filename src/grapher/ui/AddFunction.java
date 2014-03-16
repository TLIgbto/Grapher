package grapher.ui;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

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
