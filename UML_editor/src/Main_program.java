
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;


public class Main_program extends JFrame{
	// pre_setting in constructor
	Main_program(){
		// Menu Bar of frame
		JMenuBar menuBar = new JMenuBar();
		JMenu File = new JMenu("File");
		menuBar.add(File);
		JMenu Edit = new JMenu("Edit");
		menuBar.add(Edit);
		JMenuItem group = new JMenuItem("Group");
		JMenuItem chg_obj_name = new JMenuItem("Change Object Name");
		Edit.add(group);
		Edit.add(chg_obj_name);
		
		// button panel setting
		JComponent btnPanel = new JPanel();
		btnPanel.setBackground(Color.BLACK);
		GridLayout gridlayout = new GridLayout(6,1);
		btnPanel.setLayout(gridlayout);
		btnPanel.add(createButton("SELECT"));
		btnPanel.add(createButton("ASSOC"));
		btnPanel.add(createButton("GENER"));
		btnPanel.add(createButton("COMPOS"));
		btnPanel.add(createButton("CLASS"));
		btnPanel.add(createButton("USE_CASS"));
		
		// canvas panel setting
		JComponent cvsPanel = new JPanel();
		System.out.println(cvsPanel.getSize());
		cvsPanel.add(new UML_canvas());
		
		// splitPane to split canvas and btnPanel
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setLeftComponent(btnPanel);
		splitPane.setRightComponent(cvsPanel);
		splitPane.setResizeWeight(0.05);
		
		// add components to frame
		this.add(splitPane, BorderLayout.CENTER);
		this.setJMenuBar(menuBar);
		setWindowProperty();
	}
	private static JButton createButton(String Name){
		JButton btn = new JButton(Name);
		btn.setPreferredSize(new Dimension(100,100));
		return btn;
	}

	public static void main(String[] args) {
		Main_program editor = new Main_program();

	}
	public void setWindowProperty(){
		setTitle("UML Editor");
		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}

	
	
}
