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
	/*
	 * draw the square at the starting connection port of composition line
	 */
	@Override
	protected void drawAddsOn(Graphics g, int obj_port_size){
		int alter_size = (int) (this.size * Math.sqrt(2) / 2);
		Point north = new Point(this.src_cord.x + alter_size + obj_port_size / 2,
				this.src_cord.y - alter_size + obj_port_size / 2);
		Point west = new Point(this.src_cord.x + obj_port_size / 2, this.src_cord.y + obj_port_size / 2);
		Point east = new Point(this.src_cord.x + alter_size * 2 + obj_port_size / 2,
				this.src_cord.y + obj_port_size / 2);
		Point south = new Point(this.src_cord.x + alter_size + obj_port_size / 2,
				this.src_cord.y + alter_size + obj_port_size / 2);
		g.drawLine(east.x, east.y, north.x, north.y); // east point to
														// north point
		g.drawLine(east.x, east.y, south.x, south.y);// east point to
														// south point
		g.drawLine(north.x, north.y, west.x, west.y);
		g.drawLine(south.x, south.y, west.x, west.y);
	}
	
}
