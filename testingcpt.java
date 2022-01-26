import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.event.*;
public class testingcpt implements ActionListener{
	//properties
	JFrame theframe = new JFrame("testing");
	//mainmenu2panel thepanel = new mainmenu2panel();
	//winscreenpanel thepanel = new winscreenpanel();
	losescreenpanel thepanel = new losescreenpanel();
	//JPanel thepanel = new JPanel();
	
	
	//methods
	public void actionPerformed(ActionEvent evt){
		
	}
	
	
	//constructor
	public testingcpt(){
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//thepanel.setPreferredSize(new Dimension(1280, 720));
		theframe.setContentPane(thepanel);
		theframe.pack();
		theframe.setVisible(true);
	}

	//main method
	public static void main(String[] args){
		new testingcpt();
	}
}
