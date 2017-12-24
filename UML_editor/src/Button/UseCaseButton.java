package Button;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import Mode.UseCaseMode;
import uml_editor.UML_canvas;
import uml_editor.UML_editor;

public class UseCaseButton extends Button{
	private static final ImageIcon img = new ImageIcon("source/use_case.png");
	public UseCaseButton(){
		super("USECASE");
//		super(img, "USE_CASE");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("UseCase Pressed");
		UML_editor.resetButtons();
		this.setBackground(Color.black);
		this.setForeground(Color.black);
		this.setOpaque(true);
		UML_canvas.currentMode = new UseCaseMode();
		
	}
}
