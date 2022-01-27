import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.event.*;
import java.awt.image.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class mainmenu1panel extends JPanel implements ActionListener, ChangeListener{
	// properties
	private mainprogram myMainForm;
	Timer thetimer = new Timer(1000/60, this);
	SuperSocketMaster ssm;
	
	//JComponents
	JButton serverbut = new JButton("Server Mode");
	JButton readybut = new JButton("Ready");
	JButton clientbut = new JButton("Client Mode");
	JTextField IPinput = new JTextField();
	JTextField usernameinput = new JTextField();
	JLabel theusername = new JLabel("Username:");
	JLabel IPlabel = new JLabel("Server Address:");
	Font thefont = new Font("Courier New", Font.PLAIN, 125);
	
	String strUser;
	boolean blnHost = true;
	int intPlayerCount = 1;
	String strLineSplit[];
	String strIP;
	boolean blnReady = false;
	
	//images
	BufferedImage yellowrat = null;
	BufferedImage grayrat = null;
	BufferedImage greenrat = null;
	BufferedImage purplerat = null;
	BufferedImage background = null;
	
	//mini features JComponents
	JCheckBox thecheckbox = new JCheckBox("dissapear");
	JSlider theslider = new JSlider(-50,50);
	JLabel slidelabel = new JLabel("move the characters");
	
	
	
	//character variables
	int intGrayX;
	int intGrayY = 520;
	
	boolean blnDis = false;
	
	
	// methods
	public void actionPerformed(ActionEvent evt){
		/// repaint
		if(evt.getSource() == thetimer){
			this.repaint();
		}
		if(evt.getSource() == thecheckbox){
			System.out.println("CHECKED");
			if(blnDis == false){
				blnDis = true;
			}else if(blnDis == true){
				blnDis = false;
			}
		}
	}
	
	public void stateChanged(ChangeEvent evt){
		if(evt.getSource() == theslider){
			intGrayX = intGrayX + theslider.getValue();
		}
		
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(background, 0, 0, null);
		g.drawImage(grayrat, intGrayX, intGrayY, null);
		g.drawImage(yellowrat, intGrayX+200, intGrayY, null);
		g.drawImage(purplerat, intGrayX+400, intGrayY, null);
		g.drawImage(greenrat, intGrayX+600, intGrayY, null);
		if(blnDis == true){
			g.drawImage(background, 0, 0, null);
		}
		
	}
	
	
	// contructor
	public mainmenu1panel(){
		super();
		this.setPreferredSize(new Dimension(1280, 720));
		this.setLayout(null);
		thetimer.start();
	
		//loading images
		try{
			grayrat = ImageIO.read(new File("gray.png"));
			yellowrat = ImageIO.read(new File("yellow.png"));
			purplerat = ImageIO.read(new File("purple.png"));
			greenrat = ImageIO.read(new File("green.png"));
		}catch(IOException e){
			System.out.println("Error file not found");
		}
		
		//mini component features
		theslider.setLocation(170, 363);
		theslider.setSize(200, 30);
		theslider.addChangeListener(this);
		this.add(theslider);
		
		thecheckbox.setLocation(800, 363);
		thecheckbox.setSize(200, 20);
		thecheckbox.addActionListener(this);
		this.add(thecheckbox);
		
		slidelabel.setLocation(170, 394);
		slidelabel.setSize(150, 30);
		this.add(slidelabel);
		
	
		try{
			background = ImageIO.read(new File("Menu design(without rats).png"));
		}catch(IOException e){
			System.out.println("Unable to load image file");
		}
	}
	




}
