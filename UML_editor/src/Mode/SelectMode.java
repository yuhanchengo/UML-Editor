package Mode;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Collections;

import Diagram_components.Diagram_components;
import uml_editor.UML_canvas;

public class SelectMode extends Mode {

	Diagram_components selected_obj = null;
	public SelectMode(){
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
		Collections.reverse(UML_canvas.objects); // search from objects with
													// smaller depth
		if (e.getButton() == MouseEvent.BUTTON1) {
			clicked_position = e.getPoint();
			selected_obj = null;
			// unselect other objects
			for (Diagram_components dc : UML_canvas.objects) {
				dc.selected(false);
			}
			// check if is clicked in any component
			for (Diagram_components dc : UML_canvas.objects) {
				if (dc.contains(clicked_position)) {
					selected_obj = dc;
					dc.selected(true); // show or ports
					break;
				}
			}
			
			Collections.reverse(UML_canvas.objects);
			

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
	public void drawDragScope(Graphics g, int start_x, int start_y) throws InterruptedException {
		g.setColor(Color.blue);
		g.drawRect(start_x, start_y, Math.abs(des_port.x - src_port.x), Math.abs(des_port.y - src_port.y));
		Thread.sleep(500); // let the scope show for 0.5 sec
	}
}
