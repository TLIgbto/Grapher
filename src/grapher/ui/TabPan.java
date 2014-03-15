/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grapher.ui;

import javax.swing.DefaultListModel;
import javax.swing.JColorChooser;
import javax.swing.JTable;

/**
 *
 * @author toure
 */
public class TabPan extends JTable {

    public TabPan(Grapher g) {
        Object donnee[][] = new Object[g.functions.size()][2];
        for (int i = 0; i < g.functions.size(); i++) {
            donnee[i][0] = g.functions.get(i);
            donnee[i][1] = new JColorChooser();
        }
        TableModel model = new TableModel(donnee);
        setModel(model);
    }
}
