import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class UML_canvas extends Canvas{
	UML_canvas(){
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(860, 710));
	}
	
	// 因為extend canvas 所以需要override paint()
	public void paint(Graphics g){
		//g.drawLine(0, 1, 2, 2);
	}
}
