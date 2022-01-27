import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.event.*;

public class losescreenpanel extends JPanel implements ActionListener{
	//properties
	JButton retrybut = new JButton("Retry Level");
	JButton returnbut = new JButton("Return to Home Screen");
	//labels will display player usernames
	JLabel deathlabelyellow = new JLabel("username1");
	JLabel deathlabelgrey = new JLabel("username2");
	JLabel deathlabelgreen = new JLabel("username3");
	JLabel deathlabelpurple = new JLabel("username4");
	BufferedImage losescreen = null;
	

	//methods
	public void paintComponent(Graphics g){
		g.drawImage(losescreen, 0, 0, null);
	}
	
	public void actionPerformed(ActionEvent evt){
		
	}
	
	//constructor
	public losescreenpanel(){
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1280, 720));
		
		//button brings team to next level
		retrybut.setSize(300, 70);
		retrybut.setLocation(290, 520);
		this.add(retrybut);
		
		//button brings team back to main menu
		returnbut.setSize(300, 70);
		returnbut.setLocation(690, 520);
		this.add(returnbut);
		
		//labels display player usernames on tombstones
		deathlabelyellow.setSize(180, 30);
		deathlabelyellow.setLocation(160, 230);
		this.add(deathlabelyellow);
		
		deathlabelgrey.setSize(180, 30);
		deathlabelgrey.setLocation(450, 230);
		this.add(deathlabelgrey);
		
		deathlabelgreen.setSize(180, 30);
		deathlabelgreen.setLocation(744, 230);
		this.add(deathlabelgreen);
		
		deathlabelpurple.setSize(180, 30);
		deathlabelpurple.setLocation(1030, 230);
		this.add(deathlabelpurple);
		
		//load image
		try{
			losescreen = ImageIO.read(new File("Lose Screen (1).png"));
		}catch(IOException e){
			System.out.println("Error file not found");
		}
		
	}
}
