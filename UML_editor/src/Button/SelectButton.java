package Button;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import Mode.SelectMode;
import uml_editor.UML_canvas;
import uml_editor.UML_editor;

public class SelectButton extends Button{
	private static final ImageIcon img = new ImageIcon("source/select.png");
	public SelectButton(){
		super("SELECT");
//		super(img, "SELECT");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Select Pressed");
		UML_editor.resetButtons();
		this.setBackground(Color.black);
		this.setForeground(Color.black);
		this.setOpaque(true);
		UML_canvas.currentMode = new SelectMode();
		
	}
}
