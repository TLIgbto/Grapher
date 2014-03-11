package grapher.ui;

import grapher.fc.Function;
import grapher.fc.FunctionFactory;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;


public class LeftPane extends JPanel implements ActionListener, MouseListener {
	
	private JList<String> list;
	private ListModel<String> listModel;
	private JToolBar tool;
	JPanel left;
	private Grapher grapher;
	AddFunction addFunction;
	RemoveFunction removeFunction;
	
	LeftPane(DefaultListModel<String> listModel, Grapher grapher){
		addFunction = new AddFunction(grapher,listModel);
		removeFunction = new RemoveFunction(grapher,listModel);
		this.grapher=grapher;
		this.listModel=listModel;
		this.list = new JList<String>(listModel);
		setList(list);
		JPanel panel= new JPanel(new BorderLayout());
		this.tool = new JToolBar(JToolBar.HORIZONTAL);
		setToolBar(tool);
		panel.add(tool, BorderLayout.CENTER);
		left = new JPanel(new BorderLayout());
		left.add(list,BorderLayout.CENTER);
		left.add(panel,BorderLayout.SOUTH);
		
	}
	public JPanel getLeft(){
		return left;
	}
	public void setList(JList<String> list){
		
		list.setLayoutOrientation(JList.VERTICAL);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addMouseListener(this);
		
	}
	public void setToolBar(JToolBar tool){
		tool.setFloatable(false);
		tool.setPreferredSize(new Dimension(50,24));
		//tool.setLayout();
		JButton plus = makeButton(addFunction,"Ajouter une nouvelle fonction","+");
		JButton moins = makeButton(removeFunction,"Supprimer une fonction","-");
		tool.add(plus);tool.add(moins);
	}
	public JButton makeButton(AbstractAction action, String toolTipText, String altText){
		JButton button = new JButton();
		button.setAction(action);
		button.setToolTipText(toolTipText);
		button.setText(altText);
		button.addActionListener(this);
		tool.add(button);
		return button;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int index = list.locationToIndex(e.getPoint());
		String item = list.getModel().getElementAt(index);
		for(Function fo: grapher.functions){
			if(fo.toString().compareTo(item)==0){
				grapher.drawFunction((Graphics2D) grapher.getGraphics(),fo,true);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
