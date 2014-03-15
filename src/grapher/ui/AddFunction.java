package grapher.ui;

import grapher.fc.FunctionFactory;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
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
        String expression = JOptionPane.showInputDialog("Nouvelle Expression :");
        grapher.add(expression);
        JFrame f = new JFrame();
        f.setContentPane(new LeftPane(grapher));
        f.setSize(100, 100);
        f.setVisible(true);
    }
}
