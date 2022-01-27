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
			return "gray";
		}else if(intPlayerJoin == 2){
			return "purple";
		}else if(intPlayerJoin == 3){
			return "green";
		}else if(intPlayerJoin == 4){
			return "yellow";
		}
		
		return strColor;
	}
	
	public int stopmoving(){
		//makes character stop moving
		return 0;
	}
	
	public int stackcharacter(){
		intNewY = intY + 100;
		return intNewY;
	}
	
	//constructor
	public characters(int intPlayerJoin){
		this.intPlayerJoin = intPlayerJoin;
	}
}
