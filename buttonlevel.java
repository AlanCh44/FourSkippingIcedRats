import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.event.*;

// Need to add ssm and/or smth to seperate controls for host and different clients
// Need to add chat
public class buttonlevel implements KeyListener, ActionListener {
	// Properties
	JFrame theframe = new JFrame("Button Level");
	buttonpanel thepanel = new buttonpanel();
	long jumpingtime = 200;
	boolean blnJumping;

	String strPanel;

	// Methods
	public void keyReleased(KeyEvent evt) {
		if(evt.getKeyChar() == 'a'){
			thepanel.intGrayDefX = 0;
		} else if(evt.getKeyChar() == 'd') {
			thepanel.intGrayDefX = 0;
		} else if(evt.getKeyChar() == 'w') {
			thepanel.blnJump = false;
		}
	}
	public void keyPressed(KeyEvent evt) {
		if(evt.getKeyChar() == 'a') {
			thepanel.intGrayDefX = -3;
		} else if(evt.getKeyChar() == 'd') {
			thepanel.intGrayDefX = 3;
		} else if(evt.getKeyChar() == 'w') {
			thepanel.blnJump = true;
			//starts thread method
			new Thread(new thread()).start();
		}
	}
	public void keyTyped(KeyEvent evt) {
	}
	public void paintComponent(Graphics g) {
	}
	public void actionPerformed(ActionEvent evt) {	
	}
	public class thread implements Runnable { 
		public void run() {
			try {
				//disables jump over certain time (prevents float)
				Thread.sleep(jumpingtime);
				thepanel.blnJump = false;
			} catch(InterruptedException e) {
				e.printStackTrace();
				new Thread(this).start();
				System.exit(0);
			}
		}
	}

	// Constructor
	public buttonlevel() {
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.setContentPane(thepanel);
		theframe.pack();
		theframe.setResizable(false);
		theframe.setVisible(true);

		theframe.addKeyListener(this);
	}
	
	// Main
	public static void main(String[] args){
		new buttonlevel();
	}
}
