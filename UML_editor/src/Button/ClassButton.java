package Button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

import Mode.ClassMode;
import uml_editor.UML_canvas;
import uml_editor.UML_editor;

public class ClassButton extends Button{
	private static final ImageIcon img = new ImageIcon(("source/class.png"));
	public ClassButton(){
		super("CLASS");
//		super(img, "CLASS");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Class pressed");
		UML_editor.resetButtons();
		this.setBackground(Color.black);
		this.setForeground(Color.black);
		this.setOpaque(true);
		UML_canvas.currentMode = new ClassMode();
		
		
	}
	
	
	
}
