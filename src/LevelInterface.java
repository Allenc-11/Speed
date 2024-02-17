/*LevelInterface.java
*
* Description: Level Interface for Speed Game
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

class LevelInterface {
	private JFrame window; // JFrame
	private JPanel placeHolder; // JPanel 
	private JButton easyButton, mediumButton, hardButton; // JButtons
	private GetImage getImage = new GetImage(); // create new GetImage
	private JButton buttons[] = {easyButton, mediumButton, hardButton}; // create an array of buttons
	private int width, height; // width and height of window
	private static int level = 1; // game level
	private MainInterface main; // Main Interface
	
	/**
	 * constructor for LevelInterface
	 * 
	 * @param inputWindow, width, and height
	 * @return none
	 * @see none
	 */
	public LevelInterface(JFrame inputWindow, int winWidth, int winHeight) {
		window = inputWindow; // set window
		width = winWidth; // set width
		height = winHeight; // set height
	} // end constructor
	
	/**
	 * method to show interface
	 * 
	 * @param none
	 * @return none
	 * @see none
	 */
	public void showInterface(){
		window.setContentPane((Container) (createContent(width,height))); // set content pane of window
	} // end method showInterface
	
	/**
	 * method to create content pane
	 * 
	 * @param width and height
	 * @return Component
	 * @see showInterface()
	 */
	private Component createContent(int width,int height) {
		final Image image = getImage.getImage("BackgroundWithTitle.jpg",width,height-60); // get image for background
		@SuppressWarnings("serial")
		JPanel panel = new JPanel() {
		    @Override
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g); // call superclass's paint method
		        g.drawImage(image, 0, 0, null); // draw background onto JPanel
		    } // end method paintComponent
		};
		
		panel.setSize(width, height); // set size of panel
		panel.setLayout(new GridBagLayout()); // set layout to gridbag
		GridBagConstraints c = new GridBagConstraints(); // create new constraints
		
		JPanel placeHolder = new JPanel(); // create place holder panel
		placeHolder.setOpaque(false); // set panel to transparent
		
		JPanel buttonPanel = new JPanel(); // create new button JPanel
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // set layout to box layout
		buttonPanel.setOpaque(false); // set buttonPanel to transparent
		ButtonHandler handler = new ButtonHandler(); // create new button handler
		
		// initialize all the button
		for (int i = 0; i < buttons.length; i++) { // loop 3 times
			buttons[i] = new JButton(); // create JButton
			buttons[i].setMargin(new Insets(0,0,0,0)); // set margins for buttons
			buttons[i].addActionListener(handler); // add action listener
		} // end for
		
		// set images for all the buttons
		buttons[0].setIcon(new ImageIcon (getImage.getImage("Easy.png", width/(800/150), height/(500/60))));
		buttons[1].setIcon(new ImageIcon (getImage.getImage("Medium.png", width/(800/150), height/(500/60))));
		buttons[2].setIcon(new ImageIcon (getImage.getImage("Hard.png", width/(800/150), height/(500/60))));
		
		// add buttons to the button panel
		for (int i = 0; i < buttons.length; i++) { // loop 3 times
			buttonPanel.add(buttons[i]); // add buttons to panel
			buttonPanel.add(Box.createRigidArea(new Dimension(0, 5))); // add rigid area to panel
		} // end for
		
		// create grid constraints
		c.gridy = 3;
		c.gridx = 1;
		c.ipady = height/4;
		panel.add(placeHolder, c); // add place holder panel to the pane;
		
		// create grid constraints
		c.gridx = 1;
		c.gridy = 5;
		c.ipady = 1;
		c.gridheight = 1;
		panel.add(buttonPanel, c); // add button panel to panel

		return panel;
	} // end method createContent
	
	/**
	 * method to get level
	 * 
	 * @param none
	 * @return level
	 * @see none
	 */
	public int getLevel(){
		return level;
	} // end method getLevel
	
	/*
	 * ButtonHandler Class
	 */
	private class ButtonHandler implements ActionListener { 
		/**
		 * handle button event 
		 * 
		 * @param action event
		 * @return none
		 * @see none
		 */
		public void actionPerformed( ActionEvent event ) { 
			
			if (event.getSource() == buttons[0]) { // if first button clicked
				level = 1; // set level to 1
				main = new MainInterface(window,width,height); // create new Main Interface
				main.show(); // show main interface
			} else if (event.getSource() == buttons[1]) { // if second button clicked
				level = 2; // set level to 2
				main = new MainInterface(window,width,height); // create new Main Interface
				main.show(); // show main interface
			} else if (event.getSource() == buttons[2]) { // if third button clicked
				level = 3; // set level to 3
				main = new MainInterface(window,width,height); // create new Main Interface
				main.show(); // show main interface
			} // end else if
				
		} // end method actionPerformed 

	} // end private inner class ButtonHandler 
	
} // end class LevelInterface