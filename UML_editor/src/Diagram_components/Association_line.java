package Diagram_components;
import java.awt.Graphics;
import java.awt.Point;

public class Association_line extends Line_object{

	public Association_line(Port src, Port des) {
		super(src, des);
	}
	@Override
	public void draw(Graphics g) {
		g.drawLine(this.src_port.x+this.port_size/2, this.src_port.y+this.port_size/2, this.des_port.x+this.port_size/2, this.des_port.y+this.port_size/2);
		this.drawAddsOn(g);
		this.src_port.showPort(true);
		this.des_port.showPort(true);
	}
	@Override
	protected void drawAddsOn(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
