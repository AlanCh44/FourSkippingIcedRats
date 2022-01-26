import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.event.*;
public class charactermovementprototype implements KeyListener, ActionListener{
	//properties
	JFrame theframe = new JFrame("Testing movement");
	//animationpanel thepanel = new animationpanel();
	level1 thepanel = new level1();
	Timer thetimer = new Timer(1000/60, this);
	long jumpingtime = 200;
	String strColor = "gray"; //player will be assigned a colour depending on when they join. However, server is always gray
	//****need one class that represents an object in the game
	
	//keeps track of which panel player is in
	String strPanel;
	
	//if(thepanel.equals) ... data will be taken from mainmenu panels
	
	//stops characters from moving after key release
	public void keyReleased(KeyEvent evt){
		
		if(evt.getKeyChar() == 'a'){
			thepanel.intGrayDefX = 0;
			
		}else if(evt.getKeyChar() == 'd'){
			thepanel.intGrayDefX = 0;
			
		}
		
	}
	
	//character movement from keys
	public void keyPressed(KeyEvent evt){
		if(evt.getKeyChar() == 'a'){
			thepanel.intGrayDefX = -3;
		}else if(evt.getKeyChar() == 'd'){
			thepanel.intGrayDefX = 3;
		}else if(evt.getKeyChar() == 'w'){
			thepanel.blnJumpGray = true;
			//starts thread method
			new Thread(new thread()).start();
		}
		
		
	}
	
	public void keyTyped(KeyEvent evt){
		
		
	}
	
	public void paintComponent(Graphics g){
		
	}
	
	public void actionPerformed(ActionEvent evt){
		
	}
	
	public class thread implements Runnable{
		public void run(){
			try{
				//disables jump over certain time (prevents float)
				Thread.sleep(jumpingtime);
				thepanel.blnJumpGray = false;
				
			}catch(InterruptedException e){
				
				e.printStackTrace();
				new Thread(this).start();
				System.exit(0);
				
			}
		}
	}
	
	//constructor
	public charactermovementprototype(){
		
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.setContentPane(thepanel);
		theframe.pack();
		theframe.setResizable(false);
		theframe.setVisible(true);
		
		theframe.addKeyListener(this);
	}
	
	//main method
	public static void main(String[] args){
		new charactermovementprototype();
	}



}
