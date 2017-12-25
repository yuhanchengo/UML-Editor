package Button;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import Mode.CompositionMode;
import uml_editor.UML_canvas;
import uml_editor.UML_editor;

@SuppressWarnings("serial")
public class CompositionButton extends Button{
	private static final ImageIcon img = new ImageIcon("/Users/joy/GitHub/OO/project/UML_editor/src/source/composition.png");
	public CompositionButton(){
		super(img, "COMP");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		UML_editor.resetButtons();
		this.setBackground(Color.black);
		this.setForeground(Color.black);
		this.setOpaque(true);
		UML_canvas.currentMode = new CompositionMode();
		
	}
}
