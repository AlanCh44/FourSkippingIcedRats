import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.event.*;

public class colouredobjects extends Rectangle implements ActionListener{

	//properties
	String strColor;
	//strUse is between movable block or ground
	String strUse;
	boolean blnPlayerIntersect;
	Timer thetimer = new Timer(1000/60, this);
	Rectangle playerrect = new Rectangle();
	
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == thetimer){
			this.prevent();
		}
	}
	
	
	//methods
	//use prevent method when character colour does not equal object colour
	public void prevent(){
		if(this.intersects(playerrect)){
			//if(playerrect.getx() < this.getx()){
				
			//}
		}
	}
	
	public void move(){
		if(this.strUse.equals("block")){
			
		}
	}
	
	//use standon method when character colour is equal to object colour
	public void standon(){
		//if(colouredobjects.intersects
	}
	
	
	//constructor
	public colouredobjects(int x, int y, int width, int height, String strColor, String strUse, Rectangle playerrect){
		super(x, y, width, height);
		this.strColor = strColor;
		this.strUse = strUse;
		this.playerrect = playerrect;
	}


}
