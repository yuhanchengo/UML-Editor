import java.awt.Color;
import java.awt.Graphics;
/*
 * The class Use_case, a oval object on the UML editor
 */
public class Use_case extends Basic_object{
protected static int object_width = 160;
protected static int object_height = 100;
	Use_case(int x, int y){
		super(x, y, object_width, object_height);
	}
	@Override
	protected void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x_cord, y_cord, object_width, object_height);
		g.setColor(Color.black);
		g.drawOval(x_cord, y_cord, object_width, object_height);
	}

}
