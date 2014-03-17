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
import java.awt.event.KeyListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author toure
 */
public class FunctionEditor extends AbstractCellEditor implements TableCellEditor {

    private JTextField delegate = new JTextField("tan(x)");
    private TabPan tab;
    private int row;

    String savedText;

    FunctionEditor(TabPan aThis) {
        /*this.tab = tab;
        delegate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == 10) {
                    FunctionEditor.this.changeText("tan(x)");
                }
            }
        });*/
    }
    
    public void highLight() {
        tab.g.highLightGraph((Function) tab.getValueAt(row, 0), Color.BLACK);
    }
/*
    private void changeText(String text) {
        if (text != null) {
            delegate.setText(text);
        }
    }*/
    public Object getCellEditorValue() {
        return savedText;
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
            int row, int column) {
       
        //changeText(value.toString());
        this.row = row;
        
        //highLight();
        System.out.println(row);
        //delegate.setText(value.toString());
        return new JTextField("lol");
        
    }

}
