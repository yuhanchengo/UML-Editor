
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
/*
 * The main frame of the UML editor applicaiton
 */

public class UML_editor extends JFrame {
	public static String mode;
	public ArrayList<JButton> buttons = new ArrayList<JButton>();
	private UML_canvas canvas= new UML_canvas();
	// pre_setting in constructor
	UML_editor() {
		// this.setLayout(new BorderLayout());

		// Menu Bar of frame
		JMenuBar menuBar = new JMenuBar();
		JMenu File = new JMenu("File");
		menuBar.add(File);
		JMenu Edit = new JMenu("Edit");
		menuBar.add(Edit);
		JMenuItem group = new JMenuItem("Group");
		JMenuItem chg_obj_name = new JMenuItem("Change Object Name");
		JMenuItem ungroup = new JMenuItem("UnGroup");
		Edit.add(group);
		Edit.add(chg_obj_name);
		Edit.add(ungroup);
		group.addActionListener(new MenuActionListener(canvas));
		chg_obj_name.addActionListener(new MenuActionListener(canvas));
		ungroup.addActionListener(new MenuActionListener(canvas));
		
		// button panel setting
		JPanel btnPanel = new JPanel();
		btnPanel.setPreferredSize(new Dimension(120, 1000));
		// btnPanel.setBackground(Color.GREEN);
		GridLayout gridlayout = new GridLayout(6, 1);
		btnPanel.setLayout(gridlayout);
		JButton select = createButton("SELECT");
		buttons.add(select);
		JButton assoc = createButton("ASSOC");
		buttons.add(assoc);
		JButton gener = createButton("GENER");
		buttons.add(gener);
		JButton compos = createButton("COMPOS");
		buttons.add(compos);
		JButton Class = createButton("CLASS");
		buttons.add(Class);
		JButton usecase = createButton("USE_CASE");
		buttons.add(usecase);
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).addActionListener(new BtnListener());
			btnPanel.add(buttons.get(i));
		}

		// splitPane to split canvas and btnPanel
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setLeftComponent(btnPanel);
		splitPane.setRightComponent(canvas);
		splitPane.setResizeWeight(0.05);

		// add components to frame
		// this.add(btnPanel, BorderLayout.WEST);
		// this.add(new UML_canvas(), BorderLayout.EAST);
		this.add(splitPane, BorderLayout.CENTER);
		this.setJMenuBar(menuBar);
	}

	

	public static void main(String[] args) {
		UML_editor editor = new UML_editor();
		editor.setWindowProperty();
	}
	
	private static JButton createButton(String Name) {
		JButton btn = new JButton(Name);
		btn.setName(Name);
		btn.setPreferredSize(new Dimension(100, 100));
		return btn;
	}
	
	public void setWindowProperty() {
		setTitle("UML Editor");
		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	public class BtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JButton) {
				// clear other buttons' color
				resetButton();
				JButton btn = ((JButton) e.getSource());
				btn.setBackground(Color.black);
				btn.setForeground(Color.black);
				btn.setOpaque(true);
				mode = e.getActionCommand();
				

			}

		}
	}

	// menuActionListener for menuItem
	public class MenuActionListener implements ActionListener {
		UML_canvas canvas;
		MenuActionListener(UML_canvas canvas){
			this.canvas = canvas;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Change Object Name") && UML_canvas.selectionMode==1){
				JOptionPane chgNamePane = new JOptionPane();
//				chgNamePane.showConfirmDialog(null, "change or not", "Object Name alternation", JOptionPane.OK_CANCEL_OPTION);
				String rename = JOptionPane.showInputDialog(null, "Enter object new name: ");
				if(rename!=null){
					UML_canvas.selected_object.name = rename;
				}
				repaint();
				UML_canvas.selected_object = null;
			}else if(e.getActionCommand().equals("Group") && UML_canvas.selectionMode==2){
				Basic_object comp = new Composite();
				int min_src_port_x = 1000, min_src_port_y = 1000;
				int max_des_port_x = 0, max_des_port_y = 0;
				for(Basic_object bo : new ArrayList<Basic_object>(canvas.objects)){
					if(bo.select){
						comp.compos_objects.add(bo);
						canvas.objects.remove(bo);
						comp.depth = bo.depth;
						if(bo.x_cord < min_src_port_x){
							min_src_port_x = bo.x_cord;
						}
						if(bo.y_cord < min_src_port_y){
							min_src_port_y = bo.y_cord;
						}
						if(bo.x_cord+bo.object_width > max_des_port_x){
							max_des_port_x = bo.x_cord+bo.object_width;
						}
						if(bo.y_cord + bo.object_height > max_des_port_y){
							max_des_port_y = bo.y_cord + bo.object_height;
						}
					}
				}
				comp.x_cord = min_src_port_x;
				comp.y_cord = min_src_port_y;
				comp.object_height = max_des_port_y - min_src_port_y;
				comp.object_width = max_des_port_x - min_src_port_x;
				canvas.objects.add(comp);
				// check if composed
				System.out.println("length of objects after group : " + canvas.objects.size());
//				System.out.println(comp.depth);
//				for(Basic_object obj : comp.compos_objects){
//					System.out.println(obj.depth);
//				}
				
//				System.out.println("group");
			}else if(e.getActionCommand().equals("UnGroup") && UML_canvas.selectionMode==3){
				for(Basic_object bo : UML_canvas.selected_object.compos_objects){
					canvas.objects.add(bo);
				}
				canvas.objects.remove(UML_canvas.selected_object);
				System.out.println("length of objects after ungroup: " + canvas.objects.size());
			}
		}

	}

	public void resetButton() {
		/* 把所有的btn都設成沒有被選取 */
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).setBackground(Color.white);
			buttons.get(i).setOpaque(true);
		}
		// reset click_count when mode changed
		UML_canvas.click_count = 0;
	}

}
