/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grapher.ui;

import grapher.fc.Function;
import java.awt.Color;
import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author toure
 */
public class FunctionEditor extends AbstractCellEditor implements TableCellEditor {

    private String savedText, oldText;
    private JTextField delegate = new JTextField();
    private TabPan tab;
    private int row;


    FunctionEditor(TabPan aThis) {
        this.tab = aThis;
        delegate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                savedText = delegate.getText();
                FunctionEditor.this.changeText(savedText);
            }
        });
    }

    public void highLight() {
        for(Object[] f : tab.g.functions) {
            if(f[0].toString().equals((tab.getValueAt(row, 0)).toString())) {
                tab.g.highLightGraph((Function) f[0], (Color) f[1]);
                break;
            }
        }
    }

    private void changeText(String text) {
        if (text != null) {
            savedText = text;
        }
    }

    public Object getCellEditorValue() {        
        tab.g.modify(oldText, savedText);
        tab.l.refresh();
        return savedText;
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
            int row, int column) {
        this.row = row;
        highLight();
        oldText = (oldText == null || !oldText.equals(value.toString())) ? value.toString() : oldText;
        savedText = value.toString();        
        delegate.setText(value.toString());
        return delegate;
    }
}
