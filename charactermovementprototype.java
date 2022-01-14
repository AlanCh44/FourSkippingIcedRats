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
	animationpanel thepanel = new animationpanel();
	BufferedImage yellowrat = null;
	BufferedImage greenrat = null;
	Timer thetimer = new Timer(1000/60, this);
	long jumpingtime = 200;
	
	
	
	//methods
	
	
	
	public void keyReleased(KeyEvent evt){
		
		if(evt.getKeyChar() == 'a'){
			thepanel.intGrayDefX = 0;
		}else if(evt.getKeyChar() == 'd'){
			thepanel.intGrayDefX = 0;
		}
		
	}
	
	public void keyPressed(KeyEvent evt){
		
		if(evt.getKeyChar() == 'a'){
			thepanel.intGrayDefX = -3;
		}else if(evt.getKeyChar() == 'd'){
			thepanel.intGrayDefX = 3;
		
		}else if(evt.getKeyChar() == 'w'){
			thepanel.blnJump = true;
			//starts thread method
			new Thread(new thread()).start();
			
			//thepanel.intGrayDefY = -10;
			
		}
		
		
	}
	
	public void keyTyped(KeyEvent evt){
		/*
		if(evt.getKeyChar() == 'w'){
			thepanel.blnJump = true;
			new Thread(new thread()).start();
		}
		*/
		
		
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1280, 720);
		g.drawImage(yellowrat, 0, 0, null);
		
	}
	
	public void actionPerformed(ActionEvent evt){
		
	}
	
	public class thread implements Runnable{
		public void run(){
			try{
				//disables jump over certain time (prevents float)
				Thread.sleep(jumpingtime);
				thepanel.blnJump = false;
				
			}catch(InterruptedException e){
				
				e.printStackTrace();
				new Thread(this).start();
				System.exit(0);
				
			}
		}
	}
	
	
	
	
	//constructor
	public charactermovementprototype(){
		
		//thepanel.setLayout(null);
		//thepanel.setPreferredSize(new Dimension(1280, 720));
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