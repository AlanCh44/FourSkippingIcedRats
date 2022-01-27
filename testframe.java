import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
// By: Tristan Lai
// Date: 12/4/2021
// Version: 2.0

public class testframe implements KeyListener{
	// Properties
	JFrame theframe = new JFrame("Pong"); 
	laserR1panel thepanel = new laserR1panel();
	
	// Methods
	public void keyReleased(KeyEvent evt){
		
	}
	public void keyPressed(KeyEvent evt){
		
	}
	public void keyTyped(KeyEvent evt){
		
	}
	

	
	
	// Constructor
	public testframe(){
		theframe.addKeyListener(this);
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.setContentPane(thepanel);
		theframe.setResizable(false);
		theframe.pack();
		theframe.setVisible(true);
	}
	// Main
	public static void main(String[] args){
		new testframe();
	}

}
