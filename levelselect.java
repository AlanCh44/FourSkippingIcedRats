import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImage;

// Need to relocate everything
// Need main file to test
// Need to add to panel/frame
// Buttons need to trigger levels
public class levelselect extends JPanel {
	// Properties
	BufferedImage theimg = null;

	// Methods
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1280, 720);
		g.drawImage(theimg, 0, 0, null);
	}

	// Constructor
	public levelselect() {
		super();
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1280, 720));

		// Image
		try {
			theimg = ImageIO.read(new File("levelselecticedrats.png"));
		} catch (IOException e) {
			System.out.println("Unable to load image");
		}
	}
}
