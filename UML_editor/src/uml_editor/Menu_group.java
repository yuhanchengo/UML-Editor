package uml_editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;

public class Menu_group extends JMenuItem implements ActionListener{
	Menu_group(){
		super("Group");
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("group");
//		Basic_object comp = new Composite();
//		int min_src_port_x = 1000, min_src_port_y = 1000;
//		int max_des_port_x = 0, max_des_port_y = 0;
//		for(Diagram_components dc : new ArrayList<Diagram_components>(UML_canvas.objects)){
//			if(bo.select){
//				comp.compos_objs.add(bo);
//				canvas.objects.remove(bo);
//				comp.depth = bo.depth;
//				if(bo.x_cord < min_src_port_x){
//					min_src_port_x = bo.x_cord;
//				}
//				if(bo.y_cord < min_src_port_y){
//					min_src_port_y = bo.y_cord;
//				}
//				if(bo.x_cord+bo.object_width > max_des_port_x){
//					max_des_port_x = bo.x_cord+bo.object_width;
//				}
//				if(bo.y_cord + bo.object_height > max_des_port_y){
//					max_des_port_y = bo.y_cord + bo.object_height;
//				}
//			}
//		}
//		comp.x_cord = min_src_port_x;
//		comp.y_cord = min_src_port_y;
//		comp.object_height = max_des_port_y - min_src_port_y;
//		comp.object_width = max_des_port_x - min_src_port_x;
//		canvas.objects.add(comp);
//		// check if composed
////		System.out.println("length of objects after group : " + canvas.objects.size());
//		
	}
}
