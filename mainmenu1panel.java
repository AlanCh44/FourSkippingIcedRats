import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.event.*;
import java.awt.image.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class mainmenu1panel extends JPanel implements ActionListener{
	// properties
	private mainprogram myMainForm;
	Timer thetimer = new Timer(1000/60, this);
	SuperSocketMaster ssm;
	JButton serverbut = new JButton("Server Mode");
	JButton readybut = new JButton("Ready");
	JButton clientbut = new JButton("Client Mode");
	JTextField IPinput = new JTextField();
	JTextField usernameinput = new JTextField();
	JLabel theusername = new JLabel("Username:");
	JLabel IPlabel = new JLabel("Server Address:");
	Font thefont = new Font("Courier New", Font.PLAIN, 125);
	BufferedImage background = null;
	String strUser;
	boolean blnHost = true;
	int intPlayerCount = 1;
	String strLineSplit[];
	String strIP;
	boolean blnReady = false;
	// methods
	public void actionPerformed(ActionEvent evt){
		/// repaint
		if(evt.getSource() == thetimer){
			this.repaint();
		}
	}
	
	
	public void paintComponent(Graphics g){
		g.drawImage(background, 0, 0, null);
	}
	
	
	// contructor
	public mainmenu1panel(){
		super();
		this.setPreferredSize(new Dimension(1280, 720));
		this.setLayout(null);
		thetimer.start();
	
	
		
		
	
		try{
			background = ImageIO.read(new File("Menu design(without rats).png"));
		}catch(IOException e){
			System.out.println("Unable to load image file");
		}
	}
	




}
