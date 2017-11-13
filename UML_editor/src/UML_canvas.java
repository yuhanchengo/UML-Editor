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

/*
 * This is the Canvas of the UML editor
 */
public class UML_canvas extends Canvas implements MouseListener, MouseMotionListener {
	// canvas中點擊的座標點
	private Point clicked_position;
	protected ArrayList<Basic_object> objects = new ArrayList<Basic_object>();
	private ArrayList<Connection_line> lines = new ArrayList<Connection_line>();
	private ArrayList<Point> ports = new ArrayList<Point>();
	private final int canvas_width = 860;
	private final int canvas_length = 710;
	private int objDepth = 99;
	public static int click_count = 0;
	public static Basic_object selected_object;
	public static int selectionMode = 0; // 0: none selected, 1:single
											// selection, 2:group selection,
											// 3:composite selection
	private Point showPort = new Point(-1, -1);
	private Point src_port, des_port = new Point(-1, -1);
	private Basic_object src_obj; // record the source object

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
	// @ Override
	// public void paintComponent(Graphics g){
	// int top = objects.size();
	// for (int i = 0; i < top; i++) {
	// Basic_object bo = objects.get(i);
	// bo.draw(g);
	// }
	// }

	public void paint(Graphics g) {
		int obj_size = objects.size();
		int line_size = lines.size();
		int port_size = ports.size();
		int obj_port_size = Basic_object.port_size;

		/*
		 * draw basic objects on the graph
		 */
		for (int i = 0; i < obj_size; i++) {
			Basic_object bo = objects.get(i);
			if(bo instanceof Composite){
				if(bo.select){
					drawComposite(g, bo, obj_port_size);
				}else{
					for(Basic_object o: bo.compos_objects){
						o.draw(g);
						o.drawClassLine(g);
					}
				}
			}else{
				bo.draw(g);
				bo.drawClassLine(g);
				if(bo.select){
					bo.updatePorts();
					for (Point coord : bo.port_cords) {
						showConnectionPort(g, coord.x, coord.y, obj_port_size);
					}
				}
			}
			/*
			 * show the port that is going to be connected
			 */
			if (!showPort.equals(new Point(-1, -1))) {
				// System.out.println("in showPort");
				showConnectionPort(g, showPort.x, showPort.y, obj_port_size);
				showPort.x = -1;
				showPort.y = -1;
			}
			/*
			 * if the object has a name, then show its name
			 */
			if (bo.name != null) {
				g.setFont(new Font("TimesRoman", Font.PLAIN, 16));
				g.drawString(bo.name, bo.x_cord + (bo.object_width - bo.name.length() * 7) / 2,
						bo.y_cord + bo.object_height / bo.namePosRatio);
			}

		}
		/*
		 * draw connection lines on the graph
		 */
		for (int i = 0; i < line_size; i++) {
			Connection_line line = lines.get(i);
			line.draw(g);
			line.drawAddsOn(g, obj_port_size);
		}

		/*
		 * draw ports for connection lines
		 */
		
		for (int i = 0; i < port_size; i++) {
			Point p = ports.get(i);
			showConnectionPort(g, p.x, p.y, obj_port_size);
			System.out.println(p.x+" " + p.y);
		}
	}
	public void drawComposite(Graphics g, Basic_object bo, int obj_port_size){
		if(!(bo instanceof Composite)){
			bo.updatePorts();
			bo.draw(g);
			bo.drawClassLine(g);
			for (Point coord : bo.port_cords) {
				showConnectionPort(g, coord.x, coord.y, obj_port_size);
			}
			return;
		}
		for(Basic_object o : bo.compos_objects){
			drawComposite(g,o,obj_port_size);
		}
	}
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
//		System.out.println("mouse clicked");
	}

	@Override
	public void mousePressed(MouseEvent e) {
//		 System.out.println("mouse pressed");
		
		if (e.getButton() == MouseEvent.BUTTON1) {
			clicked_position = e.getPoint();
			if (UML_editor.mode == "CLASS" && click_count < 3) {
				Basic_object obj = new Class((int) clicked_position.getX(), (int) clicked_position.getY());
				obj.depth = objDepth;
				objDepth--;
				addObject(obj);
				// sort objects with depth
				click_count++;
			} else if (UML_editor.mode == "USE_CASE" && click_count < 3) {
				Basic_object obj = new Use_case((int) clicked_position.getX(), (int) clicked_position.getY());
				obj.depth = objDepth;
				objDepth--;
				addObject(obj);
				// sort objects with depth
				click_count++;
			} else if (UML_editor.mode == "COMPOS" || UML_editor.mode == "ASSOC" || UML_editor.mode == "GENER") {
				// check if clicked point is inside an object
				for (Basic_object bo : objects) {
					if (bo.contains(clicked_position)) {
						src_obj = bo; // record source object;
						checkPort(bo);
						// assign the current showPort as src_port
						src_port = (Point) showPort.clone();
//						System.out.println("mouse pressed src_port " + src_port);
						
					}
				}
			}else if (UML_editor.mode == "SELECT") {
				// for single selection
				Collections.reverse(objects); // sort from shallow to depth
				boolean objClicked = false; // if any object is clicked
				for (Basic_object bo : objects) {
					// bo.select = false;
					if (bo.contains(clicked_position)) {
						if (bo instanceof Composite) {
//							System.out.println("composite  object clicked");
							selectionMode = 3;
						} else {
//							System.out.println("single object pressed");
							selectionMode = 1;
						}
						unselectObjects(); // unselect other objects
						bo.select = true;
						selected_object = bo;
						objClicked = true;
						break;
					}
				}
				Collections.reverse(objects);
//				System.out.println("objClicked: " + objClicked);
//				System.out.println("single selection: " + selectionMode);
				// if no object is clicked and was singleSelection
				if (selectionMode == 1 && !objClicked) {
					selectionMode = 0;
					unselectObjects();
				} else if (!objClicked) { // for group selection or none
											// selection
//					System.out.println("group selection");
					selectionMode = 2;
					src_port = (Point) clicked_position.clone();
					// System.out.println(src_port);
				}
				repaint();
			} else if (click_count >= 3) { // control # objects can add
				JOptionPane.showMessageDialog(this.getParent(), "You can at most add three objects once");
			}
		}

	}

	public void unselectObjects() {
		// System.out.println("unselect objects");
		for (Basic_object bo : objects) {
//			System.out.println(bo.depth);
			selected_object = null;
			bo.select = false;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
//		 System.out.println("mouse released");
		// System.out.println(e.getX() + " " + e.getY());
		// mouse release action when mode is compos, assoc or gener
		if (UML_editor.mode == "COMPOS" || UML_editor.mode == "ASSOC" || UML_editor.mode == "GENER") {
			clicked_position = e.getPoint();
			Collections.reverse(objects);
			for (Basic_object bo : objects) {
				// check if the destination point is in "another" basic_object
				// System.out.println(bo.depth);
				if (bo.contains(clicked_position) && !bo.equals(src_obj)) {
					checkPort(bo); // check which port to show
//					des_port = showPort;
					des_port = (Point) showPort.clone();
//					System.out.println("mouse released des_port " + des_port);
					
					break;
				}
			}
			Collections.reverse(objects);
			// if source and destination ports are both assigned, then the
			// connection_line will appear
			if (src_port.x != -1 && src_port.y != -1 && des_port.x != -1 && des_port.y != -1) {
				switch (UML_editor.mode) {
				case "COMPOS":
					addLine(new Composition_line((Point) src_port.clone(), (Point) des_port.clone()));
					break;
				case "ASSOC":
					addLine(new Association_line((Point) src_port.clone(), (Point) des_port.clone()));
					break;
				case "GENER":
					addLine(new Generalization_line((Point) src_port.clone(), (Point) des_port.clone()));
					break;
				default:
				}
//				System.out.println("mouse released src_port " + src_port);
//				System.out.println("mouse released des_port " + des_port);
//				addPort(des_port);
//				addPort(src_port);
				addPort((Point) src_port.clone());
				addPort((Point) des_port.clone());
				src_port.x = src_port.y = des_port.x = des_port.y = -1;
			} else {
				showPort.x = showPort.y = -1;
			}
			repaint();
			// not single selection case
		} else if (UML_editor.mode == "SELECT" && selectionMode == 2) {
			clicked_position = e.getPoint();
			des_port = (Point) clicked_position.clone();
			// System.out.println(src_port.x + " " + src_port.y);
			int start_x = src_port.x, start_y = src_port.y;
			if (src_port.x > des_port.x && src_port.y > des_port.y) {
				start_x = des_port.x;
				start_y = des_port.y;
			}
			Shape dragScope = new Rectangle(start_x, start_y, Math.abs(des_port.x - src_port.x),
					Math.abs(des_port.y - src_port.y));
			try {
				int num_selected = 0;
				for (Basic_object bo : objects) {
					if (dragScope.contains(bo.x_cord, bo.y_cord)
							&& dragScope.contains(bo.x_cord + bo.object_width, bo.y_cord + bo.object_height)) {
						bo.select = true;
						num_selected++;
					}
				}

				if (num_selected == 1) { // only one object selected so is same
											// as single selection
					selectionMode = 1;
				} else if (num_selected == 0) { // if no object included in the
												// scope
					unselectObjects();
					selectionMode = 0;
				}

				drawDragScope(this.getGraphics(), start_x, start_y);
			} catch (InterruptedException ie) {
				ie.getStackTrace();
			}

			// System.out.println(des_port);
		}
	}

	public void drawDragScope(Graphics g, int start_x, int start_y) throws InterruptedException {
		g.setColor(Color.blue);
		g.drawRect(start_x, start_y, Math.abs(des_port.x - src_port.x), Math.abs(des_port.y - src_port.y));
		Thread.sleep(500); // let the scope show for 0.5 sec
		repaint();
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

	private void addLine(Connection_line line) {
		lines.add(line);
		repaint();
	}

	private void addPort(Point port) {
		ports.add(port);
		repaint();
	}

	// mouseMotionListener
	@Override
	public void mouseDragged(MouseEvent e) {
		if(UML_editor.mode == "SELECT" && selectionMode == 1){
//			System.out.println("single object dragged");
			selected_object.x_cord += (e.getX()-selected_object.x_cord);
			selected_object.y_cord += (e.getY()-selected_object.y_cord);
//			System.out.println("dragged position: " + selected_object.x_cord + " " + selected_object.y_cord);
			repaint();
		}else if(UML_editor.mode == "SELECT" && selectionMode == 3){
			int move_x = e.getX()-selected_object.x_cord;
			int move_y = e.getY()-selected_object.y_cord;
			MoveComposite(selected_object, move_x, move_y);
			repaint();
			
		}
		
	}
	public void MoveComposite(Basic_object bo, int move_x, int move_y){
		bo.x_cord += move_x;
		bo.y_cord += move_y;
		
		if(!(bo instanceof Composite)){
			bo.x_cord += move_x;
			bo.y_cord += move_y;
			return;
		}
		for(Basic_object o: bo.compos_objects){
			MoveComposite(o, move_x, move_y);
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	// check which port of the object to show
	public void checkPort(Basic_object bo) {
		double min = 1000;
		Point temp = new Point();
		// find minimum distance port
		for (Point p : bo.port_cords) {
			double dist = distance(clicked_position, p);
			if (dist < min) {
//				showPort = new Point(p); // reference to the port
				showPort = (Point) p.clone();
//				temp = p;
				min = dist;
			}
		}
//		addPort(temp);
		// System.out.println("showport: " + showPort.x + " " + showPort.y);
		repaint();

	}

	private double distance(Point p1, Point p2) {
		double distance = Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow((p1.y - p2.y), 2));
		// System.out.println(p2.x + " " + p2.y + "dis: " + distance);
		return (distance);
	}

}