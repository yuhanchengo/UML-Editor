package uml_editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class Menu_ungroup extends JMenuItem implements ActionListener{
	Menu_ungroup(){
		super("Ungroup");
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		UML_canvas.selected_object.ungroup();
	}
}
