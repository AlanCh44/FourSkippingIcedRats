import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.event.*;

// Need to add stacking
// Lose = reset position?
// Win = ????
public class buttonpanel extends JPanel implements ActionListener {
	// Properties
	BufferedImage yellowrat = null;
	BufferedImage grayrat = null;
	BufferedImage purplerat = null;
	BufferedImage greenrat = null;
	BufferedImage background = null;
	BufferedImage buttonimg = null;
	BufferedImage buttonpressedimg = null;
	BufferedImage cheeseimg = null;

	Timer thetimer = new Timer(1000/60, this);
	
	// Gray Rat
	int intGrayX = 0;
	int intGrayY = 520;
	int intGrayDefX = 0;
	int intGrayDefY = 0;
	// Yellow Rat
	int intYellowX = 50;
	int intYellowY = 520;
	int intYellowDefX = 0;
	int intYellowDefY = 0;
	// Purple Rat
	int intPurpleX = 100;
	int intPurpleY = 520;
	int intPurpleDefX = 0;
	int intPurpleDefY = 0;
	// Green Rat
	int intGreenX = 150;
	int intGreenY = 520;
	int intGreenDefX = 0;
	int intGreenDefY = 0;
	// Button
	int intButtonX = 500;
	int intButtonY = 520;
	int intButton2X = intButtonX+150;
	int intButton3X = intButton2X+150;

	// Jump
	boolean blnJump = false;
	
	// Hit Boxes & Walls & Floors
	Rectangle theground = new Rectangle();
	Rectangle theplatform = new Rectangle();
	Rectangle grayratrect = new Rectangle();
	Rectangle yellowratrect = new Rectangle();
	Rectangle greenratrect = new Rectangle();
	Rectangle purpleratrect = new Rectangle();
	
	// Buttons
	Rectangle button = new Rectangle();
	Rectangle button2 = new Rectangle();
	Rectangle button3 = new Rectangle();
	
	// Cheese Check
	boolean blnCheese1 = true;
	boolean blnCheese2 = true;

