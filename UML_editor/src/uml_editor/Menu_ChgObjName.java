package uml_editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Menu_ChgObjName extends JMenuItem implements ActionListener{
	Menu_ChgObjName(){
		super("Change Object Name");
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("change object name");
//		JOptionPane chgNamePane = new JOptionPane();
//		chgNamePane.showConfirmDialog(null, "change or not", "Object Name alternation", JOptionPane.OK_CANCEL_OPTION);
		String rename = JOptionPane.showInputDialog(null, "Enter object new name: ");
		if(rename!=null){
			UML_canvas.selected_object.setName(rename);
		}
		repaint();
		UML_canvas.selected_object = null;
		
	}
	
}
