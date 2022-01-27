import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.event.*;
public class mainprogram implements KeyListener, ActionListener{
	// properties
	JFrame theframe = new JFrame("Four Skipping Iced Rats");
	Timer thetimer = new Timer(1000/60, this);
	// panels
	mainmenu1panel mainmenu1 = new mainmenu1panel();
	mainmenu2panel mainmenu2 = new mainmenu2panel();
	level1 level1panel = new level1();
	level2 level2panel = new level2();
	buttonpanel level3panel = new buttonpanel();
	losescreenpanel losescreen = new losescreenpanel();
	animationpanel helpscreen = new animationpanel();
	levelselect levelselectscreen = new levelselect();
	
	//panel buttons
	JButton readybut = new JButton("Ready");
	JButton thehelpscreenbut = new JButton("Help");
	JButton playgame = new JButton("Play");
	JButton levelselectbut = new JButton("Level Select");
	JButton level1but = new JButton("Level 1");
	JButton level2but = new JButton("Level 2");
	JButton level3but = new JButton("Level 3");
	//keeps track of current panel
	String strCurrentPanel = ""; 
	characters characterobject = new characters(0);
	
	//ssm data (networking)
	JButton serverbut = new JButton("Server Mode");
	JButton clientbut = new JButton("Client Mode");
	JTextField IPinput = new JTextField();
	JTextField usernameinput = new JTextField();
	JLabel theusername = new JLabel("Username:");
	JLabel IPlabel = new JLabel("Server Address:");
	Font thefont = new Font("Courier New", Font.PLAIN, 125);
	String strUser;
	boolean blnHost = true;
	int intPlayerCount = 1;
	String strLineSplit[];
	String strIP;
	boolean blnReady = false;
	int intMyNumber;
	String strMyColor = ""; //keeps track of player color
	
	//part of jump physics
	long jumpingtime = 200;
	
	SuperSocketMaster ssm;
	int intPlayer;
	
