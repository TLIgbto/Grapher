package grapher.ui;

import grapher.fc.Function;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class LeftPane extends JPanel implements MouseListener {

    private JList<String> list;
    private DefaultListModel<String> listModel;
    private JToolBar tool;
    JPanel left;
    private Grapher grapher;

    LeftPane(DefaultListModel<String> listModel, Grapher grapher) {
        this.grapher = grapher;
        this.listModel = listModel;
        setLayout(new BorderLayout());
        TabPan tab = new TabPan(grapher, listModel);
        add(tab);
        JPanel panel = new JPanel(new BorderLayout());
        this.tool = new JToolBar(JToolBar.HORIZONTAL);
        setToolBar(tool);
        panel.add(tool, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
    }

    public void setToolBar(JToolBar tool) {
        tool.setFloatable(false);
        tool.setPreferredSize(new Dimension(50, 24));
        //tool.setLayout();
        JButton plus = makeButton(new AddFunction(grapher, listModel), "Ajouter une nouvelle fonction", "+");
        JButton moins = makeButton(new RemoveFunction(grapher, listModel), "Supprimer une fonction", "-");
        tool.add(plus);
        tool.add(moins);
    }

    public JButton makeButton(AbstractAction action, String toolTipText, String altText) {
        JButton button = new JButton();
        button.setAction(action);
        button.setToolTipText(toolTipText);
        button.setText(altText);
        tool.add(button);
        return button;
    }

    public void highLightGraph(Function f) {
        Graphics2D g = (Graphics2D) grapher.getGraphics();
        final int N = (grapher.W / grapher.STEP) + 1;
        double[] xs = new double[N];
        int Xs[] = new int[N];
        int Ys[] = new int[N];
        for (int i = 0; i < N; i++) {
            double x = grapher.xmin + i * grapher.dx(grapher.STEP);
            xs[i] = x;
            Xs[i] = grapher.X(x);
            Ys[i] = grapher.Y(f.y(xs[i]));
        }
        g.setStroke(new BasicStroke(1.5f));
        g.drawPolyline(Xs, Ys, N);
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        int index = list.locationToIndex(e.getPoint());
        String item = list.getModel().getElementAt(index);
        for (Function fo : grapher.functions) {
            if (fo.toString().compareTo(item) == 0) {
                highLightGraph(fo);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

    }

}
