import java.io.File;
import javax.swing.JOptionPane;

public class GetFile {
    /**
	 * method to a file with relative location
	 * @param name, width, height
	 * @return // image 
	 * @see none
	 */
	public File getFile(String name){
		File file = null; // image
		try { 
			String currentDirectoryPath = System.getProperty("user.dir"); //get current user directory
			currentDirectoryPath = currentDirectoryPath.replace("\\src", "");
            file  = new File(currentDirectoryPath, String.format("bin/%s", name)); // search for file in bin
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e, null, 0);
		} // end catch
		return file;
	} // end method get Image
}
