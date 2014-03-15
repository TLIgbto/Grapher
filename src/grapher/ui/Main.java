package grapher.ui;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;

public class Main extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    Main(String title, String[] expressions) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Grapher grapher = new Grapher();
        for (String expression : expressions) {
            grapher.add(expression);
        }
        JSplitPane spanel = new JSplitPane(1, new LeftPane(grapher), grapher);
        add(spanel);
        setJMenuBar(new MenuBar(new LeftPane(grapher)));
        pack();
    }

    public static void main(String[] argv) {
        final String[] expressions = argv;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main("grapher", expressions).setVisible(true);
            }
        });
    }
}
