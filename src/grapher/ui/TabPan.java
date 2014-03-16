/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grapher.ui;

import javax.swing.*;

/**
 *
 * @author toure
 */
public class TabPan extends JTable {

    Grapher g;

    public TabPan(Grapher g) {
        this.g = g;
        Object donnee[][] = new Object[g.functions.size()][2];
        for (int i = 0; i < g.functions.size(); i++) {
            donnee[i][0] = g.functions.get(i)[0];
            donnee[i][1] = g.functions.get(i)[1];
        }
        TableModel model = new TableModel(donnee);
        setModel(model);
        getColumnModel().getColumn(1).setCellEditor(new ColorChooserEditor(this));
        getColumnModel().getColumn(1).setCellRenderer(new MyCellRenderer());
    }
}
