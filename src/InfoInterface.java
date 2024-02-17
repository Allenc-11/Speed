/*InfoInterface.java
*
* Description: Info Interface for Speed Game
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class InfoInterface {
	private JFrame window; // JFrame
	private JPanel infoPanel; // JPanel
	private JButton backButton; // JButton
	private GetImage getImage = new GetImage(); // create new getImage
	private int width, height; // width and height for window and level selected by user
	private MainInterface main; // Main Interface
	
	/**
	 * constructor for InfoInterface
	 * 
	 * @param inputWindow, width, and height
	 * @return none
	 * @see none
	 */
	public InfoInterface(JFrame inputWindow, int winWidth, int winHeight) {
		window = inputWindow; // set window
		width = winWidth; // set width
		height = winHeight; // set height
	} // end levelInterface constructor
	
	/**
	 * method to show interface
	 * 
	 * @param none
	 * @return none
	 * @see none
	 */
	public void showInterface(){
		window.setContentPane((Container) (createContent(width,height))); // set content pane
	} // end method showInterface
	
	/**
	 * method to create content pane
	 * 
	 * @param width and height
	 * @return Component
	 * @see showInterface()
	 */
	private Component createContent(int width,int height) {
		final Image image = getImage.getImage("Background.jpg",width,height-60);
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
		
		final Image instruction = getImage.getImage("Instructions.jpg", (int)(width / 2.3), (int) (height / 1.4)); // get instructions image
		@SuppressWarnings("serial")
		JPanel infoPanel = new JPanel() {
		    @Override
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        g.drawImage(instruction, 0, 0, null);
		    } // end method paintComponent
		};
		
		infoPanel.setOpaque(false); // set place holder to transparent
		Dimension d = new Dimension(instruction.getWidth(null), instruction.getHeight(null)); // create new dimension
		// set fixed size for infoPanel
		infoPanel.setMinimumSize(d);
		infoPanel.setPreferredSize(d);
		infoPanel.setMaximumSize(d);
		
		ButtonHandler handler = new ButtonHandler(); // create new button handler
		backButton = new JButton(); // create new JButton
		backButton.addActionListener(handler); // add Button Handler
		backButton.setIcon(new ImageIcon (getImage.getImage("Back.png", width/(800/150), height/(500/60)))); // set icon of button
		backButton.setMargin(new Insets(0,0,0,0)); // set margins for button
		
		// create grid constraints
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(0,0,0,0); // create new insets
		panel.add(infoPanel, c); // add place holder panel to main panel
		
		// create new grid constraints
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(10,0,50,0); // create new insets
		panel.add(backButton, c); // add back button to panel
		
		return panel;
	} // end createContent
	
	/*
	 * Button Handler Class
	 */
	private class ButtonHandler implements ActionListener  { 
		/**
		 * handle button event 
		 * 
		 * @param action event
		 * @return none
		 * @see none
		 */
		public void actionPerformed( ActionEvent event ) { 
			
			if (event.getSource() == backButton) { // if first button clicked
				main = new MainInterface(window,width,height); // create new main interface
				main.show(); // show main
			} // end if
			
		} // end method actionPerformed 
		
	} // end private inner class ButtonHandler 
	
} // end class LevelInterface