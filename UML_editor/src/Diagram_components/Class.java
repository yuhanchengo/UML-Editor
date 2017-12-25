package Diagram_components;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
/*
 * The Class class. An Rectangle object in the UML editor
 */

public class Class extends Basic_object{
	
	private static int object_width = 120;
	private static int object_height = 170;
	private static int namePosRatio = 5; 
	
	public Class(int x, int y){
		super(x,y, object_width, object_height, namePosRatio);
	}
	

	@Override
	public void draw(Graphics g) {
		// draw rectangle
		g.setColor(Color.WHITE);
		g.fillRect(x_cord, y_cord, object_width, object_height);
		g.setColor(Color.black);
		g.drawRect( x_cord, y_cord, object_width, object_height);
		this.drawAddsOn(g);
		for(Port p: this.ports){
			p.draw(g);
		}
		this.drawName(g);
	}
	/*
	 * draw two more lines for Class objects
	 */
	@Override
	protected void drawAddsOn(Graphics g){
		g.drawLine(this.x_cord, (this.y_cord + object_height / 3), this.x_cord + object_width,
				(this.y_cord + object_height / 3));
		g.drawLine(this.x_cord, this.y_cord + object_height / 3 * 2, this.x_cord + object_width,
				this.y_cord + object_height / 3 * 2);
	}
	@Override
	protected void drawName(Graphics g){
		if(!this.getName().equals("")){
			g.setFont(new Font("TimesRoman", Font.PLAIN, 16));
			g.drawString(this.getName(), this.x_cord + (object_width - this.name.length() * 7) / 2,
					this.y_cord + object_height / namePosRatio);
		}
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}







	
	
}
