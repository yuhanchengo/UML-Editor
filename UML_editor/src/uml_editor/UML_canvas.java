package uml_editor;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import Diagram_components.Association_line;
import Diagram_components.Basic_object;
import Diagram_components.Composite;
import Diagram_components.Composition_line;
import Diagram_components.Diagram_components;
import Diagram_components.Generalization_line;
import Mode.Mode;

/*
 * This is the Canvas of the UML editor
 */
public class UML_canvas extends Canvas implements MouseListener, MouseMotionListener {
	public static Mode currentMode;
	// canvas中點擊的座標點
	private Point clicked_position;
	public static ArrayList<Diagram_components> objects = new ArrayList<Diagram_components>();
//	public static ArrayList<Basic_object> objects = new ArrayList<Basic_object>();
//	private ArrayList<Line_object> lines = new ArrayList<Line_object>();
	private final int canvas_width = 600;    //860
	private final int canvas_length = 700;   //710
	public static int objDepth = 99;
	public static Basic_object selected_object;

	UML_canvas() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(canvas_width, canvas_length));
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	/*
	 * 1. add the new object to a list 2. call repaint() 3. In paint(Graphics)
	 * draw the list of objects
	 */


	public void paint(Graphics g) {
		
		for(Diagram_components dc: objects){
			dc.draw(g);
			
		}
	
		
	}
//	public void drawObjects(Graphics g, Basic_object bo, int obj_port_size, boolean select){
//		bo.updatePorts();
//		bo.draw(g);
//		bo.drawClassLine(g);
//		/*
//		 * if the object has a name, then show its name
//		 */
//		if (bo.name != null) {
//			g.setFont(new Font("TimesRoman", Font.PLAIN, 16));
//			g.drawString(bo.name, bo.x_cord + (bo.object_width - bo.name.length() * 7) / 2,
//					bo.y_cord + bo.object_height / bo.namePosRatio);
//		}
//		if(select){
//			for (Point coord : bo.port_cords) {
//				showConnectionPort(g, coord.x, coord.y, obj_port_size);
//			}
//		}
//		
//	}
	/*
	 * show connection ports of an object
	 */
	public void showConnectionPort(Graphics g, int coord_x, int coord_y, int obj_port_size){
		g.setColor(Color.black);
		g.fillRect(coord_x, coord_y, obj_port_size, obj_port_size);
		g.setColor(Color.black);
		g.drawRect(coord_x, coord_y, obj_port_size, obj_port_size);
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		currentMode.mousePressed(e);
		repaint();
//		 System.out.println("mouse pressed");
//		if (e.getButton() == MouseEvent.BUTTON1) {
//			clicked_position = e.getPoint();
//			if (UML_editor.mode == "SELECT") {
//				// for single selection
//				Collections.reverse(objects); // sort from shallow to depth
//				boolean objClicked = false; // if any object is clicked
//				for (Basic_object bo : objects) {
//					if (bo.contains(clicked_position)) {
//						if (bo instanceof Composite) {
////							System.out.println("composite  object clicked");
//							selectionMode = 3;
//						} else {
////							System.out.println("single object pressed");
//							selectionMode = 1;
//						}
//						unselectObjects(); // unselect other objects
//						bo.select = true;
//						selected_object = bo;
//						objClicked = true;
//						break;
//					}
//				}
//				Collections.reverse(objects);
//				// if no object is clicked and was singleSelection
//				if (selectionMode == 1 && !objClicked) {
//					selectionMode = 0;
//					unselectObjects();
//				} else if (!objClicked) { // for group selection or none selection
////					System.out.println("group selection");
//					selectionMode = 2;
//					src_port = (Point) clicked_position.clone();
//				}
//				repaint();
//			} else if (click_count >= 3) { // control # objects can add
//				JOptionPane.showMessageDialog(this.getParent(), "You can at most add three objects once");
//			}
//		}

	}

