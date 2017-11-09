import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
/*
 * The parent class of "Class" and "USE_CASE"
 */
public abstract class Basic_object{
	protected int x_cord; // x_coordinate of object
	protected int y_cord; // y_coordinate of object
	protected int depth; // depth of object
	protected String name; // name of object
	protected static int object_height; // length of object
	protected static int object_width; //width of object 
	protected Boolean port_on = false; // show ports or not
	protected final int port_size = 10; // 
	protected ArrayList<Point> port_cords = new ArrayList<Point>();
	
	public Basic_object(int x, int y, int object_width, int object_height){
		this.x_cord = x;
		this.y_cord = y;
		port_cords.add(new Point(x_cord - port_size, y_cord + object_height/2-port_size/2));
		port_cords.add(new Point(x_cord + object_width/2 - port_size/2, y_cord + object_height));
		port_cords.add(new Point(x_cord + object_width/2 - port_size/2, y_cord - port_size));
		port_cords.add(new Point(x_cord + object_width, y_cord + object_height/2 - port_size/2));

	}

	// change object name 時更換物件名稱
	protected void change_name(String newName){
		this.name = newName;
	}
	abstract protected void draw( Graphics g);

}
