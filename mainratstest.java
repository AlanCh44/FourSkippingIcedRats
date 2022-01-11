import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class mainratstest implements ActionListener {
	// Properties + Variables
	JFrame theframe = new JFrame("Rats Test");
	levelselect thepanel = new levelselect();
	JButton lvl1but = new JButton("Level 1");
	JButton lvl2but = new JButton("Level 2");
	JButton lvl3but = new JButton("Level 3");
	Timer thetimer = new Timer(1000/60, this);

	// Methods
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == lvl1but) {
			System.out.println("Selected Level 1");
		} else if (evt.getSource() == lvl2but) {
			System.out.println("Selected Level 2");
		} else if (evt.getSource() == lvl3but) {
			System.out.println("Selected Level 3");
		}
		if (evt.getSource() == thetimer) {
			thepanel.repaint();
		}
	}

	// Constructors
	public mainratstest() {
		thepanel.setLayout(null);
		thepanel.setPreferredSize(new Dimension(1280, 720));
		theframe.setContentPane(thepanel);
		theframe.pack();
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.setVisible(true);
		thetimer.start();
		
		// lvl1but
		lvl1but.setSize(315, 50);
		lvl1but.setLocation(115, 570);
		lvl1but.addActionListener(this);
		thepanel.add(lvl1but);

		// lvl2but
		lvl2but.setSize(310, 50);
		lvl2but.setLocation(505, 570);
		lvl2but.addActionListener(this);
		thepanel.add(lvl2but);

		// lvl3but
		lvl3but.setSize(315, 50);
		lvl3but.setLocation(890, 570);
		lvl3but.addActionListener(this);
		thepanel.add(lvl3but);

		theframe.setResizable(false);
	}

	// Main Method
	public static void main(String[] args) {
		new mainratstest();
	}
}
