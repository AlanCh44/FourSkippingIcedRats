import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.event.*;
public class characters{
	//properties
	String strColor;
	int intHeight = 100;
	int intWidth = 100;
	int intX;
	int intY;
	int intNewY;
	int intPlayerJoin; //tracks when player joins, player will be assigned a colour based on join position
	
	//methods
	public int moveright(){
		return 3;
	}
	
	public int moveleft(){
		return - 3;
	}
	
	public boolean jump(){
		return true;
	}
	
	public String assigncolor(){
		if(intPlayerJoin == 1){
			strColor = "gray";
			return strColor;
		}else if(intPlayerJoin == 2){
			strColor = "purple";
			return strColor;
		}else if(intPlayerJoin == 3){
			strColor = "green";
			return strColor;
		}else if(intPlayerJoin == 4){
			strColor = "yellow";
			return strColor;
		}
		
		return strColor;
	}
	
	public int stopmoving(){
		//makes character stop moving
		return 0;
	}
	
	//constructor
	public characters(int intPlayerJoin){
		this.intPlayerJoin = intPlayerJoin;
	}
}
