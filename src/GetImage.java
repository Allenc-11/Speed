/*GetImage.java
*
* Description: Class used to get Images
*
*
* Name: Allen Chen and Nicholas Lo
* Date: Jan 21, 2023
*/
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
public class GetImage{
	/**
	 * method to get and image give location
	 * @param name, width, height
	 * @return // image 
	 * @see none
	 */
	public Image getImage(String name, int width, int height){
		Image newImage = null; // image
		try { 
			GetFile getFile = new GetFile();
			BufferedImage newbufferedImage = ImageIO.read(getFile.getFile(name)); // create buffered image
			newImage = newbufferedImage.getScaledInstance(width,height,Image.SCALE_DEFAULT); // set new image as buffered image
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e, null, 0);
		} // end catch
		
		return newImage;
	} // end method get Image
	
} // end class GetImage