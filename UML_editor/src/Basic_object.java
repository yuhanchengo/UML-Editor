import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
/*
 * The parent class of "Class" and "USE_CASE"
 */
public abstract class Basic_object implements Comparable{
	protected int x_cord; // x_coordinate of object
	protected int y_cord; // y_coordinate of object
	protected int depth; // depth of object
	protected String name; // name of object
	protected int object_height; // length of object
	protected int object_width; //width of object 
	protected int namePosRatio;
	protected Boolean select = false; // show ports or not in other word 
	protected ArrayList<Basic_object> compos_objects;
	public static final int port_size = 10; // 
	protected ArrayList<Point> port_cords = new ArrayList<Point>();
	
	public Basic_object(){
		
	}
	public Basic_object(int x, int y, int object_width, int object_height, int namePosRatio){
		this.x_cord = x;
		this.y_cord = y;
		this.object_height = object_height;
		this.object_width = object_width;
		this.namePosRatio = namePosRatio;
		Point West = new Point(x_cord - port_size, y_cord + this.object_height/2-port_size/2);
		Point South = new Point(x_cord + this.object_width/2 - port_size/2, y_cord + this.object_height);
		Point North = new Point(x_cord + this.object_width/2 - port_size/2, y_cord - port_size);
		Point East = new Point(x_cord + this.object_width, y_cord + this.object_height/2 - port_size/2);
		port_cords.add(West);
		port_cords.add(South);
		port_cords.add(North);
		port_cords.add(East);

	}

	// change object name 時更換物件名稱
	protected void change_name(String newName){
		this.name = newName;
	}
	// check if point p is contained in an basic object
	protected boolean contains(Point p){
//		System.out.println(object_width + " " + object_height);
		if(p.x<this.x_cord+object_width && p.x>x_cord){
			if(p.y>this.y_cord && p.y<this.y_cord+object_height){
				return true;
			}
		}
		return false;
	}
	abstract protected void draw( Graphics g);

	public int compareTo(Basic_object compareObject) {
		return compareObject.depth - this.depth;
	}
	abstract protected void drawClassLine(Graphics g);
	protected void updatePorts(){
		System.out.println("port upate");
		// west
		port_cords.set(0, new Point(x_cord - port_size, y_cord + this.object_height/2-port_size/2));
		// south
		port_cords.set(1, new Point(x_cord + this.object_width/2 - port_size/2, y_cord + this.object_height));
		//north
		port_cords.set(2, new Point(x_cord + this.object_width/2 - port_size/2, y_cord - port_size));
		// east
		port_cords.set(3, new Point(x_cord + this.object_width, y_cord + this.object_height/2 - port_size/2));
		System.out.println(port_cords.get(0));
	}
		
	

	

}
