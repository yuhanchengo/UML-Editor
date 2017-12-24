package Diagram_components;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Diagram_components {
	public int depth;
	public abstract void draw(Graphics g);
	abstract protected void drawAddsOn(Graphics g);
	abstract public boolean contains(Point p);
	abstract public Port getPort(Point p);
	abstract public void selected(Boolean condition);
}
