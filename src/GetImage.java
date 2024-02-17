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
import java.io.IOException;

import javax.imageio.ImageIO;
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
			BufferedImage newbufferedImage = ImageIO.read(Speed.class.getClassLoader().getResource(name)); // create buffered image
			newImage = newbufferedImage.getScaledInstance(width,height,Image.SCALE_DEFAULT); // set new image as buffered image
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // end catch
		
		return newImage;
	} // end method get Image
	
} // end class GetImage