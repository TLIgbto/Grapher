/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grapher.ui;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author toure
 */
public class TableModel extends AbstractTableModel {

    Object donnees[][];

    public TableModel(Object donnees[][]) {
        this.donnees = donnees;
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return 2;
    }

    @Override
    public int getRowCount() {
        return donnees.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        return donnees[row][column];
    }

    @Override
    public Class getColumnClass(int column) {
        return (getValueAt(0, column).getClass());
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        donnees[row][column] = value;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return true;
    }
}
