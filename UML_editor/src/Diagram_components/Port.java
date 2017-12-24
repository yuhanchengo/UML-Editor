package Diagram_components;

import java.awt.Color;
import java.awt.Graphics;

public class Port {
	public int x;
	public int y;
	private boolean state;
	private int size = 10; // port size
	
	public Port(int x, int y){
		this.x = x;
		this.y = y;
	}
	public void draw(Graphics g){
		if(this.state){
			g.setColor(Color.BLACK);
			g.fillRect(this.x, this.y, this.size, this.size);
			g.setColor(Color.BLACK);
			g.drawRect(this.x, this.y, this.size, this.size);
		}
		
	}
	// port x direction move x, y direction move y
	public void move(int x, int y){
		this.x += x;
		this.y += y;
	}
	// port show or not
	public void showPort(boolean b){
		this.state = b;
	}
	
	public boolean getPortState(){
		return this.state;
	}
	
	public int portSize(){
		return this.size;
	}
}
