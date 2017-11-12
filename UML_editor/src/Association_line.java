import java.awt.Graphics;
import java.awt.Point;

public class Association_line extends Connection_line{

	public Association_line(Point src, Point des) {
		super(src, des);
	}
	@Override
	protected void draw(Graphics g) {
		g.drawLine(this.src_cord.x+Basic_object.port_size/2, this.src_cord.y+Basic_object.port_size/2, this.des_cord.x+Basic_object.port_size/2, this.des_cord.y+Basic_object.port_size/2);
	}
	@Override
	protected void drawAddsOn(Graphics g, int obj_port_size) {
		// TODO Auto-generated method stub
		
	}
	
}