	// methods
	public void actionPerformed(ActionEvent evt){
	//network component (if messages are received)
		if(evt.getSource() == ssm){
			/// Split
			String strLine = ssm.readText();
			strLineSplit = strLine.split(",");
			if(strLineSplit[0].equals("Connect")){
				if(blnHost == true){
					characters character = new characters(1);
					strMyColor = character.assigncolor();
					System.out.println("Color"+strMyColor);
					// FileIO (player name and color sent to txt file
                    try {
                        PrintWriter assign = new PrintWriter(new FileWriter("UserColor.txt", true));
                        if(intPlayerCount == 1){
							assign.println(strUser+" = gray");
							assign.close();
						}
                        
                    } catch (IOException e) {
                        System.out.println("Error");
                    }
                
					intPlayerCount = intPlayerCount + 1;
					System.out.println("Players Count: "+intPlayerCount);
					String strPlayer = strLineSplit[1]+","+intPlayerCount;
					ssm.sendText("Number,"+strLineSplit[1]+","+intPlayerCount);
					if(intPlayerCount == 2){
						readybut.setEnabled(true);
					}
				}
			//player gets assigned a colour
			}else if(strLineSplit[0].equals("Number")){
				if(strUser.equals(strLineSplit[1])){
					intMyNumber = Integer.parseInt(strLineSplit[2]);
					characters character = new characters(intMyNumber);
					strMyColor = character.assigncolor();
					System.out.println("Color"+strMyColor);
					//player name and color are sent to txt file
					try{
                        PrintWriter assign = new PrintWriter(new FileWriter("UserColor.txt", true));
						if(intMyNumber == 2 && blnHost == false){
							assign.println(strUser+" = purple");
						}else if(intMyNumber == 3 && blnHost == false){
							assign.println(strUser+" = green");
						}else if(intMyNumber == 4 && blnHost == false){
							assign.println(strUser+" = yellow");
						}
                       assign.close();
                    }catch (IOException e) {
                        System.out.println("Error");
                    }
				}
			//sends message to switch panels for all characters
			}else if(strLineSplit[0].equals("level")){
				System.out.println("Switching levels");
				if(strLineSplit[1].equals("level1")){
					strCurrentPanel = "level1";
					theframe.setContentPane(level1panel);
					theframe.pack();
					theframe.requestFocus();
				}else if(strLineSplit[1].equals("level2")){
					strCurrentPanel = "level2";
					theframe.setContentPane(level2panel);
					theframe.pack();
					theframe.requestFocus();
				} else if (strLineSplit[1].equals("level3")) {
					strCurrentPanel = "level3";
					theframe.setContentPane(level3panel);
					theframe.pack();
					theframe.requestFocus();
				}
			
			//receives location of each character
			}else if(strLineSplit[0].equals("location")){
				if(strCurrentPanel.equals("level1")){
					if(strLineSplit[1].equals("gray")){
						level1panel.intGrayX = Integer.parseInt(strLineSplit[2]);
						level1panel.intGrayY = Integer.parseInt(strLineSplit[3]);
					}else if(strLineSplit[1].equals("green")){
						level1panel.intGreenX = Integer.parseInt(strLineSplit[2]);
						level1panel.intGreenY = Integer.parseInt(strLineSplit[3]);
					}else if(strLineSplit[1].equals("yellow")){
						level1panel.intYellowX = Integer.parseInt(strLineSplit[2]);
						level1panel.intYellowY = Integer.parseInt(strLineSplit[3]);
					}else if(strLineSplit[1].equals("purple")){
						level1panel.intPurpleX = Integer.parseInt(strLineSplit[2]);
						level1panel.intPurpleY = Integer.parseInt(strLineSplit[3]);
					}
				}else if(strCurrentPanel.equals("level2")){
					if(strLineSplit[1].equals("gray")){
						level2panel.intGrayX = Integer.parseInt(strLineSplit[2]);
						level2panel.intGrayY = Integer.parseInt(strLineSplit[3]);
					}else if(strLineSplit[1].equals("green")){
						level2panel.intGreenX = Integer.parseInt(strLineSplit[2]);
						level2panel.intGreenY = Integer.parseInt(strLineSplit[3]);
					}else if(strLineSplit[1].equals("yellow")){
						level2panel.intYellowX = Integer.parseInt(strLineSplit[2]);
						level2panel.intYellowY = Integer.parseInt(strLineSplit[3]);
					}else if(strLineSplit[1].equals("purple")){
						level2panel.intPurpleX = Integer.parseInt(strLineSplit[2]);
						level2panel.intPurpleY = Integer.parseInt(strLineSplit[3]);
					}
				}else if (strCurrentPanel.equals("level3")){
					if(strLineSplit[1].equals("gray")){
						level3panel.intGrayX = Integer.parseInt(strLineSplit[2]);
						level3panel.intGrayY = Integer.parseInt(strLineSplit[3]);
					}else if(strLineSplit[1].equals("green")){
						level3panel.intGreenX = Integer.parseInt(strLineSplit[2]);
						level3panel.intGreenY = Integer.parseInt(strLineSplit[3]);
					}else if(strLineSplit[1].equals("yellow")){
						level3panel.intYellowX = Integer.parseInt(strLineSplit[2]);
						level3panel.intYellowY = Integer.parseInt(strLineSplit[3]);
					}else if(strLineSplit[1].equals("purple")){
						level3panel.intPurpleX = Integer.parseInt(strLineSplit[2]);
						level3panel.intPurpleY = Integer.parseInt(strLineSplit[3]);
					}
				}
			}
		}
		
		//player sends message of their location
		if(evt.getSource() == thetimer){
			if(strCurrentPanel.equals("level1")){
				if(strMyColor.equals("gray")){
					ssm.sendText("location,"+strMyColor+","+level1panel.intGrayX+","+level1panel.intGrayY);
				}else if(strMyColor.equals("yellow")){
					ssm.sendText("location,"+strMyColor+","+level1panel.intYellowX+","+level1panel.intYellowY);
				}else if(strMyColor.equals("purple")){
					ssm.sendText("location,"+strMyColor+","+level1panel.intPurpleX+","+level1panel.intPurpleY);
				}else if(strMyColor.equals("green")){
					ssm.sendText("location,"+strMyColor+","+level1panel.intGreenX+","+level1panel.intGreenY);
				}
				//checks if team has won
				if(level1panel.blnWin == true){
					strCurrentPanel = "level2";
					theframe.setContentPane(level2panel);
					theframe.pack();
					theframe.requestFocus();
				}
			}else if(strCurrentPanel.equals("level2")){
				if(strMyColor.equals("gray")){
					ssm.sendText("location,"+strMyColor+","+level2panel.intGrayX+","+level2panel.intGrayY);
				}else if(strMyColor.equals("yellow")){
					ssm.sendText("location,"+strMyColor+","+level2panel.intYellowX+","+level2panel.intYellowY);
				}else if(strMyColor.equals("purple")){
					ssm.sendText("location,"+strMyColor+","+level2panel.intPurpleX+","+level2panel.intPurpleY);
				}else if(strMyColor.equals("green")){
					ssm.sendText("location,"+strMyColor+","+level2panel.intGreenX+","+level2panel.intGreenY);
				}
				//checks if team has won
				if(level2panel.blnWin == true){
					strCurrentPanel = "level3";
					theframe.setContentPane(level3panel);
					theframe.pack();
					theframe.requestFocus();
				}
			}else if(strCurrentPanel.equals("level3")){
				if(strMyColor.equals("gray")){
					ssm.sendText("location,"+strMyColor+","+level3panel.intGrayX+","+level3panel.intGrayY);
				}else if(strMyColor.equals("yellow")){
					ssm.sendText("location,"+strMyColor+","+level3panel.intYellowX+","+level3panel.intYellowY);
				}else if(strMyColor.equals("purple")){
					ssm.sendText("location,"+strMyColor+","+level3panel.intPurpleX+","+level3panel.intPurpleY);
				}else if(strMyColor.equals("green")){
					ssm.sendText("location,"+strMyColor+","+level3panel.intGreenX+","+level3panel.intGreenY);
				}
				if(level3panel.blnWin == true){
					if(blnHost == true){
						theframe.setContentPane(mainmenu2);
						theframe.pack();
						theframe.requestFocus();
					}else if(blnHost == false){
						theframe.setContentPane(mainmenu1);
						theframe.pack();
						theframe.requestFocus();
					}
				}
			}
		}
		
		// Username input
		if(evt.getSource() == usernameinput){
			strUser = usernameinput.getText();
			usernameinput.setEnabled(false);
			clientbut.setEnabled(true);
			serverbut.setEnabled(true);
		// Server input
		}else if(evt.getSource() == serverbut){
			ssm = new SuperSocketMaster(2188, this);
			IPinput.setText(ssm.getMyAddress());
			ssm.connect();
			blnHost = true;
			serverbut.setEnabled(false);
			clientbut.setEnabled(false);
		// Client input
		}else if(evt.getSource() == clientbut){
			serverbut.setEnabled(false);
			clientbut.setEnabled(false);
			IPinput.setEnabled(true);
			blnHost = false;
		}else if(evt.getSource() == IPinput){
			strIP = IPinput.getText();
			ssm = new SuperSocketMaster(strIP, 2188, this);
			ssm.connect();
			ssm.sendText("Connect,"+strUser);
			IPinput.setEnabled(false);
		}else if(evt.getSource() == readybut){
			blnReady = true;
			System.out.println("READY");
		
		}

		//ready button sends user to second menu screen
		
		if(evt.getSource() == readybut){
			theframe.setContentPane(mainmenu2);
			theframe.pack();
			System.out.println("user: "+strUser);
			theframe.add(thehelpscreenbut);
			if(blnHost == true){
				theframe.add(playgame);
				theframe.add(levelselectbut);
			}
		}
		
		//selecting playgame brings players to level 1
		else if(evt.getSource() == playgame){
			strCurrentPanel = "level1";
			theframe.setContentPane(level1panel);
			theframe.pack();
			theframe.requestFocus();
			if(blnHost == true){
				ssm.sendText("level,"+strCurrentPanel);
			}
		}
		
		//selecting help brings players to tutorial
		else if(evt.getSource() == thehelpscreenbut){
			strCurrentPanel = "help";
			theframe.setContentPane(helpscreen);
			theframe.pack();
			theframe.requestFocus();
		}
		//selecting helps bring player to levelselect screen
		else if(evt.getSource() == levelselectbut){
			theframe.setContentPane(levelselectscreen);
			theframe.pack();
			theframe.requestFocus();
			theframe.add(level1but);
			theframe.add(level2but);
			theframe.add(level3but);
		//selecting brings player to first level
		}else if(evt.getSource() == level1but){
			strCurrentPanel = "level1";
			theframe.setContentPane(level1panel);
			theframe.pack();
			theframe.requestFocus();
			if(blnHost == true){
				ssm.sendText("level,"+strCurrentPanel);
			}
		//selecting brings player to second level
		}else if(evt.getSource() == level2but){
			strCurrentPanel = "level2";
			theframe.setContentPane(level2panel);
			theframe.pack();
			theframe.requestFocus();
			if(blnHost == true){
				ssm.sendText("level,"+strCurrentPanel);
			}
		//selecting brings player to third level
		}else if (evt.getSource() == level3but) {
			strCurrentPanel = "level3";
			theframe.setContentPane(level3panel);
			theframe.pack();
			theframe.requestFocus();
			if(blnHost == true){
				ssm.sendText("level,"+strCurrentPanel);
			}
		}
	}
	
