/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grapher.ui;

import java.awt.Color;
import javax.swing.JTable;

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
            donnee[i][0] = g.functions.get(i);
            donnee[i][1] = Color.BLACK;
        }
        TableModel model = new TableModel(donnee);
        setModel(model);
        getColumnModel().getColumn(1).setCellEditor(new ColorChooserEditor(this));
        getColumnModel().getColumn(1).setCellRenderer(new MyCellRenderer());
    }
}
