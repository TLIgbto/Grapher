/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grapher.ui;

import javax.swing.JOptionPane;

/**
 *
 * @author toure
 */
public class ErrorPane extends JOptionPane {
    public ErrorPane(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}
