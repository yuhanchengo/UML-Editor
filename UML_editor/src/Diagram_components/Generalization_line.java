package Diagram_components;
import java.awt.Point;
import java.awt.Graphics;


public class Generalization_line extends Line_object{
	private final int triangle_size = 20;
	private final int triangle_height = (int)(this.triangle_size*Math.sqrt(3)/2);
	
	public Generalization_line(Port src,Port des) {
		super(src, des);
	}

	@Override
	public void draw(Graphics g) {
		g.drawLine(this.src_port.x+triangle_height+port_size/2, this.src_port.y + port_size/2, this.des_port.x + port_size/2, this.des_port.y + port_size/2);
		drawAddsOn(g);
		this.src_port.showPort(true);
		this.des_port.showPort(true);
	}
	/*
	 * draw the triangle at the starting connection port of generalization line
	 */
	@Override
	protected void drawAddsOn(Graphics g) {
		Point top = new Point(this.src_port.x + port_size/2, this.src_port.y + port_size/2); // 頂點
		Point left = new Point(this.src_port.x + triangle_height + port_size/2,
				this.src_port.y + this.triangle_size/2 + port_size/2); // 偏上的點
		Point right = new Point(this.src_port.x + triangle_height+port_size/2,
				this.src_port.y - this.triangle_size/2 + port_size/2);// 偏下的點
		g.drawLine(top.x, top.y, left.x, left.y);
		g.drawLine(top.x, top.y, right.x, right.y);
		g.drawLine(right.x, right.y, left.x, left.y);
		
	}



	

}
