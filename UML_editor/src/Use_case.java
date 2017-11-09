import java.awt.Color;
import java.awt.Graphics;
/*
 * The class Use_case, a oval object on the UML editor
 */
public class Use_case extends Basic_object{

	Use_case(int x, int y){
		x_cord = x;
		y_cord = y;
		object_width = 160;
		object_height = 100;
		port_cords.add(new Coords(x_cord - port_size, y_cord + object_height/2-port_size/2));
		port_cords.add(new Coords(x_cord + object_width/2 - port_size/2, y_cord + object_height));
		port_cords.add(new Coords(x_cord + object_width/2 - port_size/2, y_cord - port_size));
		port_cords.add(new Coords(x_cord + object_width, y_cord + object_height/2 - port_size/2));
	}
	@Override
	protected void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x_cord, y_cord, object_width, object_height);
		g.setColor(Color.black);
		g.drawOval(x_cord, y_cord, object_width, object_height);
	}

}
