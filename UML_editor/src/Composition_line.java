import java.awt.Graphics;
import java.awt.Point;

public class Composition_line extends Connection_line{
	
	private int diagonal;
	public Composition_line(Point src, Point des){
		super(src, des);
		size = 10;
		diagonal = (int)(this.size*Math.sqrt(2)/2);
		System.out.println(src.x+ " " + src.y+" " + des.x + " " + des.y);
	}

	@Override
	protected void draw(Graphics g) {
		g.drawLine(this.src_cord.x+ diagonal*2, this.src_cord.y, this.des_cord.x, this.des_cord.y);
	}
	
}
