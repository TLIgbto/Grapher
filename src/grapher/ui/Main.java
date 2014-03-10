package grapher.ui;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

public class Main extends JFrame {

    Main(String title, String[] expressions) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Grapher grapher = new Grapher();
        for (String expression : expressions) {
            grapher.add(expression);
        }
        MenuPan panel = new MenuPan(grapher);
        JSplitPane split = new JSplitPane(1, panel, grapher);
        add(split);
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
