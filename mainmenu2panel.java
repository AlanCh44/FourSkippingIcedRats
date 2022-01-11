import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.event.*;

public class mainmenu2panel extends JPanel implements ActionListener{
	//properties
	JButton thehelpscreenbut = new JButton("Help");
	JButton playgame = new JButton("Play");
	JButton levelselectbut = new JButton("Level Select");
	BufferedImage background = null;
	
	//methods
	public void actionPerformed(ActionEvent evt){
		
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(background, 0, 0, null);
	}
	
	//constructor
	public mainmenu2panel(){
		super();
		this.setPreferredSize(new Dimension(1280, 720));
		this.setLayout(null);
		
		thehelpscreenbut.setSize(350, 70);
		thehelpscreenbut.setLocation(46, 20);
		add(thehelpscreenbut);
		
		playgame.setSize(350, 70);
		playgame.setLocation(442, 20);
		add(playgame);
		
		levelselectbut.setSize(350, 70);
		levelselectbut.setLocation(838, 20);
		add(levelselectbut);
		
		try{
			background = ImageIO.read(new File("Menu design(without rats).png"));
		}catch(IOException e){
			System.out.println("Unable to load image file");
		}
	}
}

