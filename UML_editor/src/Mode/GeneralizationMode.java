package Mode;

import java.awt.event.MouseEvent;
import java.util.Collections;
import Diagram_components.Diagram_components;
import Diagram_components.Generalization_line;
import uml_editor.UML_canvas;

public class GeneralizationMode extends Mode{
	
	public GeneralizationMode(){
		super();
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
		if(e.getButton()==MouseEvent.BUTTON1){
			clicked_position = e.getPoint();
			Collections.reverse(UML_canvas.objects); // first search for objects with smaller depth
			// check if clicked point is inside any object on the canvas
			for (Diagram_components obj : UML_canvas.objects) {
				if (obj.contains(clicked_position)) {
					src_obj = obj; // for release to check if the des object is same as src object
					src_port = obj.getPort(clicked_position);
					src_port_prevState = src_port.getPortState();
					src_port.showPort(true);
					System.out.println("source port: " + src_port);
					break;
				}
			}
			Collections.reverse(UML_canvas.objects);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		clicked_position = e.getPoint();
		Collections.reverse(UML_canvas.objects);
		// check if the destination point is in "another" basic_object
		for(Diagram_components obj: UML_canvas.objects){
			if(obj.contains(clicked_position) && !obj.equals(src_obj)){
				des_port = obj.getPort(clicked_position);
				System.out.println("des port: " + des_port);
				break;
			}
		}
		Collections.reverse(UML_canvas.objects);
		if(src_port!=null && des_port!=null){
			addObjects(new Generalization_line(src_port, des_port));
			System.out.println("Generalization line added");
			des_port.showPort(true);
		}else if(src_port!=null){
			src_port.showPort(src_port_prevState);
		}
		src_port=des_port=null;
		
		
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
