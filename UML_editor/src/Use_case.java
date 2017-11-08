import java.awt.Graphics;

public class Use_case extends Basic_object{

	Use_case(int x, int y){
		x_cord = x;
		y_cord = y;
	}
	@Override
	protected void draw(Graphics g) {
		g.drawOval(x_cord, y_cord, 160, 100);
	}

}
