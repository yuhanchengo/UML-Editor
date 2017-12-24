package Diagram_components;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Composite extends Basic_object{
	public ArrayList<Basic_object> compos_objs;
	
	public Composite() {
		super();
		compos_objs = new ArrayList<Basic_object>();
	}

	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void draw(Graphics g) {
		// draw composite objects
		for(Basic_object bo: compos_objs){
			bo.draw(g);
			bo.drawAddsOn(g);
		}
		
	}
	
	public boolean contains(Point p){
		if(p.x>this.x_cord && p.x<this.x_cord+this.object_width && p.y>this.y_cord && p.y < this.y_cord+this.object_height){
			return true;
		}
		return false;
	}


	@Override
	protected void drawAddsOn(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	
}
