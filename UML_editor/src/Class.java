import java.awt.Graphics;
import java.awt.Rectangle;

public class Class extends Basic_object{

	Class(int x, int y){
		x_cord = x;
		y_cord = y;
	}
	@Override
	protected void draw(Graphics g) {
		g.drawRect( x_cord, y_cord, 80, 150);
		
	}
	
	
}