	//player movement

	public void keyReleased(KeyEvent evt){
		//prevents movement after releasing key
		if(evt.getKeyChar() == 'a'){
			//for help screen
			if(strCurrentPanel.equals("help")){
				helpscreen.intGrayDefX = characterobject.stopmoving();
			//for level 1
			}else if(strCurrentPanel.equals("level1")){
				if(strMyColor.equals("gray")){
					level1panel.intGrayDefX = characterobject.stopmoving();
				}else if(strMyColor.equals("green")){
					level1panel.intGreenDefX = characterobject.stopmoving();
				}else if(strMyColor.equals("yellow")){
					level1panel.intYellowDefX = characterobject.stopmoving();
				}else if(strMyColor.equals("purple")){
					level1panel.intPurpleDefX = characterobject.stopmoving();
				}
			//for level 2
			}else if(strCurrentPanel.equals("level2")){
				if(strMyColor.equals("gray")){
					level2panel.intGrayDefX = characterobject.stopmoving();
				}else if(strMyColor.equals("green")){
					level2panel.intGreenDefX = characterobject.stopmoving();
				}else if(strMyColor.equals("yellow")){
					level2panel.intYellowDefX = characterobject.stopmoving();
				}else if(strMyColor.equals("purple")){
					level2panel.intPurpleDefX = characterobject.stopmoving();
				}
			//for level 3
			} else if(strCurrentPanel.equals("level3")){
				if(strMyColor.equals("gray")){
					level3panel.intGrayDefX = characterobject.stopmoving();
				}else if(strMyColor.equals("green")){
					level3panel.intGreenDefX = characterobject.stopmoving();
				}else if(strMyColor.equals("yellow")){
					level3panel.intYellowDefX = characterobject.stopmoving();
				}else if(strMyColor.equals("purple")){
					level3panel.intPurpleDefX = characterobject.stopmoving();
				}
			}
			
		}else if(evt.getKeyChar() == 'd'){
			//help
			if(strCurrentPanel.equals("help")){
				helpscreen.intGrayDefX = characterobject.stopmoving();
			//level 1
			}else if(strCurrentPanel.equals("level1")){
				if(strMyColor.equals("gray")){
					level1panel.intGrayDefX = characterobject.stopmoving();
				}else if(strMyColor.equals("green")){
					level1panel.intGreenDefX = characterobject.stopmoving();
				}else if(strMyColor.equals("yellow")){
					level1panel.intYellowDefX = characterobject.stopmoving();
				}else if(strMyColor.equals("purple")){
					level1panel.intPurpleDefX = characterobject.stopmoving();
				}
			//level 2
			}else if(strCurrentPanel.equals("level2")){
				if(strMyColor.equals("gray")){
					level2panel.intGrayDefX = characterobject.stopmoving();
				}else if(strMyColor.equals("green")){
					level2panel.intGreenDefX = characterobject.stopmoving();
				}else if(strMyColor.equals("yellow")){
					level2panel.intYellowDefX = characterobject.stopmoving();
				}else if(strMyColor.equals("purple")){
					level2panel.intPurpleDefX = characterobject.stopmoving();
				}
			//level 3
			}else if(strCurrentPanel.equals("level3")){
				if(strMyColor.equals("gray")){
					level3panel.intGrayDefX = characterobject.stopmoving();
				}else if(strMyColor.equals("green")){
					level3panel.intGreenDefX = characterobject.stopmoving();
				}else if(strMyColor.equals("yellow")){
					level3panel.intYellowDefX = characterobject.stopmoving();
				}else if(strMyColor.equals("purple")){
					level3panel.intPurpleDefX = characterobject.stopmoving();
				}
			}
		}
	}
	public void keyPressed(KeyEvent evt){
		//allows characters to move
		if(evt.getKeyChar() == 'a'){
			//help
			if(strCurrentPanel.equals("help")){
				helpscreen.intGrayDefX = characterobject.moveleft();
			//level 1
			}else if(strCurrentPanel.equals("level1")){
				if(strMyColor.equals("gray")){
					level1panel.intGrayDefX = characterobject.moveleft();
				}else if(strMyColor.equals("green")){
					level1panel.intGreenDefX = characterobject.moveleft();
				}else if(strMyColor.equals("yellow")){
					level1panel.intYellowDefX = characterobject.moveleft();
				}else if(strMyColor.equals("purple")){
					System.out.println("moving");
					level1panel.intPurpleDefX = characterobject.moveleft();
				}
			//level 2
			}else if(strCurrentPanel.equals("level2")){
				if(strMyColor.equals("gray")){
					level2panel.intGrayDefX = characterobject.moveleft();
				}else if(strMyColor.equals("green")){
					level2panel.intGreenDefX = characterobject.moveleft();
				}else if(strMyColor.equals("yellow")){
					level2panel.intYellowDefX = characterobject.moveleft();
				}else if(strMyColor.equals("purple")){
					level2panel.intPurpleDefX = characterobject.moveleft();
				}
			//level 3
			}else if(strCurrentPanel.equals("level3")){
				if(strMyColor.equals("gray")){
					level3panel.intGrayDefX = characterobject.moveleft();
				}else if(strMyColor.equals("green")){
					level3panel.intGreenDefX = characterobject.moveleft();
				}else if(strMyColor.equals("yellow")){
					level3panel.intYellowDefX = characterobject.moveleft();
				}else if(strMyColor.equals("purple")){
					System.out.println("moving");
					level3panel.intPurpleDefX = characterobject.moveleft();
				}
			}
		
		}else if(evt.getKeyChar() == 'd'){
			//help
			if(strCurrentPanel.equals("help")){
				helpscreen.intGrayDefX = characterobject.moveright();
			//level 1
			}else if(strCurrentPanel.equals("level1")){
				if(strMyColor.equals("gray")){
					level1panel.intGrayDefX = characterobject.moveright();
				}else if(strMyColor.equals("green")){
					level1panel.intGreenDefX = characterobject.moveright();
				}else if(strMyColor.equals("yellow")){
					level1panel.intYellowDefX = characterobject.moveright();
				}else if(strMyColor.equals("purple")){
					System.out.println("moving");
					level1panel.intPurpleDefX = characterobject.moveright();
				}
			//level 2
			}else if(strCurrentPanel.equals("level2")){
				if(strMyColor.equals("gray")){
					level2panel.intGrayDefX = characterobject.moveright();
				}else if(strMyColor.equals("green")){
					level2panel.intGreenDefX = characterobject.moveright();
				}else if(strMyColor.equals("yellow")){
					level2panel.intYellowDefX = characterobject.moveright();
				}else if(strMyColor.equals("purple")){
					level2panel.intPurpleDefX = characterobject.moveright();
				}
			//level 3
			}else if(strCurrentPanel.equals("level3")){
				if(strMyColor.equals("gray")){
					level3panel.intGrayDefX = characterobject.moveright();
				}else if(strMyColor.equals("green")){
					level3panel.intGreenDefX = characterobject.moveright();
				}else if(strMyColor.equals("yellow")){
					level3panel.intYellowDefX = characterobject.moveright();
				}else if(strMyColor.equals("purple")){
					System.out.println("moving");
					level3panel.intPurpleDefX = characterobject.moveright();
				}
			}
		//jumping 
		}else if(evt.getKeyChar() == 'w'){
			if(strCurrentPanel.equals("help")){
				helpscreen.blnJump = true;
				//starts thread which prevents character floating
				new Thread(new thread()).start();
			}else if(strCurrentPanel.equals("level1")){
				if(strMyColor.equals("gray")){
					level1panel.blnJumpGray = true;	
				}else if(strMyColor.equals("green")){
					level1panel.blnJumpGreen = true;
				}else if(strMyColor.equals("yellow")){
					level1panel.blnJumpYellow = true;
				}else if(strMyColor.equals("purple")){
					level1panel.blnJumpPurple = true;
				}
				new Thread(new thread()).start();
			}else if(strCurrentPanel.equals("level2")){
				if(strMyColor.equals("gray")){
					level2panel.blnJumpGray = true;	
				}else if(strMyColor.equals("green")){
					level2panel.blnJumpGreen = true;
				}else if(strMyColor.equals("yellow")){
					level2panel.blnJumpYellow = true;
				}else if(strMyColor.equals("purple")){
					level2panel.blnJumpPurple = true;
				}
				new Thread(new thread()).start();
			}else if(strCurrentPanel.equals("level3")){
				if(strMyColor.equals("gray")){
					level3panel.blnJumpGray = true;	
				}else if(strMyColor.equals("green")){
					level3panel.blnJumpGreen = true;
				}else if(strMyColor.equals("yellow")){
					level3panel.blnJumpYellow = true;
				}else if(strMyColor.equals("purple")){
					level3panel.blnJumpPurple = true;
				}
				new Thread(new thread()).start();
			}
		}
	}
	public void keyTyped(KeyEvent evt){
		
	}

	
	public void paintComponent(Graphics g){
		
	}
	
