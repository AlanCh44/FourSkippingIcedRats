import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class mynetapp2 implements ActionListener{
	// Properties
	JFrame theframe = new JFrame("Basic Server Net App");
	JPanel thepanel = new JPanel();
	JLabel thelabel = new JLabel("Text will appear here");
	SuperSocketMaster ssm = new SuperSocketMaster(6112, this);
	// Methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == ssm){
			thelabel.setText(ssm.readText());
		}
	}
	
	// Constructor
	public mynetapp2(){
		thepanel.add(thelabel);
		thepanel.setPreferredSize(new Dimension(300, 300));
		theframe.setContentPane(thepanel);
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.pack();
		theframe.setVisible(true);
		ssm.connect();
	}
	// Main
	public static void main(String[] args){
		new mynetapp2();
	}
}
