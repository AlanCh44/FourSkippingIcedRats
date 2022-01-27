import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.event.*;

public class winscreenpanel extends JPanel implements ActionListener{
	//properties
	JButton nextbut = new JButton("Next Level");
	JButton returnbut = new JButton("Return to Home Screen");
	BufferedImage winscreen = null;
	

	//methods
	public void paintComponent(Graphics g){
		g.drawImage(winscreen, 0, 0, null);
	}
	
	public void actionPerformed(ActionEvent evt){
		
	}
	
	//constructor
	public winscreenpanel(){
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1280, 720));
		
		//button brings team to next level
		nextbut.setSize(300, 70);
		nextbut.setLocation(290, 520);
		this.add(nextbut);
		
		//button brings team back to main menu
		returnbut.setSize(300, 70);
		returnbut.setLocation(690, 520);
		this.add(returnbut);
		
		
		try{
			winscreen = ImageIO.read(new File("Win Screen without buttons.png"));
		}catch(IOException e){
			System.out.println("Error file not found");
		}
		
	}
}
