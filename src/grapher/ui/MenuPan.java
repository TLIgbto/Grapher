/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grapher.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author toure
 */
public class MenuPan extends JPanel implements ActionListener {

    Grapher grapher;
    JButton addB, remB;

    public MenuPan(Grapher grapher) {
        this.grapher = grapher;
        setLayout(new BorderLayout());
        JList list = new JList(grapher.functions);
        JPanel bPan = new JPanel();
        bPan.setLayout(new FlowLayout());

        addB = new JButton("+");
        remB = new JButton("-");

        bPan.add(addB);
        bPan.add(remB);

        add(list);
        add(bPan, BorderLayout.SOUTH);
        addB.addActionListener(this);
        remB.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addB) {
            new JOptionPane().showMessageDialog(
                    null,
                    "",
                    "Ajout nouvelle fonction", JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

}
