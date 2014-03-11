/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grapher.ui;

import javax.swing.DefaultListModel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author toure
 */
public class MenuBar extends JMenuBar{
    public MenuBar(Grapher g, DefaultListModel<String> listModel) {
        JMenu exp = new JMenu("Expression");
        JMenuItem add = new JMenuItem();
        add.setAction(new AddFunction(g, listModel));
        add.setText("Add ...");
        JMenuItem rem = new JMenuItem("Remove");
        rem.setAction(new RemoveFunction(g, listModel));
        rem.setText("Remove ...");
        exp.add(add);
        exp.add(rem);
        add(exp);
    }
}
