import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.event.*;
public class animationpanel extends JPanel implements ActionListener{
	//CREATE OBJECTS FOR ALL WALLS AND FLOORS
	//properties
	BufferedImage yellowrat = null;
	BufferedImage grayrat = null;
	BufferedImage background = null;
	Timer thetimer = new Timer(1000/60, this);
	
	int intGrayX = 0;
	int intGrayY = 520;
	int intGrayDefX = 0;
	int intGrayDefY = 0;
	int intPlatformY = 585; //x stays constant(draw with g)
	boolean blnJump = false;
	
	Rectangle theground = new Rectangle();
	Rectangle theplatform = new Rectangle();
	Rectangle grayratrect = new Rectangle();
	
	
	//methods
	public void paintComponent(Graphics g){
		Rectangle grayratrect = new Rectangle(intGrayX, intGrayY, 100, 100);
		g.drawImage(background, 0, 0, null);
		g.drawImage(grayrat,intGrayX,intGrayY,null);
		intGrayX = intGrayX + intGrayDefX;
		intGrayY = intGrayY + intGrayDefY;
		g.setColor(new Color(230, 145, 56));
		g.fillRect(211, intPlatformY, 200, 30);
		
		//boundary
		if(intGrayY > 520){
			intGrayY = 520;
			intGrayDefY = 0;
			
		}
		
		//jumping physics
		if(blnJump == true){
			intGrayDefY = -10;
		}else if(blnJump == false){
			intGrayDefY = 10;
		}
		
		/*
		//adjust so that only if your on the side and intersects
		if(grayratrect.intersects(theplatform)){
			if(intGrayX > 130 && intGrayY == 520){
				intGrayX = intGrayX - 5;
			//keep character above platform
			}else if(intGrayX > 130 && intGrayX < 413){
				intGrayY = intPlatformY - 2;
			}
		}
		*/
		
		
	}
	
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == thetimer){
			this.repaint();
		}
	}
	
	
	
	//constructor
	public animationpanel(){
		super();
		
		theground = new Rectangle(0, 620, 1280, 100);
		theplatform = new Rectangle(211, intPlatformY, 200, 30);
		
		
		
		thetimer.start();
		
		this.setPreferredSize(new Dimension(1280, 720));
		this.setLayout(null);
		
		
		try{
			grayrat = ImageIO.read(new File("gray.png"));
		}catch(IOException e){
			System.out.println("Error file not found");
		}
		
		try{
			background = ImageIO.read(new File("Tutorial(no rat).png"));
		}catch(IOException e){
			System.out.println("Error file not found");
		}
	}



}
