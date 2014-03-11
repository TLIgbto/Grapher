package grapher.ui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class RemoveFunction extends AbstractAction {
	
	private Grapher grapher;
	private DefaultListModel<String> expressions;
	public RemoveFunction(Grapher g, DefaultListModel<String> listModel) {
		// TODO Auto-generated constructor stub
		this.grapher=g;
		this.expressions=listModel;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String expression = (String) JOptionPane.showInputDialog(grapher, 
	        "Choisir fonction a supprimer?",
	        "Supprimer fonction",
	        JOptionPane.QUESTION_MESSAGE, 
	        null, 
	        expressions.toArray(), expressions.get(0));
		grapher.functions.removeElement(expression);
		expressions.removeElement(expression);
		grapher.repaint();
	}

}
