package Diagram_components;
import java.awt.Graphics;
import java.awt.Point;

public class Composition_line extends Line_object{
	
	private int diagonal; //  diagonal of square port
	private int rhombus_size = 10;
	private int rhombus_diagonal = (int)(this.rhombus_size*Math.sqrt(2)/2);
	public Composition_line(Port src, Port des){
		super(src, des);
	}

	@Override
	public void draw(Graphics g) {
		g.drawLine(this.src_port.x + this.port_size/2 + diagonal*2, this.src_port.y + this.port_size/2, this.des_port.x + this.port_size/2, this.des_port.y + this.port_size/2);
		drawAddsOn(g);
		// if port has line, then show
		this.src_port.showPort(true);
		this.des_port.showPort(true);
	}
	/*
	 * draw the rhombus at the starting connection port of composition line
	 */
	@Override
	protected void drawAddsOn(Graphics g){
		
		Point north = new Point(this.src_port.x + rhombus_diagonal + this.port_size/2,
				this.src_port.y - rhombus_diagonal + this.port_size/2);
		Point west = new Point(this.src_port.x + this.port_size / 2, this.src_port.y + this.port_size / 2);
		Point east = new Point(this.src_port.x + rhombus_diagonal * 2 + this.port_size / 2,
				this.src_port.y + this.port_size / 2);
		Point south = new Point(this.src_port.x + rhombus_diagonal + this.port_size / 2,
				this.src_port.y + rhombus_diagonal + this.port_size / 2);
		g.drawLine(east.x, east.y, north.x, north.y); // east point to north point
		g.drawLine(east.x, east.y, south.x, south.y);// east point to south point
		g.drawLine(north.x, north.y, west.x, west.y);
		g.drawLine(south.x, south.y, west.x, west.y);
	}
	
}
