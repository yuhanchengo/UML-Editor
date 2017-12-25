package uml_editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;

import Diagram_components.Composite;
import Diagram_components.Diagram_components;

@SuppressWarnings("serial")
public class Menu_group extends JMenuItem implements ActionListener{
	
	Menu_group(){
		super("Group");
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Diagram_components comp = new Composite();
		int min_src_port_x = 1000, min_src_port_y = 1000;
		int max_des_port_x = 0, max_des_port_y = 0;
		for(Diagram_components dc : new ArrayList<Diagram_components>(UML_canvas.objects)){
			if(dc.isSelected){
				comp.group(dc);
				if(dc.x_cord < min_src_port_x){
					min_src_port_x = dc.x_cord;
				}
				if(dc.y_cord < min_src_port_y){
					min_src_port_y = dc.y_cord;
				}
				if(dc.x_cord+dc.object_width > max_des_port_x){
					max_des_port_x = dc.x_cord+dc.object_width;
				}
				if(dc.y_cord + dc.object_height > max_des_port_y){
					max_des_port_y = dc.y_cord + dc.object_height;
				}
			}
		}
		comp.x_cord = min_src_port_x;
		comp.y_cord = min_src_port_y;
		comp.object_height = max_des_port_y - min_src_port_y;
		comp.object_width = max_des_port_x - min_src_port_x;
		UML_canvas.objects.add(comp);
	}
}
