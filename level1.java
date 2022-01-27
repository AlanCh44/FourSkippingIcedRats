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
	int intGreenX = 0;
	int intGreenY = 520;
	int intYellowX = 0;
	int intYellowY = 520;
	int intPurpleX = 0;
	int intPurpleY = 520;
	int intGrayDefX = 0;
	int intGrayDefY = 0;
	int intYellowDefX = 0;
	int intYellowDefY = 0;
	int intGreenDefX = 0;
	int intGreenDefY = 0;
	int intPurpleDefX = 0;
	int intPurpleDefY = 0;
	boolean blnJumpGreen = false;
	boolean blnJumpGray = false;
	boolean blnJumpYellow = false;
	boolean blnJumpPurple = false;
	
	Rectangle theplatform = new Rectangle();
	Rectangle yellowplat = new Rectangle();
	
	//methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == thetimer){
			this.repaint();
	
		}
	}
	
	public void paintComponent(Graphics g){
		//rectangle object for each player (used to keep track of each players location and interactions
		
		//character image and platform drawings
		g.drawImage(background, 0, 0, null);
		g.drawImage(grayrat, intGrayX, intGrayY, null);
		g.drawImage(greenrat, intGreenX, intGreenY, null);
		g.drawImage(purplerat, intPurpleX, intPurpleY, null);
		g.drawImage(yellowrat, intYellowX, intYellowY, null);
		
		Rectangle grayratrect = new Rectangle(intGrayX, intGrayY, 100, 100);
		Rectangle yellowratrect = new Rectangle(intYellowX, intYellowY, 100, 100);
		Rectangle purpleratrect = new Rectangle(intPurpleX, intPurpleY, 100, 100);
		Rectangle greenratrect = new Rectangle(intGreenX, intGreenY, 100, 100);
		
	
		
		//character deflections
		intGrayX = intGrayX + intGrayDefX;
		intGrayY = intGrayY + intGrayDefY;
		intGreenX = intGreenX + intGreenDefX;
		intGreenY = intGreenY + intGreenDefY;
		intPurpleX = intPurpleX + intPurpleDefX;
		intPurpleY = intPurpleY + intPurpleDefY;
		intYellowX = intYellowX + intYellowDefX;
		intYellowY = intYellowY + intYellowDefY;
		
		//stacking
		//purple gray 
		if(purpleratrect.intersects(grayratrect)){
			if(intPurpleY < intGrayY){
				intPurpleY = intGrayY - 100;
			}else if(intPurpleY > intGrayY){
				intGrayY = intPurpleY - 100;
			}else{
				intGrayY = intPurpleY - 100;
			}
		}
		//purple green
		if(purpleratrect.intersects(greenratrect)){
			if(intPurpleY < intGreenY){
				intPurpleY = intGreenY - 100;
			}else if(intPurpleY > intGreenY){
				intGreenY = intPurpleY - 100;
			}else{
				intGreenY = intPurpleY - 100;
			}
		}
		//purple yellow
		if(purpleratrect.intersects(yellowratrect)){
			if(intPurpleY < intYellowY){
				intPurpleY = intYellowY - 100;
			}else if(intPurpleY > intYellowY){
				intYellowY = intPurpleY - 100;
			}else{
				intPurpleY = intYellowY - 100;
			}
		}
		//yellow gray
		if(grayratrect.intersects(yellowratrect)){
			if(intGrayY < intYellowY){
				intGrayY = intYellowY - 100;
			}else if(intGrayY > intYellowY){
				intYellowY = intGrayY - 100;
			}else{
				intGrayY = intYellowY - 100;
			}
		}
		
		//yellow green
		if(greenratrect.intersects(yellowratrect)){
			if(intGreenY < intYellowY){
				intGreenY = intYellowY - 100;
			}else if(intGreenY > intYellowY){
				intYellowY = intGreenY - 100;
			}else{
				intGreenY = intYellowY - 100;
			}
		}
		
		//gray green
		if(greenratrect.intersects(grayratrect)){
			if(intGreenY < intGrayY){
				intGreenY = intGrayY - 100;
			}else if(intGreenY > intGrayY){
				intGrayY = intGreenY - 100;
			}else{
				intGreenY = intGrayY - 100;
			}
		}
		
		//general floor
		if(intGrayY > 520){
			intGrayY = 520;
			intGrayDefY = 0;
		}
		if(intGreenY > 520){
			intGreenY = 520;
			intGreenDefY = 0;
		}
		if(intPurpleY > 520){
			intPurpleY = 520;
			intPurpleDefY = 0;
		}
		if(intYellowY > 520){
			intYellowY = 520;
			intYellowDefY = 0;
		}
		
		//jumping physics
		if(blnJumpGray == true){
			intGrayDefY = -10;
		}else if(blnJumpGray == false){
			intGrayDefY = 10;
		}
		
		if(blnJumpGreen == true){
			intGreenDefY = -10;
		}else if(blnJumpGreen == false){
			intGreenDefY = 10;
		}
		
		if(blnJumpPurple == true){
			intPurpleDefY = -10;
		}else if(blnJumpPurple == false){
			intPurpleDefY = 10;
		}
		
		if(blnJumpYellow == true){
			intYellowDefY = -10;
		}else if(blnJumpYellow == false){
			intYellowDefY = 10;
		}
		
		//moving platform/air
		
		if(intGrayX > 138 && intGrayX < 450){
			intGrayY = 310;
		}
		
		if(intGreenX > 138 && intGreenX < 450){
			intGreenY = 310;
		}
		
		if(intPurpleX > 138 && intPurpleX < 450){
			intPurpleY = 310;
		}
		
		if(intYellowX > 138 && intYellowX < 450){
			intYellowY = 310;
		}
		
		
		//yellow platform
		
		if(intYellowX > 444 && intYellowX < 636 && intYellowY < 350){
			if(intYellowY > 310){
				intYellowY = 310;
			}
		}
		
		//purple platform
		
		if(intPurpleX > 615 && intPurpleX < 772 && intPurpleY < 264){
			if(intPurpleY > 244){
				intPurpleY = 244;
			}
		}
		
		//gray platform
		if(intGrayX > 753 && intGrayX < 1089 && intGrayY < 226){
			if(intGrayY > 196){
				intGrayY = 196;
			}
		}
		
		//green platform
		
		if(intGreenX > 1050 && intGreenX < 1280 && intGreenY < 60){
			if(intGreenY > 40){
				intGreenY = 40;
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
