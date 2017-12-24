package Diagram_components;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
/*
 * The parent class of "Class" and "USE_CASE"
 */
public abstract class Basic_object extends Diagram_components implements Comparable<Object>{
	
	protected int x_cord; // x_coordinate of object
	protected int y_cord; // y_coordinate of object
	protected int depth; // depth of object
	protected String name; // name of object
	protected int object_height; // length of object
	protected int object_width; //width of object 
	protected int namePosRatio; // where to put name
	public final int port_size = 10; // port size
	protected Port[] ports= new Port[4]; // four ports
	ArrayList<Basic_object> compos_objects;
	
	public Basic_object(){};
	public Basic_object(int x, int y, int object_width, int object_height, int namePosRatio){
		this.x_cord = x;
		this.y_cord = y;
		this.object_height = object_height;
		this.object_width = object_width;
		this.namePosRatio = namePosRatio;
		ports[0] = new Port(x+this.object_width, y+this.object_height/2-port_size/2); // 東
		ports[1] = new Port(x-port_size, y+this.object_height/2-port_size/2); // 西
		ports[2] = new Port(x+this.object_width/2-port_size/2, y+this.object_height); //南
		ports[3] = new Port(x+this.object_width/2-port_size/2, y-port_size); // 北
		
	}
	abstract public void draw(Graphics g);
	abstract protected void drawAddsOn(Graphics g);
	// change object name 時更換物件名稱
	protected void change_name(String newName){
		this.name = newName;
	}
	
	// check if point p is contained in an basic object
	public boolean contains(Point p){
		if(p.x<this.x_cord+object_width && p.x>x_cord){
			if(p.y>this.y_cord && p.y<this.y_cord+object_height){
				return true;
			}
		}
		return false;
	}
	
	public Port getPort(Point clicked_point){
		double min = 1000;
		// find minimum distance port
		Port showPort=null;
		for (Port p : this.ports) {
			double dist = distance(clicked_point.x, clicked_point.y, p.x, p.y);
			if (dist < min) {
				showPort = p;
				min = dist;
			}
		}
		System.out.println("Basic_object showPort: " + showPort);
//		showPort.showPort(true);
		return showPort;
	}
	private double distance(int clicked_x, int clicked_y, int port_x, int port_y) {
		double distance = Math.sqrt(Math.pow(clicked_x - port_x, 2) + Math.pow((clicked_y - port_y), 2));
		return (distance);
	}
	
	
	// compare objects' depth
	public int compareTo(Basic_object compareObject) {
		return compareObject.depth - this.depth;
	}
	
	
	// if object is selected, show all ports
	public void selected(Boolean condition){
		for(Port p: ports){
			p.showPort(condition);
		}
		
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	

}
