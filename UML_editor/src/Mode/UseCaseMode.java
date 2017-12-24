package Mode;

import java.awt.event.MouseEvent;

import Diagram_components.Diagram_components;
import Diagram_components.Use_case;
import uml_editor.UML_canvas;

public class UseCaseMode extends Mode{
	private int click_count = 0;
	public UseCaseMode(){
		super();
		click_count = 0;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1){ // check if is left click
			clicked_position = e.getPoint();
			if(click_count < 3){
				Diagram_components comp = new Use_case(clicked_position.x, clicked_position.y);
				comp.depth = UML_canvas.objDepth;
				UML_canvas.objDepth--;
				addObjects(comp);
				click_count++;
			}
		}
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
