import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import javafx.scene.input.MouseButton;

/*
 * This is the Canvas of the UML editor
 */
public class UML_canvas extends Canvas implements MouseListener{
	// canvas中點擊的座標點
	private static Point clicked_position;
	private static ArrayList<Basic_object> objects = new ArrayList<Basic_object>();
	private static final int canvas_width = 860;
	private static final int canvas_length = 710;
	
	UML_canvas(){
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(canvas_width, canvas_length));
		addMouseListener(this);
	}
	
	
	/* 1. add the new object to a list
	 * 2. call repaint()
	 * 3. In paint(Graphics) draw the list of objects 
	 */
//	@ Override
//	public void paintComponent(Graphics g){
//		int top = objects.size();
//	    for (int i = 0; i < top; i++) {
//	        Basic_object bo = objects.get(i);
//	        bo.draw(g);
//	    }
//	}
	
	public void paint(Graphics g){
		int top = objects.size();
	    for (int i = 0; i < top; i++) {
	        Basic_object bo = objects.get(i);
	        bo.draw(g);
	        // if is Class then draw two more lines on it (just it's appearance)
	        if(bo instanceof Class){
	        	g.drawLine(bo.x_cord, (bo.y_cord + bo.object_height/3), bo.x_cord+bo.object_width, (bo.y_cord+bo.object_height/3));
	        	g.drawLine(bo.x_cord, bo.y_cord + bo.object_height/3*2, bo.x_cord+bo.object_width, bo.y_cord+bo.object_height/3*2);
	        }
	        // if port_on is true then show connection ports
	        if(bo.port_on){
	        	for(Coords coord: bo.port_cords){
	        		g.setColor(Color.black);
	        		g.fillRect(coord.x, coord.y, bo.port_size, bo.port_size);
	        		g.setColor(Color.black);
	        		g.drawRect(coord.x, coord.y, bo.port_size, bo.port_size);
	        	}
	        }
	       
	    }
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// only when clicked CLASS or USE_CASE button can one draw a figure on the canvas
		if(UML_editor.mode == "CLASS" && e.getButton() == MouseEvent.BUTTON1){
			clicked_position = e.getPoint();
			addObject(new Class((int) clicked_position.getX(), (int)clicked_position.getY()));
			repaint();
		}else if(UML_editor.mode == "USE_CASE" && e.getButton() == MouseEvent.BUTTON1){
			clicked_position = e.getPoint();
			addObject(new Use_case((int)clicked_position.getX(), (int)clicked_position.getY()));
			repaint();
		}
		
	}
	private void addObject(Basic_object obj){
		objects.add(obj);
		repaint();
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