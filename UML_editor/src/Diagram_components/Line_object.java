package Diagram_components;
import java.awt.Graphics;
import java.awt.Point;

public abstract class Line_object extends Diagram_components{

	protected Port src_port;    // source port
	protected Port des_port; 	// des port
	protected final int port_size = 10;
	
	public Line_object(Port src, Port des){
		this.src_port = src;
		this.des_port = des;
	}
	public boolean contains(Point p){
		return false;
	}
	public Port getPort(Point p){return null;}
	public void selected(Boolean condition){}
	abstract public void draw(Graphics g);
	abstract protected void drawAddsOn(Graphics g);
	

}

