import java.awt.Graphics;
import java.util.ArrayList;
/*
 * The parent class of "Class" and "USE_CASE"
 */
public abstract class Basic_object{
	protected int x_cord; // x_coordinate of object
	protected int y_cord; // y_coordinate of object
	protected int depth; // depth of object
	protected String name; // name of object
	protected int object_height; // length of object
	protected int object_width; //width of object 
	protected Boolean port_on = false; // show ports or not
	protected final int port_size = 10; // 
	protected ArrayList<Coords> port_cords = new ArrayList<Coords>();
	
	public Basic_object(){
		
	}

	// change object name 時更換物件名稱
	protected void change_name(String newName){
		this.name = newName;
	}
	abstract protected void draw( Graphics g);

}
