package Mode;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Diagram_components.Diagram_components;
import Diagram_components.Port;
import uml_editor.UML_canvas;

public class Mode implements MouseListener, MouseMotionListener{
	Point clicked_position;
	Port src_port = null;
	Port des_port = null;
	boolean src_port_prevState = false; // src_port 前個狀態，是show or not
	Diagram_components src_obj;
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
		// TODO Auto-generated method stub
		
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
	protected void addObjects(Diagram_components s){
		UML_canvas.objects.add(s);
	}

}
