package Button;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import Mode.AssociationMode;
import uml_editor.UML_canvas;
import uml_editor.UML_editor;

public class AssociationButton extends Button{
	private static final ImageIcon img = new ImageIcon("source/association.png");
	public AssociationButton(){
		super("ASSOC");
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("ASSOCIATION PRESSED");
		
		UML_editor.resetButtons();
		this.setBackground(Color.black);
		this.setForeground(Color.black);
		this.setOpaque(true);
		UML_canvas.currentMode = new AssociationMode();
		
	}
}
