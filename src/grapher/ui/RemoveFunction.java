package grapher.ui;

import grapher.fc.Function;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class RemoveFunction extends AbstractAction {

    private Grapher grapher;

    public RemoveFunction(LeftPane l) {
        // TODO Auto-generated constructor stub
        this.grapher = l.grapher;

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
        for (Function f : grapher.functions) {
            System.out.println(f);
        }
    }

}
