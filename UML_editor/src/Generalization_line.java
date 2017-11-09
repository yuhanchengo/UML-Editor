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
		g.drawLine(this.src_cord.x + height, this.src_cord.y, this.des_cord.x, this.des_cord.y);
		
	}

	

}
