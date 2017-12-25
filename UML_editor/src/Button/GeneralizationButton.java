package Button;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import Mode.GeneralizationMode;
import uml_editor.UML_canvas;
import uml_editor.UML_editor;

@SuppressWarnings("serial")
public class GeneralizationButton extends Button{
	private static final ImageIcon img = new ImageIcon("src/source/generalization.png");
	public GeneralizationButton(){
		super(img, "GENER");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		UML_editor.resetButtons();
		this.setBackground(Color.black);
		this.setForeground(Color.black);
		this.setOpaque(true);
		UML_canvas.currentMode = new GeneralizationMode();
	}
}