	//part of jumping
	public class thread implements Runnable{
		public void run(){
			try{
				//disables jump over certain time (prevents float)
				Thread.sleep(jumpingtime);
				if(strCurrentPanel.equals("help")){
					helpscreen.blnJump = false;
				}else if(strCurrentPanel.equals("level1")){
					if(strMyColor.equals("gray")){
						level1panel.blnJumpGray = false;
					}else if(strMyColor.equals("purple")){
						level1panel.blnJumpPurple = false;
					}else if(strMyColor.equals("yellow")){
						level1panel.blnJumpYellow = false;
					}else if(strMyColor.equals("green")){
						level1panel.blnJumpGreen = false;
					}
				}else if(strCurrentPanel.equals("level2")){
					if(strMyColor.equals("gray")){
						level2panel.blnJumpGray = false;
					}else if(strMyColor.equals("purple")){
						level2panel.blnJumpPurple = false;
					}else if(strMyColor.equals("yellow")){
						level2panel.blnJumpYellow = false;
					}else if(strMyColor.equals("green")){
						level2panel.blnJumpGreen = false;
					}
				} else if(strCurrentPanel.equals("level3")){
					if(strMyColor.equals("gray")){
						level3panel.blnJumpGray = false;
					}else if(strMyColor.equals("purple")){
						level3panel.blnJumpPurple = false;
					}else if(strMyColor.equals("yellow")){
						level3panel.blnJumpYellow = false;
					}else if(strMyColor.equals("green")){
						level3panel.blnJumpGreen = false;
					}
				}
				
			}catch(InterruptedException e){
				
				e.printStackTrace();
				new Thread(this).start();
				System.exit(0);
				
			}
		}
	}
	
