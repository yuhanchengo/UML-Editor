import java.awt.Graphics;
import java.awt.Point;

public abstract class Connection_line {
	// coordinate of source point 
	protected Point src_cord;
	// coordinate of destination point
	protected Point des_cord;
	protected int size; // side size of triangle or rhombus
	public Connection_line(Point src,Point des){
		this.src_cord = src;
		this.des_cord = des;
	}
	abstract protected void draw(Graphics g);
		
		

}

