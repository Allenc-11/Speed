/*MainInterface.java
*
* Description: Main Interface for Speed Game
*
*
* Name: Allen Chen and Nicholas Lo
* Date: Jan 21, 2023
*/
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class MainInterface {
	private JFrame window; // Jframe
	private JPanel placeHolder; // JPanel
	private JButton startButton, levelButton, instructonsButton, exitButton; // JButton
	private GetImage getImage =  new GetImage(); // create new instance of class getImage
	private JButton buttons[] = {startButton, levelButton, instructonsButton, exitButton,}; // array of JButton
	private LevelInterface levelInterface; // LevelInterface
	private InfoInterface infoInterface; // InfoInterface
	private GetScreenDimension getSize = new GetScreenDimension(); // create new instance of GetScreenDimension
	private int width, height; // width and height of window
	private SpeedGame game; // SpeedGame
	
	/**
	 * constructor for MainInterface
	 * 
	 * @param window, winWidth, winHeight 
	 * @return none
	 * @see none
	 */
	public MainInterface(JFrame win, int winWidth, int winHeight) {
		window = win; // set window
		width = winWidth; // set width
		height = winHeight; // set height
		levelInterface = new LevelInterface(window, width, height); // create new LevelInterface
		infoInterface = new InfoInterface(window, width, height);
	} // end constructor MainInterface
	
	/**
	 * method to show window
	 * 
	 * @param none
	 * @return none
	 * @see none
	 */
	public void show() {
		window.setContentPane((Container) (createContent(width,height))); // call method set content pane
	} // end method show
	
	/**
	 * method to create the content on the window
	 * 
	 * @param width and height
	 * @return none
	 * @see show()
	 */
	private Component createContent(int width,int height) {
		final Image image = getImage.getImage("BackgroundWithTitle.jpg",width,height-60); // call get image to get background image
		@SuppressWarnings("serial")
		JPanel panel = new JPanel() { // create new JPanel
		    @Override
		    protected void paintComponent(Graphics g) { 
		        super.paintComponent(g); // call superclass's paint method
		        g.drawImage(image, 0, 0, null); // draw background image onto JPanel
		    } // end method paintComponent
		};
		
		panel.setSize(width, height); // set panel size
		panel.setLayout(new GridBagLayout()); // set layout
		GridBagConstraints c = new GridBagConstraints(); // create new gridbag constraints
		
		JPanel placeHolder = new JPanel(); // create new JPanel for placeHolder
		placeHolder.setOpaque(false); // set place holder panel to transparent
		
		JPanel buttonPanel = new JPanel(); // create new button panel
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // set layout to box layout
		buttonPanel.setOpaque(false); // set panel to transparent
		ButtonHandler handler = new ButtonHandler(); // create new button handler
		
		// set properties of all the buttons in the button array
		for (int i = 0; i < buttons.length; i++) {  // loop 4 times
			buttons[i] = new JButton(); // create now button
			buttons[i].setMargin(new Insets(0,0,0,0)); // set margins of the button
			buttons[i].addActionListener(handler); // add action listener to button
		} // end for
		
		//set image for all the button
		buttons[0].setIcon(new ImageIcon (getImage.getImage("Start.png", width/(800/150), height/(500/55))));
		buttons[1].setIcon(new ImageIcon (getImage.getImage("Level.png", width/(800/150), height/(500/55))));
		buttons[2].setIcon(new ImageIcon (getImage.getImage("Info.jpg", width/(800/150), height/(500/55))));
		buttons[3].setIcon(new ImageIcon (getImage.getImage("Exit.png", width/(800/150), height/(500/55))));
		
		// add buttons to JPanel
		for (int i = 0; i < buttons.length; i++) { // loop 4 times
			buttonPanel.add(buttons[i]); // add button to panel
			buttonPanel.add(Box.createRigidArea(new Dimension(0, 5))); // add rigid area to button
		} // end for
		
		// set gridbag constraints
		c.gridy = 3;
		c.gridx = 1;
		c.ipady = height/3;
		panel.add(placeHolder, c); // add place holder panel to main panel
		
		// set new gridbag constraints
		c.gridx = 1;
		c.gridy = 5;
		c.ipady = 1;
		c.gridheight = 1;
		panel.add(buttonPanel, c); // add button panel to main panel
		
		return panel;
	} // end method createContent
	
	/*
	 * Button Handler Class
	 */
	private class ButtonHandler implements ActionListener { 
		/**
		 * method to handle button event
		 * 
		 * @param none
		 * @return none
		 * @see none
		 */
		public void actionPerformed( ActionEvent event ) { 
			
			if (event.getSource() == buttons[0]) { // if button is 0
				game = new SpeedGame(window,levelInterface.getLevel()); // create new speed game
			} else if (event.getSource() == buttons[1]) { // if button is 1
				levelInterface.showInterface(); // show level interface
			} else if (event.getSource() == buttons[2]) { // if button is 2
				infoInterface.showInterface(); // show info Interface
			} else if (event.getSource() == buttons[3]){
				System.exit(0);
			}// end else if
			
		} // end method actionPerformed 
		
	} // end private inner class ButtonHandler 
	
} // end class Main Interface