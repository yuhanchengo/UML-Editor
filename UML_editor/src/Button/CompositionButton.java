package Button;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import Mode.CompositionMode;
import uml_editor.UML_canvas;
import uml_editor.UML_editor;

public class CompositionButton extends Button{
	private static final ImageIcon img = new ImageIcon("source/composition.png");
	public CompositionButton(){
		super("COMP");
//		super(img, "COMP");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Composition Pressed");
		UML_editor.resetButtons();
		this.setBackground(Color.black);
		this.setForeground(Color.black);
		this.setOpaque(true);
		UML_canvas.currentMode = new CompositionMode();
		
	}
}
