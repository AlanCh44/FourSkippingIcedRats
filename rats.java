import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.event.*;
public class rats{
	//properties
	BufferedImage greenrat = null;
	BufferedImage grayrat = null;
	BufferedImage purplerat = null;
	BufferedImage yellowrat = null;
	String strColor;
	Rectangle characterrect = new Rectangle();
	int intX;
	int intY;
	
	//methods

	public void paintComponent(Graphics g){
		if(this.strColor.equals("gray")){
			g.drawImage(grayrat, intX, intY, null);
		}else if(this.strColor.equals("yellow")){
			g.drawImage(yellowrat, intX, intY, null);
		}else if(this.strColor.equals("green")){
			g.drawImage(greenrat, intX, intY, null);
		}else if(this.strColor.equals("purple")){
			g.drawImage(purplerat, intX, intY, null);
		}
		characterrect = new Rectangle(intX, intY, 100, 100);
	}

	
	
	//constructor
	public rats(String strColor, int intX, int intY){
		this.strColor = strColor;
		try{
			grayrat = ImageIO.read(new File("gray.png"));
		}catch(IOException e){
			System.out.println("Error file not found");
		}
		
		try{
			greenrat = ImageIO.read(new File("green.png"));
		}catch(IOException e){
			System.out.println("Error file not found");
		}
		
		try{
			purplerat = ImageIO.read(new File("purple.png"));
		}catch(IOException e){
			System.out.println("Error file not found");
		}
		
		try{
			yellowrat = ImageIO.read(new File("yellow.png"));
		}catch(IOException e){
			System.out.println("Error file not found");
		}
		
		
	}




}
