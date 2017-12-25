package Mode;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.util.Collections;

import Diagram_components.Diagram_components;
import uml_editor.UML_canvas;

public class SelectMode extends Mode {
	
	Point drag_src = null; // src point when dragged
	boolean singleSelection=true; // if it's singleSelection or not
	
	public SelectMode(){
		super();
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		if(UML_canvas.selected_object!=null){
			Diagram_components selected_obj = UML_canvas.selected_object;
			selected_obj.move((int)(e.getX()-selected_obj.x_cord), (int)(e.getY()-selected_obj.y_cord));
		}
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
		Collections.reverse(UML_canvas.objects); // search from objects with smaller depth
		if (e.getButton() == MouseEvent.BUTTON1) { // if is left clicked
			clicked_position = e.getPoint();
			UML_canvas.selected_object = null;
			// unselect other objects if is singleSelection
			if(singleSelection){
				for (Diagram_components dc : UML_canvas.objects) {
					dc.selected(false);
				}
			}
			// check if is clicked in any component
			for (Diagram_components dc : UML_canvas.objects) {
				if (dc.contains(clicked_position)) {
					UML_canvas.selected_object = dc;
					dc.selected(true); // show or ports
					break;
				}
			}
			Collections.reverse(UML_canvas.objects);
			// for multiple selection
			if(UML_canvas.selected_object==null){
				drag_src = clicked_position;
			}

		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		clicked_position = e.getPoint();
		boolean unselect = true; // if it's unselecting or not
		Shape dragScope;
		// 多重選取(正反)
		if(drag_src!=null){
			if(drag_src.x > clicked_position.x && drag_src.y>clicked_position.y){
				dragScope = new Rectangle(clicked_position.x, clicked_position.y, Math.abs(clicked_position.x - drag_src.x),
						Math.abs(clicked_position.y - drag_src.y));
			}else{
				dragScope = new Rectangle(drag_src.x, drag_src.y, Math.abs(clicked_position.x - drag_src.x),
						Math.abs(clicked_position.y - drag_src.y));
			}
			for(Diagram_components dc: UML_canvas.objects){
				if(dragScope.contains(dc.x_cord, dc.y_cord) && dragScope.contains(dc.x_cord+dc.object_width, dc.y_cord+dc.object_height)){
					dc.selected(true);
					unselect = false;
					singleSelection=false;
				}
			}
		}
		// unselect multiple objects
		if(!singleSelection && unselect){
			for(Diagram_components dc: UML_canvas.objects){
				dc.selected(false);
			}
			singleSelection=true;
		}
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
