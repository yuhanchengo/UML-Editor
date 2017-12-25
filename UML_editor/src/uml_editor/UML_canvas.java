package uml_editor;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;


import Diagram_components.Basic_object;
import Diagram_components.Diagram_components;
import Mode.Mode;

/*
 * This is the Canvas of the UML editor
 */
@SuppressWarnings("serial")
public class UML_canvas extends Canvas implements MouseListener, MouseMotionListener {
	
	public static Mode currentMode; // mode of the editor
	public static ArrayList<Diagram_components> objects = new ArrayList<Diagram_components>();
	public static ArrayList<Basic_object> basic_objects = new ArrayList<Basic_object>();
	public static Diagram_components selected_object;
	private final int canvas_width = 860;    
	private final int canvas_length = 710;  
	public static int objDepth = 99;
	

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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		currentMode.mousePressed(e);
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		currentMode.mouseReleased(e);
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

	// mouseMotionListener
	@Override
	public void mouseDragged(MouseEvent e) {
		currentMode.mouseDragged(e);
		repaint();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}




}