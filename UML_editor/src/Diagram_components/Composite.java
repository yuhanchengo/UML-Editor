package Diagram_components;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import uml_editor.UML_canvas;

public class Composite extends Basic_object{
	public ArrayList<Diagram_components> compos_objs;
	
	public Composite() {
		super();
		compos_objs = new ArrayList<Diagram_components>();
	}

	@Override
	public void draw(Graphics g) {
		// draw composite objects
		for(Diagram_components bo: compos_objs){
			bo.draw(g);
			bo.drawAddsOn(g);
		}
		
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean contains(Point p){
		if(p.x>this.x_cord && p.x<this.x_cord+this.object_width && p.y>this.y_cord && p.y < this.y_cord+this.object_height){
			return true;
		}
		return false;
	}

	@Override
	protected void drawAddsOn(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	public void group(Diagram_components comp){
		this.compos_objs.add(comp);
		UML_canvas.objects.remove(comp);
		this.depth = comp.depth;
	}
	
	public void ungroup(){
		for(Diagram_components dc:this.compos_objs){
			UML_canvas.objects.add(dc);
		}
		UML_canvas.objects.remove(this);
	}
	public void selected(boolean condition){
		for(Diagram_components dc: this.compos_objs){
			dc.selected(condition);
		}
		this.isSelected=condition;
	}
	public void move(int x_move, int y_move){
		for(Diagram_components dc: this.compos_objs){
			dc.move(x_move, y_move);
		}
		this.x_cord+=x_move;
		this.y_cord+=y_move;
	}
	@Override
	protected void drawName(Graphics g) {
		for(Diagram_components dc: this.compos_objs){
			dc.draw(g);
		}
		
	}
	
}
