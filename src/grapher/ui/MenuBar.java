/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grapher.ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author toure
 */
public class MenuBar extends JMenuBar{
    public MenuBar(LeftPane l) {
        JMenu exp = new JMenu("Expression");
        JMenuItem add = new JMenuItem();
        add.setAction(new AddFunction(l));
        add.setText("Add ...");
        JMenuItem rem = new JMenuItem("Remove");
        rem.setAction(new RemoveFunction(l));
        rem.setText("Remove ...");
        exp.add(add);
        exp.add(rem);
        add(exp);
    }
}
