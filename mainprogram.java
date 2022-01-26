import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.event.*;
public class mainprogram implements KeyListener, ActionListener{
	// properties
	JFrame theframe = new JFrame("Four Skipping Iced Rats");
	Timer thetimer = new Timer(1000/60, this);
	// panels
	mainmenu1panel mainmenu1 = new mainmenu1panel();
	mainmenu2panel mainmenu2 = new mainmenu2panel();
	JButton readybut = new JButton("Ready");
	SuperSocketMaster ssm;
	int intPlayer;
	
	// methods
	public void keyReleased(KeyEvent evt){
		
	}
	public void keyPressed(KeyEvent evt){
		
	}
	public void keyTyped(KeyEvent evt){
		
	}
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == thetimer){
			
		}
		if(evt.getSource() == readybut){
			theframe.setContentPane(mainmenu2);
			theframe.pack();
			
		}
	}
	public void paintComponent(Graphics g){
		
	}
	
	// constructor
	public mainprogram(){
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.setContentPane(mainmenu1);
		theframe.pack();
		theframe.setResizable(false);
		theframe.setVisible(true);
		theframe.addKeyListener(this);
		
		// readybut
		readybut.setSize(150, 50);
		readybut.setLocation(1100, 650);
		readybut.addActionListener(this);
		theframe.add(readybut);
		
	}
	
	// main
	public static void main(String[] args){
		new mainprogram();
	}

}
