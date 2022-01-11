import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class mynetapp1 implements ActionListener{
	// Properties
	JFrame theframe = new JFrame("Basic Client Net App");
	JPanel thepanel = new JPanel();
	JTextField thetext = new JTextField("Enter text");
	SuperSocketMaster ssmC = new SuperSocketMaster("127.0.0.1", 6112, this);
	
	// Methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == thetext){
			System.out.println("I heard you");
			ssmC.sendText(thetext.getText());
		}
	}
	// Constructor
	public mynetapp1(){
		thetext.addActionListener(this);
		thepanel.add(thetext);
		thepanel.setPreferredSize(new Dimension(300, 300));
		theframe.setContentPane(thepanel);
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.pack();
		theframe.setVisible(true);
		ssmC.connect();
	}
	// Main
	public static void main(String[] args){
		new mynetapp1();
	}
}
