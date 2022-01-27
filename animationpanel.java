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
	BufferedImage lazerimg = null;
	Timer thetimer = new Timer(1000/60, this);
	
	int intGrayX = 0;
	int intGrayY = 520;
	int intGrayDefX = 0;
	int intGrayDefY = 0;
	int intBoxX = 732;
	int intBoxY = 202;
	int intPlatformY = 585; //x stays constant(draw with g)
	boolean blnJump = false;
	
	Rectangle theground = new Rectangle();
	Rectangle theplatform = new Rectangle();
	Rectangle grayratrect = new Rectangle();
	Rectangle largewall = new Rectangle();
	Rectangle bump = new Rectangle();
	Rectangle graybox = new Rectangle();
	//if player touches lazer, reset player position
	Rectangle lazer = new Rectangle();
	
	
	//methods
	public void paintComponent(Graphics g){
		Rectangle grayratrect = new Rectangle(intGrayX, intGrayY, 100, 100);
		graybox = new Rectangle(intBoxX, intBoxY, 136, 117);
		g.drawImage(background, 0, 0, null);
		g.drawImage(grayrat,intGrayX,intGrayY,null);
		//rats player = new rats("gray", intGrayX, intGrayY);
		g.drawImage(lazerimg, intBoxX + 136, 206, null);
		intGrayX = intGrayX + intGrayDefX;
		intGrayY = intGrayY + intGrayDefY;
		g.setColor(new Color(230, 145, 56));
		g.fillRect(211, intPlatformY, 200, 30);
		g.setColor(new Color(153, 153, 153));
		g.fillRect(intBoxX, intBoxY, 136, 117);
		
		//boundaries
		
		//general floor
		
		if(intGrayY > 520){
			intGrayY = 520;
			intGrayDefY = 0;
			
		}
		
		//platform boundary
		if(intPlatformY < 305){
			intPlatformY = intPlatformY+5;
		}
		//elevated ground boundary
		if(intGrayX > 372 && intGrayX < 585){
			if(intGrayY > 219){
				intGrayY = 218;
			}
		}
		//prevents morph through large wall
		//from left side
		if(grayratrect.intersects(largewall) && intGrayY> 301 && intGrayX < 500){
			intGrayX = intGrayX -5;
		}
		//from right side
		if(grayratrect.intersects(largewall) && intGrayX > 528){
			System.out.println("intersects");
			intGrayX = intGrayX +5;
		}
		
		//prevents morph through bump(player will have to jump over)
		if(grayratrect.intersects(bump) && intGrayY >= 218){
			intGrayX = intGrayX - 5;
		}else if(grayratrect.intersects(bump) && intGrayY < 218){
			if(intGrayY >199){
				intGrayY = 199;
			}
		}else if(grayratrect.intersects(bump) && intGrayX > 570 && intGrayY >= 218){
			intGrayX = intGrayX + 5;
		}
		
		//gray platform
		if(intGrayY < 319 && intGrayX >= 585 && intGrayX < 877){
			if(intGrayY > 219){
				intGrayY = 218;
			}
		} 
		
		//gray box
		if(grayratrect.intersects(graybox) && intGrayX < intBoxX){
			intBoxX = intBoxX + 3;
		}
			
		
		
		//jumping physics
		if(intGrayX > 220 && intGrayX < 412){
			intGrayDefY = 0;
		}
		
		if(blnJump == true){
			intGrayDefY = -10;
		}else if(blnJump == false && grayratrect.intersects(theplatform) == false){
			intGrayDefY = 10;
		}
		
		
		
		//platform movement when player is on it
		
		if(intGrayX > 119 && intGrayX < 372){
			intGrayY = intPlatformY-100;
			intPlatformY = intPlatformY - 5;
			intGrayDefY = 0;
		}
		
		//platform moves back downwards if player is below it
		if(intGrayY > intPlatformY-60){
			intPlatformY = intPlatformY + 5;
		}
	}
	
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == thetimer){
			this.repaint();
			/*
			System.out.println("X: "+intGrayX);
			System.out.println("Y: "+intGrayY);
			*/
		}
	}
	
	
	
	//constructor
	public animationpanel(){
		super();
		
		theground = new Rectangle(0, 620, 1280, 100);
		theplatform = new Rectangle(211, intPlatformY, 200, 30);
		largewall = new Rectangle(413, 319, 182, 301);
		bump = new Rectangle(559, 289, 26, 30);
		
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
		
		try{
			lazerimg = ImageIO.read(new File("horizontallazer.png"));
		}catch(IOException e){
			System.out.println("Error file not found");
		}
	}



}
