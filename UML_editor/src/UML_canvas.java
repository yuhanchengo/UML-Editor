import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import javafx.scene.input.MouseButton;

public class UML_canvas extends Canvas implements MouseListener{
	// canvas中點擊的座標點
	private static Point clicked_position;
	private static ArrayList<Basic_object> objects = new ArrayList<Basic_object>();
	
	
	UML_canvas(){
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(860, 710));
		addMouseListener(this);
	}
	
	
	/* 1. add the new object to a list
	 * 2. call repaint()
	 * 3. In paint(Graphics) draw the list of objects 
	 */
	public void paint(Graphics g){		 
		int top = objects.size();
	    for (int i = 0; i < top; i++) {
	        Basic_object bo = objects.get(i);
	        bo.draw(g);
	    }
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
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