	// Methods
	public void paintComponent(Graphics g) {
		// Hit Boxes
		Rectangle grayratrect = new Rectangle(intGrayX, intGrayY, 50, 100);
		Rectangle yellowratrect = new Rectangle(intYellowX, intYellowY, 50, 100);
		Rectangle greenratrect = new Rectangle(intGreenX, intGreenY, 50, 100);
		Rectangle purpleratrect = new Rectangle(intPurpleX, intPurpleY, 50, 100);
		Rectangle cheeserect = new Rectangle(575, 275, 50, 50);
		Rectangle cheese2rect = new Rectangle(1150, 520, 50, 50);
		// Background
		g.drawImage(background, 0, 0, null);
		// Rats
		g.drawImage(grayrat,intGrayX, intGrayY, null);
		g.drawImage(yellowrat,intYellowX, intYellowY, null);
		g.drawImage(purplerat,intPurpleX, intPurpleY, null);
		g.drawImage(greenrat,intGreenX, intGreenY, null);
		// Buttons / Lose
		if(grayratrect.intersects(button) || purpleratrect.intersects(button) || yellowratrect.intersects(button) || greenratrect.intersects(button)) {
			g.drawImage(buttonpressedimg, intButtonX, intButtonY, null);
			intGrayX = 0;
			intGrayY = 520;
			intGrayDefX = 0;
			intGrayDefY = 0;
			// Yellow Rat
			intYellowX = 50;
			intYellowY = 520;
			intYellowDefX = 0;
			intYellowDefY = 0;
			// Purple Rat
			intPurpleX = 100;
			intPurpleY = 520;
			intPurpleDefX = 0;
			intPurpleDefY = 0;
			// Green Rat
			intGreenX = 150;
			intGreenY = 520;
			intGreenDefX = 0;
			intGreenDefY = 0;
			System.out.println("lost!");
		} else {
			g.drawImage(buttonimg, intButtonX, intButtonY, null);
		}
		if (grayratrect.intersects(button2) || purpleratrect.intersects(button2) || yellowratrect.intersects(button2) || greenratrect.intersects(button2)) {
			g.drawImage(buttonpressedimg, intButton2X, intButtonY, null);
			intGrayX = 0;
			intGrayY = 520;
			intGrayDefX = 0;
			intGrayDefY = 0;
			// Yellow Rat
			intYellowX = 50;
			intYellowY = 520;
			intYellowDefX = 0;
			intYellowDefY = 0;
			// Purple Rat
			intPurpleX = 100;
			intPurpleY = 520;
			intPurpleDefX = 0;
			intPurpleDefY = 0;
			// Green Rat
			intGreenX = 150;
			intGreenY = 520;
			intGreenDefX = 0;
			intGreenDefY = 0;
			System.out.println("lost!");
		} else {
			g.drawImage(buttonimg, intButton2X, intButtonY, null);
		}
		if (grayratrect.intersects(button3) || purpleratrect.intersects(button3) || yellowratrect.intersects(button3) || greenratrect.intersects(button3)) {
			g.drawImage(buttonpressedimg, intButton3X, intButtonY, null);
			intGrayX = 0;
			intGrayY = 520;
			intGrayDefX = 0;
			intGrayDefY = 0;
			// Yellow Rat
			intYellowX = 50;
			intYellowY = 520;
			intYellowDefX = 0;
			intYellowDefY = 0;
			// Purple Rat
			intPurpleX = 100;
			intPurpleY = 520;
			intPurpleDefX = 0;
			intPurpleDefY = 0;
			// Green Rat
			intGreenX = 150;
			intGreenY = 520;
			intGreenDefX = 0;
			intGreenDefY = 0;
			System.out.println("lost!");
		} else {
			g.drawImage(buttonimg, intButton3X, intButtonY, null);
		}

		// Cheese
		if (grayratrect.intersects(cheeserect) || yellowratrect.intersects(cheeserect) || purpleratrect.intersects(cheeserect) || greenratrect.intersects(cheeserect)) {
			blnCheese1 = false;
		} else if(blnCheese1 == true) {
				g.drawImage(cheeseimg, 575, 275, null);
		}
		if (grayratrect.intersects(cheese2rect) || yellowratrect.intersects(cheese2rect) || purpleratrect.intersects(cheese2rect) || greenratrect.intersects(cheese2rect)) {
			blnCheese2 = false;
		} else if(blnCheese2 == true) {
				g.drawImage(cheeseimg, 1150, 520, null);
		}
		// Win
		if (blnCheese1 == false && blnCheese2 == false) {
			System.out.println("Win");
		}
		// Defs
		intGrayX = intGrayX + intGrayDefX;
		intGrayY = intGrayY + intGrayDefY;
		intGreenX = intGreenX + intGreenDefX;
		intGreenY = intGreenY + intGreenDefY;
		intPurpleX = intPurpleX + intPurpleDefX;
		intPurpleY = intPurpleY + intPurpleDefY;
		intYellowX = intYellowX + intYellowDefX;
		intYellowY = intYellowY + intYellowDefY;

		// Stacking

		// Boarders
		// general floor
		if(intGrayY > 520) {
			intGrayY = 520;
			intGrayDefY = 0;
		}
		if(intGreenY > 520) {
			intGreenY = 520;
			intGreenDefY = 0;
		}
		if(intYellowY > 520) {
			intYellowY = 520;
			intYellowDefY = 0;
		}
		if(intPurpleY > 520) {
			intPurpleY = 520;
			intPurpleDefY = 0;
		}
		if (intGrayX < 10) {
			intGrayX = 10;
			intGrayDefX = 0;
		}
		if (intGrayX > 1240) {
			intGrayX = 1240;
			intGrayDefX = 0;
		}
		if (intYellowX < 10) {
			intYellowX = 10;
			intYellowDefX = 0;
		}
		if (intYellowX > 1240) {
			intYellowX = 1240;
			intYellowDefX = 0;
		}
		if (intPurpleX < 10) {
			intPurpleX = 10;
			intPurpleDefX = 0;
		}
		if (intPurpleX > 1240) {
			intPurpleX = 1240;
			intPurpleDefX = 0;
		}
		if (intGreenX < 10) {
			intGreenX = 10;
			intGreenDefX = 0;
		}
		if (intGreenX > 1240) {
			intGreenX = 1240;
			intGreenDefX = 0;
		}
		
		//jumping physics
		if(intGrayX > 220 && intGrayX < 412){
			intGrayDefY = 0;
		}
		if(intYellowX > 220 && intYellowX < 412){
			intYellowDefY = 0;
		}
		if(intGreenX > 220 && intGreenX < 412){
			intGreenDefY = 0;
		}
		if(intPurpleX > 220 && intPurpleX < 412){
			intPurpleDefY = 0;
		}

		if(blnJump) {
			intGrayDefY = -12;
		} else if(blnJump == false) {
			intGrayDefY = 12;
		}
	}
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == thetimer){
			this.repaint();
		}
	}
	
	// Constructor
	public buttonpanel() {
		super();
		theground = new Rectangle(0, 620, 1280, 100);
		
		button = new Rectangle(intButtonX, intButtonY, 50, 50);
		button2 = new Rectangle(intButton2X, intButtonY, 50, 50);
		button3 = new Rectangle(intButton3X, intButtonY, 50, 50);
		
		thetimer.start();
		
		this.setPreferredSize(new Dimension(1280, 720));
		this.setLayout(null);
		
		// Images
		// Rats
		try {
			grayrat = ImageIO.read(new File("gray.png"));
		} catch(IOException e) {
			System.out.println("Error file not found");
		}
		try {
			greenrat = ImageIO.read(new File("green.png"));
		} catch(IOException e) {
			System.out.println("Error file not found");
		}
		try {
			yellowrat = ImageIO.read(new File("yellow.png"));
		} catch(IOException e) {
			System.out.println("Error file not found");
		}
		try {
			purplerat = ImageIO.read(new File("purple.png"));
		} catch(IOException e) {
			System.out.println("Error file not found");
		}
		// Background
		try {
			background = ImageIO.read(new File("buttonlevel.jpeg"));
		} catch(IOException e) {
			System.out.println("Error file not found");
		}
		// Buttons
		try {
			buttonimg = ImageIO.read(new File("button.png"));
		} catch(IOException e) {
			System.out.println("Error file not found");
		}
		try {
			buttonpressedimg = ImageIO.read(new File("buttonpressed.png"));
		} catch(IOException e) {
			System.out.println("Error file not found");
		}
		try {
			cheeseimg = ImageIO.read(new File("cheese.png"));
		} catch(IOException e) {
			System.out.println("Error file not found");
		}
	}
}
