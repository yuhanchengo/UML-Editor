package Button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Button extends JButton implements ActionListener{

	public Button(ImageIcon img, String name){
		setIcon(img);
		setName(name);
		setPreferredSize(new Dimension(100, 100));
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
