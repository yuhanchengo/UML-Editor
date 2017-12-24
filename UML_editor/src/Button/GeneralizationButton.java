package Button;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import Mode.GeneralizationMode;
import uml_editor.UML_canvas;
import uml_editor.UML_editor;

public class GeneralizationButton extends Button{
	private static final ImageIcon img = new ImageIcon("source/generalization.png");
	public GeneralizationButton(){
		super("GENER");
//		super(img, "GENER");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Generalization Pressed");
		UML_editor.resetButtons();
		this.setBackground(Color.black);
		this.setForeground(Color.black);
		this.setOpaque(true);
		UML_canvas.currentMode = new GeneralizationMode();
		
	}
}
