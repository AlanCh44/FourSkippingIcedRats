import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.event.*;
import java.awt.image.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class level2 extends JPanel implements ActionListener{
	// properties
	BufferedImage yellowrat = null;
	BufferedImage grayrat = null;
	BufferedImage purplerat = null;
	BufferedImage greenrat = null;
	BufferedImage lazerimg = null;
	Timer thetimer = new Timer(1000/60, this);
	BufferedImage background = null;
	
	Rectangle thebox = new Rectangle(364, 519, 100, 100);

	// gray rat position
	int intGrayX = 0;
	int intGrayY = 520;
	int intGrayDefX = 0;
	int intGrayDefY = 0;
	int intGrayYPre = 0;
	// yellow rat position
	int intYellowX = 0;
	int intYellowY = 520;
	int intYellowDefX = 0;
	int intYellowDefY = 0;
	// purple rat posiiton
	int intPurpleX = 0;
	int intPurpleY = 520;
	int intPurpleDefX = 0;
	int intPurpleDefY = 0;
	// green rat position
	int intGreenX = 0;
	int intGreenY = 520;
	int intGreenDefX = 0;
	int intGreenDefY = 0;
	
	boolean blnJumpGreen = false;
	boolean blnJumpGray = false;
	boolean blnJumpYellow = false;
	boolean blnJumpPurple = false;
	Rectangle theground = new Rectangle(0, 620, 1280, 100);
	/// if player touches lazer, reset player position
	Rectangle lazer = new Rectangle();
	Rectangle theplatform = new Rectangle();
	
	// methods
	public void actionPerformed(ActionEvent evt){
		/// repaint
		if(evt.getSource() == thetimer){
			this.repaint();
		}
		
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(background, 0, 0, null);
		
		
		
		// rats
		Rectangle grayratrect = new Rectangle(intGrayX, intGrayY, 100, 100);
		Rectangle greenratrect = new Rectangle(intGreenX, intGreenY, 100, 100);
		Rectangle yellowratrect = new Rectangle(intYellowX, intYellowY, 100, 100);
		Rectangle purpleratrect = new Rectangle(intPurpleX, intPurpleY, 100, 100);
		g.drawImage(grayrat,intGrayX,intGrayY,null);
		g.drawImage(greenrat,intGreenX,intGreenY,null);
		g.drawImage(yellowrat,intYellowX,intYellowY,null);
		g.drawImage(purplerat,intPurpleX,intPurpleY,null);
		
		// boundries
		// the box
		Rectangle thebox = new Rectangle(365, 520, 100, 100);
		if(grayratrect.intersects(thebox)){
			if(intGrayDefX > 0){
				intGrayX = intGrayX - 5;
			}else if(intGrayDefX < 0){
				intGrayX = intGrayX + 5;
			}
		}
		if(intGrayX + 100 > 365 && intGrayX < 465 && intGrayY < 520 && intGrayY > 415 && blnJumpGray == false){
			intGrayY = 420;
			intGrayDefY = 0;
		}
		if(purpleratrect.intersects(thebox)){
			if(intPurpleDefX > 0){
				intPurpleX = intPurpleX - 5;
			}else if(intPurpleDefX < 0){
				intPurpleX = intPurpleX + 5;
			}
		}
		if(intPurpleX + 100 > 365 && intPurpleX < 465 && intPurpleY < 520 && intPurpleY > 415 && blnJumpPurple == false){
			intPurpleY = 420;
			intPurpleDefY = 0;
		}
		if(greenratrect.intersects(thebox)){
			if(intGreenDefX > 0){
				intGreenX = intGreenX - 5;
			}else if(intGreenDefX < 0){
				intGreenX = intGreenX + 5;
			}
		}
		if(intGreenX + 100 > 365 && intGreenX < 465 && intGreenY < 520 && intGreenY > 415 && blnJumpGreen == false){
			intGreenY = 420;
			intGreenDefY = 0;
		}
		if(yellowratrect.intersects(thebox)){
			if(intYellowDefX > 0){
				intYellowX = intYellowX - 5;
			}else if(intYellowDefX < 0){
				intYellowX = intYellowX + 5;
			}
		}
		if(intYellowX + 100 > 365 && intYellowX < 465 && intYellowY < 520 && intYellowY > 415 && blnJumpYellow == false){
			intYellowY = 420;
			intYellowDefY = 0;
		}
		// the high platform
		Rectangle thehighplat = new Rectangle(720, 465, 1000, 50);
		if(grayratrect.intersects(thehighplat)){
			if(intGrayDefX > 0){
				intGrayX = intGrayX - 5;
			}else if(intGrayDefX < 0){
				intGrayX = intGrayX + 5;
			}
		}
		if(purpleratrect.intersects(thehighplat)){
			if(intPurpleDefX > 0){
				intPurpleX = intPurpleX - 5;
			}else if(intPurpleDefX < 0){
				intPurpleX = intPurpleX + 5;
			}
		}
		if(greenratrect.intersects(thehighplat)){
			if(intGreenDefX > 0){
				intGreenX = intGreenX - 5;
			}else if(intGreenDefX < 0){
				intGreenX = intGreenX + 5;
			}
		}
		if(yellowratrect.intersects(thehighplat)){
			if(intYellowDefX > 0){
				intYellowX = intYellowX - 5;
			}else if(intYellowDefX < 0){
				intYellowX = intYellowX + 5;
			}
		}
		if(intGrayX + 100 > 720 && intGrayY > 510 && blnJumpGray == true){
			intGrayY = 520;
			blnJumpGray = false;
		}
		if(intPurpleX + 100 > 720 && intPurpleY > 510 && blnJumpPurple == true){
			intPurpleY = 520;
			blnJumpPurple = false;
		}
		if(intGreenX + 100 > 720 && intGreenY > 510 && blnJumpGreen == true){
			intGreenY = 520;
			blnJumpGreen = false;
		}
		if(intYellowX + 100 > 720 && intYellowY > 510 && blnJumpYellow == true){
			intYellowY = 520;
			blnJumpYellow = false;
		}
		
		
		if(intGrayX + 100 > 720 && intGrayY > 350 && intGrayY < 500 && blnJumpGray == false){
			intGrayY = 355;
			intGrayDefY = 0;
		}
		if(intPurpleX + 100 > 720 && intPurpleY > 350 && intPurpleY < 500 && blnJumpPurple == false){
			intPurpleY = 355;
			intPurpleDefY = 0;
		}
		if(intGreenX + 100 > 720 && intGreenY > 350 && intGreenY < 500 && blnJumpGreen == false){
			intGreenY = 355;
			intGreenDefY = 0;
		}
		if(intYellowX + 100 > 720 && intYellowY > 350 && intYellowY < 500 && blnJumpYellow == false){
			intYellowY = 355;
			intYellowDefY = 0;
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
		
		//character deflections
		intGrayX = intGrayX + intGrayDefX;
		intGrayY = intGrayY + intGrayDefY;
		intGreenX = intGreenX + intGreenDefX;
		intGreenY = intGreenY + intGreenDefY;
		intPurpleX = intPurpleX + intPurpleDefX;
		intPurpleY = intPurpleY + intPurpleDefY;
		intYellowX = intYellowX + intYellowDefX;
		intYellowY = intYellowY + intYellowDefY;
		
		//jumping physics
		if(blnJumpGray == true){
			intGrayDefY = -10;
		}else if(blnJumpGray == false && intGrayY != 520){
			intGrayDefY = 10;
		}
		
		if(blnJumpGreen == true){
			intGreenDefY = -10;
		}else if(blnJumpGreen == false && intGreenY != 520){
			intGreenDefY = 10;
		}
		
		if(blnJumpPurple == true){
			intPurpleDefY = -10;
		}else if(blnJumpPurple == false && intPurpleY != 520){
			intPurpleDefY = 10;
		}
		
		if(blnJumpYellow == true){
			intYellowDefY = -10;
		}else if(blnJumpYellow == false && intYellowY != 520){
			intYellowDefY = 10;
		}
	}
	// contructor
	public level2(){
		super();
		this.setPreferredSize(new Dimension(1280, 720));
		this.setLayout(null);
		thetimer.start();
		
		
		
		try{
			grayrat = ImageIO.read(new File("gray.png"));
			yellowrat = ImageIO.read(new File("yellow.png"));
			purplerat = ImageIO.read(new File("purple.png"));
			greenrat = ImageIO.read(new File("green.png"));
		}catch(IOException e){
			System.out.println("Error file not found");
		}
		
		try{
			background = ImageIO.read(new File("laser R1 (no rats).png"));
		}catch(IOException e){
			System.out.println("Unable to load image file");
		}
		try{
			lazerimg = ImageIO.read(new File("vertlazer.png"));
		}catch(IOException e){
			System.out.println("Error file not found");
		}
		try{
			lazerimg = ImageIO.read(new File("horizontallazer.png"));
		}catch(IOException e){
			System.out.println("Error file not found");
		}
	}



}
