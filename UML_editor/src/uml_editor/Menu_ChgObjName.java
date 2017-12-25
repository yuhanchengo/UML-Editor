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
		String rename = JOptionPane.showInputDialog(null, "Enter object new name: ");
		if(rename!=null){
			System.out.println(UML_canvas.selected_object);
			UML_canvas.selected_object.setName(rename);
		}
		repaint();
		UML_canvas.selected_object = null;
		
	}
	
}
