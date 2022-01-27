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
	
	boolean blnJump = false;
	Rectangle theground = new Rectangle(0, 620, 1280, 100);
	/// if player touches lazer, reset player position
	Rectangle lazer = new Rectangle();
	
	
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
		/*g.drawImage(greenrat,intGreenX,intGreenY,null);
		g.drawImage(yellowrat,intYellowX,intYellowY,null);
		g.drawImage(purplerat,intPurpleX,intPurpleY,null);*/
		
		// boundries
		if(grayratrect.intersects(thebox) && intGrayY == 520){
			intGrayDefX = 0;
			if(intGrayX + 100 >= 364){
				intGrayX = intGrayX - 2;
			}
		}
		
		// floor
		if(intGrayY > 520){
			intGrayY = 520;
			intGrayDefY = 0;
		}else if(intYellowY > 520){
			intYellowY = 520;
			intGrayDefY = 0; 
		}else if(intGreenY > 520){
			intGreenY = 520;
			intGreenDefY = 0;
		}else if(intPurpleY > 520){
			intPurpleY = 520;
			intPurpleDefY = 0;
		}
		
		// deflections
		intGrayX = intGrayX + intGrayDefX;
		intGrayY = intGrayY + intGrayDefY;
		
		//jumping physics
		if(intGrayX > 220 && intGrayX < 412){
			intGrayDefY = 0;
		}
		
		if(blnJump == true){
			intGrayDefY = -12;
			System.out.println(intGrayY);
		}else if(blnJump == false){
			
		}else if(blnJump == false && grayratrect.intersects(theground) == false){
			intGrayDefY = 12;
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
	}



}
