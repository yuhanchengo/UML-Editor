package Button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Button extends JButton implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Button(String name){
		JButton btn = new JButton(name);
//		btn.setIcon(img);
		btn.setName(name);
		btn.setPreferredSize(new Dimension(100, 100));
		this.addActionListener(this);
	}
	public Button(ImageIcon img, String name){
		JButton btn = new JButton(name);
		btn.setIcon(img);
		btn.setName(name);
		btn.setPreferredSize(new Dimension(100, 100));
		this.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	public void resetButton(){
		this.setBackground(Color.WHITE);
		this.setOpaque(true);
	}
	

}
