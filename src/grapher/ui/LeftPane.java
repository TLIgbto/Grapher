package grapher.ui;

import javax.swing.*;
import java.awt.*;

public class LeftPane extends JPanel {

    private JToolBar tool;
    TabPan tab;
    Grapher grapher;

    LeftPane(Grapher grapher) {
        this.grapher = grapher;
        this.grapher.setLeftPane(this);
        setLayout(new BorderLayout());

        tab = new TabPan(this);
        add(tab);
        JPanel panel = new JPanel(new BorderLayout());
        this.tool = new JToolBar(JToolBar.HORIZONTAL);
        setToolBar(tool);
        panel.add(tool, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
    }
    /**
     * méthode pour paramétrer un JToolBar
     * @param tool JToolBar
     */
    public void setToolBar(JToolBar tool) {
        tool.setFloatable(false);
        tool.setPreferredSize(new Dimension(150, 24));
        //tool.setLayout();
        JButton plus = makeButton(new AddFunction(this), "Ajouter une nouvelle fonction", "+");
        JButton moins = makeButton(new RemoveFunction(this), "Supprimer une fonction", "-");
        tool.add(plus);
        tool.add(moins);
    }
    /**
     * fonction de fabrication de bouton
     * @param action AbstractionAction : une action
     * @param toolTipText String : information sur l'action du bouton
     * @param altText String : label du bouton
     * @return un bouton
     */
    public JButton makeButton(AbstractAction action, String toolTipText, String altText) {
        JButton button = new JButton();
        button.setAction(action);
        button.setToolTipText(toolTipText);
        button.setText(altText);
        setPreferredSize(new Dimension(100, 24));
        tool.add(button);
        return button;
    }

    public void refresh() {
        remove(tab);
        grapher.setLeftPane(new LeftPane(grapher));
        tab = grapher.getLeftPane().tab;
        add(tab);
        System.out.println("refresh");
        revalidate();
        repaint();
    }
}
