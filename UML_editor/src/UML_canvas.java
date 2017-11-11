import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/*
 * This is the Canvas of the UML editor
 */
public class UML_canvas extends Canvas implements MouseListener, MouseMotionListener {
	// canvas中點擊的座標點
	private Point clicked_position;
	private ArrayList<Basic_object> objects = new ArrayList<Basic_object>();
	private ArrayList<Connection_line> lines = new ArrayList<Connection_line>();
	private ArrayList<Point> ports = new ArrayList<Point>();
	private final int canvas_width = 860;
	private final int canvas_length = 710;
	public static int click_count = 0;
	public static Basic_object selected_object;
	private Point showPort = new Point(-1, -1);
	private Point src_port, des_port = new Point(-1, -1);
	private Basic_object src_obj; // record the source object

	UML_canvas() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(canvas_width, canvas_length));
		addMouseListener(this);
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

		// draw basic_object
		for (int i = 0; i < obj_size; i++) {
			Basic_object bo = objects.get(i);
			bo.draw(g);
			// if is Class then draw two more lines on it (just it's appearance)
			if (bo instanceof Class) {
				g.drawLine(bo.x_cord, (bo.y_cord + bo.object_height / 3), bo.x_cord + bo.object_width,
						(bo.y_cord + bo.object_height / 3));
				g.drawLine(bo.x_cord, bo.y_cord + bo.object_height / 3 * 2, bo.x_cord + bo.object_width,
						bo.y_cord + bo.object_height / 3 * 2);
			}
			// (SELECT)if port_on is true then show connection ports
			if (bo.select) {
				for (Point coord : bo.port_cords) {
					g.setColor(Color.black);
					g.fillRect(coord.x, coord.y, obj_port_size, obj_port_size);
					g.setColor(Color.black);
					g.drawRect(coord.x, coord.y, obj_port_size, obj_port_size);
				}
			}
			// if showPort is set
			if (!showPort.equals(new Point(-1, -1))) {
				// System.out.println("in showPort");
				g.setColor(Color.black);
				g.fillRect(showPort.x, showPort.y, obj_port_size, obj_port_size);
				g.setColor(Color.black);
				g.drawRect(showPort.x, showPort.y, obj_port_size, obj_port_size);
				showPort.x = -1;
				showPort.y = -1;
			}
			if(bo.name != null){
				g.setFont(new Font("TimesRoman",Font.PLAIN, 16));
				g.drawString(bo.name, bo.x_cord + (bo.object_width-bo.name.length()*7)/2 , bo.y_cord+bo.object_height/bo.namePosRatio);
			}

		}

		// draw connection_line
		for (int i = 0; i < line_size; i++) {
			Connection_line line = lines.get(i);
			line.draw(g);
			if (line instanceof Composition_line) {
				int alter_size = (int) (line.size * Math.sqrt(2) / 2);
				Point north = new Point(line.src_cord.x + alter_size + obj_port_size / 2,
						line.src_cord.y - alter_size + obj_port_size / 2);
				Point west = new Point(line.src_cord.x + obj_port_size / 2, line.src_cord.y + obj_port_size / 2);
				Point east = new Point(line.src_cord.x + alter_size * 2 + obj_port_size / 2,
						line.src_cord.y + obj_port_size / 2);
				Point south = new Point(line.src_cord.x + alter_size + obj_port_size / 2,
						line.src_cord.y + alter_size + obj_port_size / 2);
				g.drawLine(east.x, east.y, north.x, north.y); // east point to
																// north point
				g.drawLine(east.x, east.y, south.x, south.y);// east point to
																// south point
				g.drawLine(north.x, north.y, west.x, west.y);
				g.drawLine(south.x, south.y, west.x, west.y);

			} else if (line instanceof Generalization_line) {
				int height = (int) (line.size * Math.sqrt(3) / 2);
				Point top = new Point(line.src_cord.x + obj_port_size / 2, line.src_cord.y + obj_port_size / 2); // 頂點
				Point left = new Point(line.src_cord.x + height + obj_port_size / 2,
						line.src_cord.y + line.size / 2 + obj_port_size / 2); // 偏上的點
				Point right = new Point(line.src_cord.x + height + obj_port_size / 2,
						line.src_cord.y - line.size / 2 + obj_port_size / 2);// 偏下的點
				g.drawLine(top.x, top.y, left.x, left.y);
				g.drawLine(top.x, top.y, right.x, right.y);
				g.drawLine(right.x, right.y, left.x, left.y);
			}
		}

		// draw ports
		for (int i = 0; i < port_size; i++) {
			Point p = ports.get(i);
			// Coordinate p = ports.get(i);
			g.setColor(Color.black);
			g.fillRect(p.x, p.y, obj_port_size, obj_port_size);
			g.setColor(Color.black);
			g.drawRect(p.x, p.y, obj_port_size, obj_port_size);

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// only when clicked CLASS or USE_CASE button can one draw a figure on
		// the canvas
		if (e.getButton() == MouseEvent.BUTTON1) {
			clicked_position = e.getPoint();
			if (UML_editor.mode == "CLASS" && click_count < 3) {
				addObject(new Class((int) clicked_position.getX(), (int) clicked_position.getY()));
				click_count++;
			} else if (UML_editor.mode == "USE_CASE" && click_count < 3) {
				addObject(new Use_case((int) clicked_position.getX(), (int) clicked_position.getY()));
				click_count++;
			} else if (UML_editor.mode == "COMPOS" || UML_editor.mode == "ASSOC" || UML_editor.mode == "GENER") {
				// check if clicked point is inside an object
				for (Basic_object bo : objects) {
					if (bo.contains(clicked_position)) {
						src_obj = bo; // record source object;
						checkPort(bo);
						// assign the current showPort as src_port
						src_port = (Point) showPort.clone();
					}
				}

			} else if (UML_editor.mode == "SELECT") {
				// if some single object is selected
				for (Basic_object bo : objects) {
					bo.select = false;
					if (bo.contains(clicked_position)) {
						bo.select = true;
						selected_object = bo;
					}
				}
				repaint();
			} else if (click_count >= 3) { // control # objects can add
				JOptionPane.showMessageDialog(this.getParent(), "You can at most add three objects once");
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		if (UML_editor.mode == "COMPOS" || UML_editor.mode == "ASSOC" || UML_editor.mode == "GENER") {
			clicked_position = e.getPoint();
			for (Basic_object bo : objects) {
				// check if the destination point is in "another" basic_object
				if (bo.contains(clicked_position) && !bo.equals(src_obj)) {
					checkPort(bo);
					des_port = (Point) showPort.clone();
				}
			}
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
				addPort((Point) src_port.clone());
				addPort((Point) des_port.clone());
				src_port.x = src_port.y = des_port.x = des_port.y = -1;
			} else {
				showPort.x = showPort.y = -1;
			}
			repaint();
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
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	// check which port of the object to show
	public void checkPort(Basic_object bo) {
		double min = 1000;
		// find minimum distance port
		for (Point p : bo.port_cords) {
			double dist = distance(clicked_position, p);
			if (dist < min) {
				showPort = (Point) p.clone();
				min = dist;
			}
		}
		// System.out.println("showport: " + showPort.x + " " + showPort.y);
		repaint();

	}

	private double distance(Point p1, Point p2) {
		double distance = Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow((p1.y - p2.y), 2));
		// System.out.println(p2.x + " " + p2.y + "dis: " + distance);
		return (distance);
	}
	
	
}