	// constructor
	public mainprogram(){
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.setContentPane(mainmenu1);
		theframe.pack();
		theframe.setResizable(false);
		theframe.setVisible(true);
		theframe.addKeyListener(this);
		
		// readybut
		readybut.setSize(150, 50);
		readybut.setLocation(1100, 650);
		readybut.addActionListener(this);
		theframe.add(readybut);
		
		//help screen button from mainmenu2
		thehelpscreenbut.setSize(350, 70);
		thehelpscreenbut.setLocation(46, 20);
		thehelpscreenbut.addActionListener(this);
		
		//play button from mainmenu2
		playgame.setSize(350, 70);
		playgame.setLocation(442, 20);
		playgame.addActionListener(this);
	
		//level button from mainmenu2
		levelselectbut.setSize(350, 70);
		levelselectbut.setLocation(838, 20);
		levelselectbut.addActionListener(this);
		//theframe.add(levelselectbut);
		
		//level 1,2,and 3 buttons from level select
		level1but.setSize(310, 30);
		level1but.setLocation(117, 570);
		level1but.addActionListener(this);
		
		level2but.setSize(310, 30);
		level2but.setLocation(508, 570);
		level2but.addActionListener(this);
		
		level3but.setSize(310, 30);
		level3but.setLocation(895, 570);
		level3but.addActionListener(this);
		
		
		theframe.addKeyListener(this);
		
		//from mainmenu1
		
		// serverbut
		serverbut.setSize(150, 50);
		serverbut.setLocation(465, 650);
		serverbut.addActionListener(this);
		theframe.add(serverbut);
		serverbut.setEnabled(false);
		
		// readybut
		readybut.setSize(150, 50);
		readybut.setLocation(1100, 650);
		readybut.addActionListener(this);
		
		readybut.setEnabled(false);
		
		// clientbut
		clientbut.setSize(150, 50);
		clientbut.setLocation(665, 650);
		clientbut.addActionListener(this);
		theframe.add(clientbut);
		clientbut.setEnabled(false);
		
		// IPinput
		IPinput.setSize(300, 50);
		IPinput.setLocation(750, 50);
		IPinput.addActionListener(this);
		theframe.add(IPinput);
		IPinput.setEnabled(false);
		
		// IPlabel
		IPlabel.setSize(120, 50);
		IPlabel.setLocation(650, 50);
		theframe.add(IPlabel);
		
		// usernameinput
		usernameinput.setSize(300, 50);
		usernameinput.setLocation(225, 50);
		usernameinput.addActionListener(this);
		theframe.add(usernameinput);
		
		// theusername
		theusername.setSize(120, 50);
		theusername.setLocation(150, 50);
		theframe.add(theusername);
		
		thetimer.start();
		
		
	}
	
	// main
	public static void main(String[] args){
		new mainprogram();
	}

}

