import java.awt.Point;
import java.awt.Graphics;


public class Generalization_line extends Connection_line{
	private int height;
	public Generalization_line(Point src,Point des) {
		super(src, des);
		size = 20;
		height = (int)(this.size*Math.sqrt(3)/2);
	}

	@Override
	protected void draw(Graphics g) {
		g.drawLine(this.src_cord.x + height + Basic_object.port_size/2, this.src_cord.y + Basic_object.port_size/2, this.des_cord.x + Basic_object.port_size/2, this.des_cord.y + Basic_object.port_size/2);
		
	}
	/*
	 * draw the triangle at the starting connection port of generalization line
	 */
	@Override
	protected void drawAddsOn(Graphics g, int obj_port_size) {
		int height = (int) (this.size * Math.sqrt(3) / 2);
		Point top = new Point(this.src_cord.x + obj_port_size / 2, this.src_cord.y + obj_port_size / 2); // 頂點
		Point left = new Point(this.src_cord.x + height + obj_port_size / 2,
				this.src_cord.y + this.size / 2 + obj_port_size / 2); // 偏上的點
		Point right = new Point(this.src_cord.x + height + obj_port_size / 2,
				this.src_cord.y - this.size / 2 + obj_port_size / 2);// 偏下的點
		g.drawLine(top.x, top.y, left.x, left.y);
		g.drawLine(top.x, top.y, right.x, right.y);
		g.drawLine(right.x, right.y, left.x, left.y);
		
	}

	

}
