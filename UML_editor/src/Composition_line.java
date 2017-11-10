import java.awt.Graphics;
import java.awt.Point;

public class Composition_line extends Connection_line{
	
	private int diagonal; //  diagonal of square port
	public Composition_line(Point src, Point des){
		super(src, des);
		size = 10;
		diagonal = (int)(this.size*Math.sqrt(2)/2);
	}

	@Override
	protected void draw(Graphics g) {
		g.drawLine(this.src_cord.x + Basic_object.port_size/2 + diagonal*2, this.src_cord.y + Basic_object.port_size/2, this.des_cord.x + Basic_object.port_size/2, this.des_cord.y+Basic_object.port_size/2);
	}
	
}
