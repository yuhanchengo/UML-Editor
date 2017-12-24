package Diagram_components;
import java.awt.Color;
import java.awt.Graphics;
/*
 * The class Use_case, a oval object on the UML editor
 */
public class Use_case extends Basic_object{
	
	protected static int object_width = 160;
	protected static int object_height = 100;
	protected static int namePosRatio = 2;
	
	public Use_case(int x, int y){
		super(x, y, object_width, object_height, namePosRatio);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x_cord, y_cord, object_width, object_height);
		g.setColor(Color.black);
		g.drawOval(x_cord, y_cord, object_width, object_height);
		for(Port p: this.ports){
			p.draw(g);
		}
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	protected void drawAddsOn(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
