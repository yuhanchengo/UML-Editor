import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
/*
 * The Class class. An Rectangle object in the UML editor
 */

public class Class extends Basic_object{

	Class(int x, int y){
		x_cord = x;
		y_cord = y;
		object_width = 120;
		object_height = 170;
		port_cords.add(new Coords(x_cord - port_size, y_cord + object_height/2-port_size/2));
		port_cords.add(new Coords(x_cord + object_width/2 - port_size/2, y_cord + object_height));
		port_cords.add(new Coords(x_cord + object_width/2 - port_size/2, y_cord - port_size));
		port_cords.add(new Coords(x_cord + object_width, y_cord + object_height/2 - port_size/2));
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
