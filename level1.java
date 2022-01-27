import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.event.*;
public class level1 extends JPanel implements ActionListener{
	//need to use arrays
	
	//properties
	BufferedImage yellowrat = null;
	BufferedImage grayrat = null;
	BufferedImage greenrat = null;
	BufferedImage purplerat = null;
	BufferedImage background = null;
	Timer thetimer = new Timer(1000/60, this);
	
	//character position variables
	int intGrayX = 0;
	int intGrayY = 520;
	int intGrayDefX = 0;
	int intGrayDefY = 0;
	int intGreenX = 0;
	int intGreenY = 520;
	int intGreenDefX = 0;
	int intGreenDefY = 0;
	int intYellowX = 0;
	int intYellowY = 520;
	int intYellowDefX = 0;
	int intYellowDefY = 0;
	int intPurpleX = 0;
	int intPurpleY = 520;
	int intPurpleDefX = 0;
	int intPurpleDefY = 0;
	int intplatY = 585;
	boolean blnJumpGray = false;
	
	Rectangle theplatform = new Rectangle();
	Rectangle yellowplat = new Rectangle();
	
	//methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == thetimer){
			this.repaint();
			/*
			System.out.println("X: "+intGrayX);
			System.out.println("Y: "+intGrayY);
			System.out.println("jump: "+blnJumpGray);
			*/
		}
	}
	
	public void paintComponent(Graphics g){
		//rectangle object for each player (used to keep track of each players location and interactions
		Rectangle grayratrect = new Rectangle(intGrayX, intGrayY, 100, 100);
		Rectangle yellowratrect = new Rectangle(intYellowX, intYellowY, 100, 100);
		Rectangle purpleratrect = new Rectangle(intYellowX, intYellowY, 100, 100);
		Rectangle greenratrect = new Rectangle(intYellowX, intYellowY, 100, 100);
		
		//character image and platform drawings
		g.drawImage(background, 0, 0, null);
		g.drawImage(grayrat, intGrayX, intGrayY, null);
		g.drawImage(greenrat, intGreenX, intGreenY, null);
		//drawplatform
		g.setColor(new Color(230, 145, 56));
		g.fillRect(200, intplatY, 270, 35);
		theplatform = new Rectangle(200, intplatY, 270, 35);
		intGrayX = intGrayX + intGrayDefX;
		intGrayY = intGrayY + intGrayDefY;
		intGreenX = intGreenX + intGreenDefX;
		intGreenY = intGreenY + intGreenDefY;
		
		//general floor
		if(intGrayY > 520){
			intGrayY = 520;
			intGrayDefY = 0;
			
		}
		
		//jumping physics
		if(blnJumpGray == true){
			intGrayDefY = -10;
		}else if(blnJumpGray == false){
			intGrayDefY = 10;
		}
		
		//moving platform
		if((intGrayX > 138 && intGrayX < 450 && intGrayY < intplatY) || (intGreenX > 138 && intGreenX < 450 && intGreenY < intplatY)||(intPurpleX > 138 && intPurpleX < 450 && intPurpleY < intplatY)||(intYellowX > 138 && intYellowX < 450 && intYellowY < intplatY)){
			intGrayY = intplatY - 100;
			intplatY = intplatY - 5;
		
		//change else if statement later so that the platform will move back down if a single character isnt above it
		}else if(intGrayY > intplatY - 60){
			intplatY = intplatY + 5;
			if(intplatY > 585){
				intplatY = 585;
			}
		}
		
		//moving platform boundary
		if(intplatY <= 405){
			intplatY = 405;
		}
		
		//yellow platform
		if(intGrayX > 444 && intGrayX < 636 && intGrayY < 350){
			if(intGrayY > 310){
				intGrayY = 310;
			}
		}
		
		//purple platform
		if(intGrayX > 615 && intGrayX < 772 && intGrayY < 264){
			if(intGrayY > 244){
				intGrayY = 244;
			}
		}
		
		//gray platform
		if(intGrayX > 753 && intGrayX < 1089 && intGrayY < 226){
			if(intGrayY > 196){
				intGrayY = 196;
			}
		}
		
		//green platform
		if(intGrayX > 1050 && intGrayX < 1280 && intGrayY < 60){
			if(intGrayY > 40){
				intGrayY = 40;
			}
		}
		
		
	}
	
	
	//constructor
	public level1(){
		thetimer.start();
		
		this.setPreferredSize(new Dimension(1280, 720));
		this.setLayout(null);
		
		
		
		try{
			grayrat = ImageIO.read(new File("gray.png"));
		}catch(IOException e){
			System.out.println("Error file not found");
		}
		
		try{
			purplerat = ImageIO.read(new File("purple.png"));
		}catch(IOException e){
			System.out.println("Error file not found");
		}
		
		try{
			greenrat = ImageIO.read(new File("green.png"));
		}catch(IOException e){
			System.out.println("Error file not found");
		}
		
		try{
			yellowrat = ImageIO.read(new File("yellow.png"));
		}catch(IOException e){
			System.out.println("Error file not found");
		}
		
		try{
			background = ImageIO.read(new File("Colour R1 no rats.png"));
		}catch(IOException e){
			System.out.println("Error file not found");
		}
	
	}

}
