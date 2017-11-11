import java.awt.Color;
import java.awt.Graphics;
/*
 * The Class class. An Rectangle object in the UML editor
 */

public class Class extends Basic_object{
protected static int object_width = 120;
protected static int object_height = 170;
protected static int namePosRatio = 5; 
	Class(int x, int y){
		super(x,y, object_width, object_height, namePosRatio);
	}
	
	@Override
	protected void draw(Graphics g) {
		// draw rectangle with border
		g.setColor(Color.WHITE);
		g.fillRect(x_cord, y_cord, object_width, object_height);
		g.setColor(Color.black);
		g.drawRect( x_cord, y_cord, object_width, object_height);
	}
	
	
}
