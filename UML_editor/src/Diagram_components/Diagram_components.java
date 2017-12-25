package Diagram_components;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Diagram_components {
	public int depth;
	public int x_cord, y_cord;
	public int object_height, object_width;
	public boolean isSelected = false;
	public abstract void draw(Graphics g);
	abstract protected void drawAddsOn(Graphics g);
	abstract protected void drawName(Graphics g);
	abstract public boolean contains(Point p);
	abstract public Port getPort(Point p);
	abstract public void selected(boolean condition);
	abstract public void setName(String name);
	abstract public void group(Diagram_components dc);
	abstract public void ungroup();
	abstract public void move(int x_move, int y_move);
}
