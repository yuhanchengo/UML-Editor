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
import javax.swing.JOptionPane;
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
	private static ArrayList<Connection_line> lines = new ArrayList<Connection_line>();
	private static final int canvas_width = 860;
	private static final int canvas_length = 710;
	public static int click_count = 0;
	private static Point showPort = new Point(-1, -1);
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
		int obj_size = objects.size();
		int line_size = lines.size();
	    for (int i = 0; i < obj_size; i++) {
	        Basic_object bo = objects.get(i);
	        bo.draw(g);
	        // if is Class then draw two more lines on it (just it's appearance)
	        if(bo instanceof Class){
	        	g.drawLine(bo.x_cord, (bo.y_cord + bo.object_height/3), bo.x_cord+bo.object_width, (bo.y_cord+bo.object_height/3));
	        	g.drawLine(bo.x_cord, bo.y_cord + bo.object_height/3*2, bo.x_cord+bo.object_width, bo.y_cord+bo.object_height/3*2);
	        }
	        // (SELECT)if port_on is true then show connection ports
	        if(bo.port_on){
	        	for(Point coord: bo.port_cords){
	        		g.setColor(Color.black);
	        		g.fillRect(coord.x, coord.y, bo.port_size, bo.port_size);
	        		g.setColor(Color.black);
	        		g.drawRect(coord.x, coord.y, bo.port_size, bo.port_size);
	        	}
	        }
	        // if showPort is set
	        if(!showPort.equals(new Point(-1, -1))){
	        	g.setColor(Color.black);
	        	g.fillRect(showPort.x, showPort.y,bo.port_size , bo.port_size);
	        	g.setColor(Color.black);
	        	g.drawRect(showPort.x, showPort.y,bo.port_size , bo.port_size);
	        	showPort.x= -1;
	        	showPort.y = -1;
	        }
	       
	    }
	    for(int i=0; i < line_size; i++){
	    	Connection_line line = lines.get(i);
	    	line.draw(g);
	    	if(line instanceof Composition_line){
	    		int alter_size = (int)(line.size*Math.sqrt(2)/2);
	    		Point north = new Point(line.src_cord.x+alter_size, line.src_cord.y - alter_size);
	    		Point west = new Point(line.src_cord.x, line.src_cord.y);
	    		Point east = new Point(line.src_cord.x + alter_size*2, line.src_cord.y);
	    		Point south = new Point(line.src_cord.x+alter_size, line.src_cord.y+alter_size);
	    		g.drawLine(east.x,east.y, north.x, north.y); // east point to north point
	    		g.drawLine(east.x, east.y, south.x, south.y);// east point to south point
	    		g.drawLine(north.x, north.y, west.x, west.y);
	    		g.drawLine(south.x, south.y, west.x, west.y);
	    	}else if(line instanceof Generalization_line){
	    		int height = (int)(line.size*Math.sqrt(3)/2);
	    		Point top = new Point(line.src_cord.x, line.src_cord.y); // 頂點
	    		Point left = new Point(line.src_cord.x+height, line.src_cord.y+line.size/2); //偏上的點
	    		Point right = new Point(line.src_cord.x+height, line.src_cord.y-line.size/2);//偏下的點
	    		g.drawLine(top.x, top.y, left.x, left.y);
	    		g.drawLine(top.x, top.y, right.x, right.y);
	    		g.drawLine(right.x,right.y, left.x, left.y);
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
		if(e.getButton() == MouseEvent.BUTTON1){
			clicked_position = e.getPoint();
			if(UML_editor.mode == "CLASS" && click_count<3 ){
				addObject(new Class((int) clicked_position.getX(), (int)clicked_position.getY()));
				click_count++;
			}else if(UML_editor.mode == "USE_CASE" && click_count<3){
				addObject(new Use_case((int)clicked_position.getX(), (int)clicked_position.getY()));
				click_count++;
			}else if(UML_editor.mode == "COMPOS"){
				checkPort();
				System.out.println("compos mode");
//				addLine(new Composition_line(clicked_position, new Point(500,500)));
			}else if(UML_editor.mode == "ASSOC" ){
				addLine(new Association_line(clicked_position, new Point(500, 500)));
			}else if(UML_editor.mode == "GENER"){
				addLine(new Generalization_line(clicked_position, new Point(500, 500)));
			}else if(click_count >= 3){ // control # objects can add
				JOptionPane.showMessageDialog(this.getParent(), "You can at most add three objects once");
			}
		}
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
//		System.out.println("released");
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	private void addObject(Basic_object obj){
		objects.add(obj);
		repaint();
	}
	private void addLine(Connection_line line){
		lines.add(line);
		repaint();
	}
	// if it is in a class or a use_case
	public void checkPort(){
		double min = 1000;
		for(Basic_object bo : objects){
			if(bo.contains(clicked_position)){
//				System.out.println("contains point");
				// find minimum distance port
				for(Point p: bo.port_cords){
					 double dist = distance(clicked_position, p);
					 System.out.println(p.x + " " + p.y);
					 System.out.println("clicked_position" + clicked_position.x + " " + clicked_position.y);
					 System.out.println("distance : " + dist);
					if(dist < min){
						showPort = (Point) p.clone();
						min = dist;
					}
				}
				System.out.println("showport: " + showPort.x + " " + showPort.y);
			}
		}
		repaint();

	}
	private double distance(Point p1, Point p2){
		double distance = Math.sqrt(Math.pow(p1.x-p2.x ,2)+Math.pow((p1.y-p2.y), 2));
//		System.out.println(p2.x + " " + p2.y + "dis: " + distance);
		return(distance);
	}
	
}