package firebase;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

public class ImageConverter {
	
	//static final String OS_TEMP_DIR_FILE = System.getProperty("java.io.tmpdir") + "img.jpg";
	//private static final Logger LOGGER = Logger.getLogger(ImageConverter.class.getName());
	
	//private static File tmpFile = new File(OS_TEMP_DIR_FILE);
	
	
	public static BufferedImage convertToJPG(BufferedImage image) {
		BufferedImage newBufferedImage = new BufferedImage(image.getWidth(),
					image.getHeight(), BufferedImage.TYPE_INT_RGB);
			  newBufferedImage.createGraphics().drawImage(image, 0, 0, 
					  image.getWidth(), image.getHeight(), Color.WHITE, null);
		return newBufferedImage;
	}
}