//	public void unselectObjects() {
//		// System.out.println("unselect objects");
//		for (Basic_object bo : objects) {
//			selected_object = null;
//			bo.select = false;
//		}
//	}

	@Override
	public void mouseReleased(MouseEvent e) {
		currentMode.mouseReleased(e);
		repaint();
		
		// mouse release action when mode is compos, assoc or gener
//		if (UML_editor.mode == "COMPOS" || UML_editor.mode == "ASSOC" || UML_editor.mode == "GENER") {
//			clicked_position = e.getPoint();
//			Collections.reverse(objects);
//			for (Basic_object bo : objects) {
//				// check if the destination point is in "another" basic_object
//				if (bo.contains(clicked_position) && !bo.equals(src_obj)) {
//					checkPort(bo); // check which port to show
//					des_port = (Point) showPort.clone();
//					break;
//				}
//			}
//			Collections.reverse(objects);
//			// if source and destination ports are both assigned, then the
//			// connection_line will appear
//			if (src_port.x != -1 && src_port.y != -1 && des_port.x != -1 && des_port.y != -1) {
//				switch (UML_editor.mode) {
//				case "COMPOS":
//					addLine(new Composition_line((Point) src_port.clone(), (Point) des_port.clone()));
//					break;
//				case "ASSOC":
//					addLine(new Association_line((Point) src_port.clone(), (Point) des_port.clone()));
//					break;
//				case "GENER":
//					addLine(new Generalization_line((Point) src_port.clone(), (Point) des_port.clone()));
//					break;
//				default:
//				}
//				addPort((Point) src_port.clone());
//				addPort((Point) des_port.clone());
//				src_port.x = src_port.y = des_port.x = des_port.y = -1;
//			} else {
//				showPort.x = showPort.y = -1;
//			}
//			repaint();
//			// not single selection case
//		} else if (UML_editor.mode == "SELECT" && selectionMode == 2) {
//			clicked_position = e.getPoint();
//			des_port = (Point) clicked_position.clone();
//			int start_x = src_port.x, start_y = src_port.y;
//			if (src_port.x > des_port.x && src_port.y > des_port.y) {
//				start_x = des_port.x;
//				start_y = des_port.y;
//			}
//			Shape dragScope = new Rectangle(start_x, start_y, Math.abs(des_port.x - src_port.x),
//					Math.abs(des_port.y - src_port.y));
//			try {
//				int num_selected = 0;
//				for (Basic_object bo : objects) {
//					if (dragScope.contains(bo.x_cord, bo.y_cord)
//							&& dragScope.contains(bo.x_cord + bo.object_width, bo.y_cord + bo.object_height)) {
//						bo.select = true;
//						num_selected++;
//					}
//				}
//
//				if (num_selected == 1) { // only one object selected so is same
//											// as single selection
//					selectionMode = 1;
//				} else if (num_selected == 0) { // if no object included in the
//												// scope
//					unselectObjects();
//					selectionMode = 0;
//				}
//
//				drawDragScope(this.getGraphics(), start_x, start_y);
//			} catch (InterruptedException ie) {
//				ie.getStackTrace();
//			}
//
//		}
	}

	
	
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	private void addObject(Basic_object obj) {
		objects.add(obj);
		repaint();
	}
	// mouseMotionListener
	@Override
	public void mouseDragged(MouseEvent e) {
		
//		if(UML_editor.mode == "SELECT" && selectionMode == 1){
//			selected_object.x_cord += (e.getX()-selected_object.x_cord);
//			selected_object.y_cord += (e.getY()-selected_object.y_cord);
//			repaint();
//		}else if(UML_editor.mode == "SELECT" && selectionMode == 3){
//			int move_x = e.getX()-selected_object.x_cord;
//			int move_y = e.getY()-selected_object.y_cord;
//			MoveComposite(selected_object, move_x, move_y);
//			repaint();
//			
//		}
		
	}
//	public void MoveComposite(Basic_object bo, int move_x, int move_y){
//		bo.x_cord += move_x;
//		bo.y_cord += move_y;
//		
//		if(!(bo instanceof Composite)){
//			bo.x_cord += move_x;
//			bo.y_cord += move_y;
//			return;
//		}
//		for(Basic_object o: bo.compos_objects){
//			MoveComposite(o, move_x, move_y);
//		}
//	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}
//	public void drawDragScope(Graphics g, int start_x, int start_y) throws InterruptedException {
//		g.setColor(Color.blue);
//		g.drawRect(start_x, start_y, Math.abs(des_port.x - src_port.x), Math.abs(des_port.y - src_port.y));
//		Thread.sleep(500); // let the scope show for 0.5 sec
//		repaint();
//	}



}