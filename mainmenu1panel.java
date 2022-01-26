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
		//ssm
		if(evt.getSource() == ssm){
			/// Split
			String strLine = ssm.readText();
			strLineSplit = strLine.split(",");
			
			if(strLineSplit[0].equals("Connect")){
				if(blnHost == true){
					intPlayerCount = intPlayerCount + 1;
					System.out.println("Players Count: "+intPlayerCount);
					if(intPlayerCount == 2){
						readybut.setEnabled(true);
					}
				}
			}
		}
		/// Username
		if(evt.getSource() == usernameinput){
			strUser = usernameinput.getText();
			usernameinput.setEnabled(false);
			clientbut.setEnabled(true);
			serverbut.setEnabled(true);
		/// Server
		}else if(evt.getSource() == serverbut){
			ssm = new SuperSocketMaster(2188, this);
			IPinput.setText(ssm.getMyAddress());
			ssm.connect();
			blnHost = true;
			serverbut.setEnabled(false);
			clientbut.setEnabled(false);
		/// Client
		}else if(evt.getSource() == clientbut){
			serverbut.setEnabled(false);
			clientbut.setEnabled(false);
			IPinput.setEnabled(true);
			blnHost = false;
		}else if(evt.getSource() == IPinput){
			strIP = IPinput.getText();
			ssm = new SuperSocketMaster(strIP, 2188, this);
			ssm.connect();
			ssm.sendText("Connect,"+strUser);
			IPinput.setEnabled(false);
		}else if(evt.getSource() == readybut){
			blnReady = true;
			System.out.println("READY");
		
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
	
	
		// serverbut
		serverbut.setSize(150, 50);
		serverbut.setLocation(465, 650);
		serverbut.addActionListener(this);
		this.add(serverbut);
		serverbut.setEnabled(false);
		
		// readybut
		readybut.setSize(150, 50);
		readybut.setLocation(1100, 650);
		readybut.addActionListener(this);
		
		readybut.setEnabled(false);
		
		// clientbut
		clientbut.setSize(150, 50);
		clientbut.setLocation(665, 650);
		clientbut.addActionListener(this);
		this.add(clientbut);
		clientbut.setEnabled(false);
		
		// IPinput
		IPinput.setSize(300, 50);
		IPinput.setLocation(750, 50);
		IPinput.addActionListener(this);
		this.add(IPinput);
		IPinput.setEnabled(false);
		
		// IPlabel
		IPlabel.setSize(120, 50);
		IPlabel.setLocation(650, 50);
		this.add(IPlabel);
		
		// usernameinput
		usernameinput.setSize(300, 50);
		usernameinput.setLocation(225, 50);
		usernameinput.addActionListener(this);
		this.add(usernameinput);
		
		// theusername
		theusername.setSize(120, 50);
		theusername.setLocation(150, 50);
		this.add(theusername);
		
		
		
	
		try{
			background = ImageIO.read(new File("Menu design(without rats).png"));
		}catch(IOException e){
			System.out.println("Unable to load image file");
		}
	}
	




}
