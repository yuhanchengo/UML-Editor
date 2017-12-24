package uml_editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class Menu_ungroup extends JMenuItem implements ActionListener{
	Menu_ungroup(){
		super("Ungroup");
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("ungroup");
//		for(Basic_object bo : UML_canvas.selected_object.compos_objects){
//			canvas.objects.add(bo);
//		}
//		canvas.objects.remove(UML_canvas.selected_object);
//		System.out.println("length of objects after ungroup: " + canvas.objects.size());
		
	}
}
