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
		this.x_cord=this.y_cord=0;
		this.object_height=this.object_width=0;
		this.depth=99;
	}
	public boolean contains(Point p){
		return false;
	}
	public Port getPort(Point p){return null;}
	public void selected(boolean condition){}
	abstract public void draw(Graphics g);
	abstract protected void drawAddsOn(Graphics g);
	protected void drawName(Graphics g){}
	public void setName(String name){}
	public void group(Diagram_components dc){}
	public void ungroup(){}
	public void move(int move_x, int move_y){}
}

