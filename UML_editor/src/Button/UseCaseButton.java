package Button;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import Mode.UseCaseMode;
import uml_editor.UML_canvas;
import uml_editor.UML_editor;

@SuppressWarnings("serial")
public class UseCaseButton extends Button{
	private static final ImageIcon img = new ImageIcon("src/source/use_case.png");
	public UseCaseButton(){
		super(img, "USE_CASE");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		UML_editor.resetButtons();
		this.setBackground(Color.black);
		this.setForeground(Color.black);
		this.setOpaque(true);
		UML_canvas.currentMode = new UseCaseMode();
		
	}
}
