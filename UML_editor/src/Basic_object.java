import java.awt.Graphics;

public abstract class Basic_object{
	protected int x_cord;
	protected int y_cord;
	protected int depth;
	protected String name;
	// 秀出四個方位的點
	protected void show_connect_ports(){
		
	}
	// change object name 時更換物件名稱
	protected void change_name(String newName){
		this.name = newName;
	}
	abstract protected void draw( Graphics g);
	
}
