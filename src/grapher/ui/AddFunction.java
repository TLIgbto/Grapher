package grapher.ui;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class AddFunction extends AbstractAction {
	
	private Grapher grapher;
	private DefaultListModel<String> expressions;
	public AddFunction(Grapher g, DefaultListModel<String> listModel) {
		// TODO Auto-generated constructor stub
		this.grapher=g;
		this.expressions=listModel;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//
		String expression=JOptionPane.showInputDialog("Nouvelle Expression :");
		grapher.add(expression);
		expressions.addElement(expression);
		
		//this.grapher.add(expression);
	}

}
