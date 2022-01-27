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
	//Timer thetimer = new Timer(1000/60, this);
	// panels
	mainmenu1panel mainmenu1 = new mainmenu1panel();
	mainmenu2panel mainmenu2 = new mainmenu2panel();
	level1 level1panel = new level1();
	losescreenpanel losescreen = new losescreenpanel();
	animationpanel helpscreen = new animationpanel();
	levelselect levelselectscreen = new levelselect();
	JButton readybut = new JButton("Ready");
	JButton thehelpscreenbut = new JButton("Help");
	JButton playgame = new JButton("Play");
	JButton levelselectbut = new JButton("Level Select");
	JButton level1but = new JButton("Level 1");
	JButton level2but = new JButton("Level 2");
	JButton level3but = new JButton("Level 3");
	String strCurrentPanel = ""; //keeps track of current panel
	
	//part of jump physics
	long jumpingtime = 200;
	
	SuperSocketMaster ssm;
	int intPlayer;
	
	// methods
	public void keyReleased(KeyEvent evt){
		if(evt.getKeyChar() == 'a'){
			if(strCurrentPanel.equals("help")){
				helpscreen.intGrayDefX = 0;
			}else if(strCurrentPanel.equals("level1")){
				level1panel.intGrayDefX = 0;
			}
			
		}else if(evt.getKeyChar() == 'd'){
			if(strCurrentPanel.equals("help")){
				helpscreen.intGrayDefX = 0;
			}else if(strCurrentPanel.equals("level1")){
				level1panel.intGrayDefX = 0;
			}
			
		}
	}
	public void keyPressed(KeyEvent evt){
		if(evt.getKeyChar() == 'a'){
			if(strCurrentPanel.equals("help")){
				helpscreen.intGrayDefX = -3;
			}else if(strCurrentPanel.equals("level1")){
				level1panel.intGrayDefX = -3;
			}
		}else if(evt.getKeyChar() == 'd'){
			helpscreen.intGrayDefX = 3;
		}else if(evt.getKeyChar() == 'w'){
			helpscreen.blnJump = true;
			//starts thread method
			new Thread(new thread()).start();
		}
	}
	public void keyTyped(KeyEvent evt){
		
	}
	public void actionPerformed(ActionEvent evt){
		
		//ready button sends user to second menu screen
		if(evt.getSource() == readybut){
			theframe.setContentPane(mainmenu2);
			theframe.pack();
			System.out.println("user: "+mainmenu1.strUser);
			theframe.add(thehelpscreenbut);
			if(mainmenu1.blnHost == true){
				theframe.add(playgame);
				theframe.add(levelselectbut);
			}
		}
		
		//selecting playgame brings players to level 1
		else if(evt.getSource() == playgame){
			strCurrentPanel = "level1";
			theframe.setContentPane(level1panel);
			theframe.pack();
			theframe.requestFocus();
		}
		
		//selecting help brings players to tutorial
		else if(evt.getSource() == thehelpscreenbut){
			strCurrentPanel = "help";
			theframe.setContentPane(helpscreen);
			theframe.pack();
			theframe.requestFocus();
		}
		else if(evt.getSource() == levelselectbut){
			theframe.setContentPane(levelselectscreen);
			theframe.pack();
			theframe.requestFocus();
			theframe.add(level1but);
			theframe.add(level2but);
			theframe.add(level3but);
		}
		
		else if(evt.getSource() == level1but){
			strCurrentPanel = "help";
			theframe.setContentPane(level1panel);
			theframe.pack();
			theframe.requestFocus();
		}
	}
	
	public void paintComponent(Graphics g){
		
	}
	
	//part of jumping
	public class thread implements Runnable{
		public void run(){
			try{
				//disables jump over certain time (prevents float)
				Thread.sleep(jumpingtime);
				helpscreen.blnJump = false;
				
			}catch(InterruptedException e){
				
				e.printStackTrace();
				new Thread(this).start();
				System.exit(0);
				
			}
		}
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
		
		//help screen button from mainmenu2
		thehelpscreenbut.setSize(350, 70);
		thehelpscreenbut.setLocation(46, 20);
		thehelpscreenbut.addActionListener(this);
		
		//play button from mainmenu2
		playgame.setSize(350, 70);
		playgame.setLocation(442, 20);
		playgame.addActionListener(this);
	
		//level button from mainmenu2
		levelselectbut.setSize(350, 70);
		levelselectbut.setLocation(838, 20);
		levelselectbut.addActionListener(this);
		//theframe.add(levelselectbut);
		
		//level 1,2,and 3 buttons from level select
		level1but.setSize(310, 30);
		level1but.setLocation(117, 570);
		level1but.addActionListener(this);
		
		level2but.setSize(310, 30);
		level2but.setLocation(508, 570);
		level2but.addActionListener(this);
		
		level3but.setSize(310, 30);
		level3but.setLocation(895, 570);
		level3but.addActionListener(this);
		
		
		theframe.addKeyListener(this);
		
		
		
	}
	
	// main
	public static void main(String[] args){
		new mainprogram();
	}

}
