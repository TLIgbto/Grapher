/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grapher.ui;

import grapher.fc.Function;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

class ColorChooserEditor extends AbstractCellEditor implements TableCellEditor {

    private JButton delegate = new JButton();
    private TabPan tab;
    private int row;

    Color savedColor;

    public ColorChooserEditor(TabPan tab) {
        this.tab = tab;
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Color color = JColorChooser.showDialog(delegate, "Color Chooser", savedColor);
                ColorChooserEditor.this.changeColor(color);
                highLight();
            }
        };
        delegate.addActionListener(actionListener);
    }

    public void highLight() {
        tab.g.highLightGraph((Function) tab.getValueAt(row, 0), savedColor);
    }

    public Object getCellEditorValue() {
        return savedColor;
    }

    private void changeColor(Color color) {
        if (color != null) {
            savedColor = color;
            delegate.setBackground(color);
        }
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
            int row, int column) {
        changeColor((Color) value);
        this.row = row;
        return delegate;
    }
}
