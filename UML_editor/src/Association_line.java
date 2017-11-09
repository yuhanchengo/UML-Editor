import java.awt.Graphics;
import java.awt.Point;

public class Association_line extends Connection_line{

	public Association_line(Point src, Point des) {
		super(src, des);
	}
	@Override
	protected void draw(Graphics g) {
		g.drawLine(this.src_cord.x, this.src_cord.y, this.des_cord.x, this.des_cord.y);
	}
	
}
