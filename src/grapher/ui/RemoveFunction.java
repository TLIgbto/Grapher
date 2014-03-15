package grapher.ui;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public class RemoveFunction extends AbstractAction {

    private Grapher grapher;
    private LeftPane l;
    
    public RemoveFunction(LeftPane l) {
        // TODO Auto-generated constructor stub
        this.grapher = l.grapher;
        this.l = l;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object expression = JOptionPane.showInputDialog(grapher,
                "Choisir fonction à supprimer",
                "Suppression fonction",
                JOptionPane.QUESTION_MESSAGE,
                null,
                grapher.functions.toArray(), grapher.functions.get(0));
        grapher.remove(expression.toString());
        l.refresh();
    }
}
