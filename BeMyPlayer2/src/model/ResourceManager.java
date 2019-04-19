package model;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import firebase.FireBaseAdapter;

// TODO: Auto-generated Javadoc
/**
 * The Class ResourceManager.
 */
public class ResourceManager {
	
	/** The Constant IMAGE_FOLDER. */
	public static final String IMAGE_FOLDER = "src/main/resources/img/";
	
	/** The Constant FONTS_FOLDER. */
	public static final String FONTS_FOLDER = "src/main/resources/fonts/";
	
	/** The logger. */
	private static Logger LOGGER = Logger.getLogger(ResourceManager.class.getName());
	
	/**
	 * Load image.
	 *
	 * @param name the name
	 * @return the buffered image
	 */
	public static BufferedImage loadImage(String name) {
		try {
			return ImageIO.read(new File(IMAGE_FOLDER + name));
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to load image resource: " + name);
		}
		return null;
	}
	
	/**
	 * Load font.
	 *
	 * @param name the name
	 * @param size the size
	 * @return the font
	 */
	public static Font loadFont(String name, float size) {
		InputStream fontStream;
		try {
			File fontFile = new File(FONTS_FOLDER + name);
			fontStream = new BufferedInputStream(new FileInputStream(fontFile));
		} catch (FileNotFoundException e1) {
			LOGGER.log(Level.SEVERE, "Failed to find font resource: " + name);
			return null;
		}
		try {
			return Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(Font.PLAIN, size);
		} catch (FontFormatException | IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to load font resource: " + name);
		}
		return null;
	}
	
	/**
	 * Load font.
	 *
	 * @param name the name
	 * @return the font
	 */
	public static Font loadFont(String name) {
		InputStream fontStream;
		try {
			File fontFile = new File(FONTS_FOLDER + name);
			fontStream = new BufferedInputStream(new FileInputStream(fontFile));
		} catch (FileNotFoundException e1) {
			LOGGER.log(Level.SEVERE, "Failed to find font resource: " + name);
			return null;
		}
		try {
			return Font.createFont(Font.TRUETYPE_FONT, fontStream);
		} catch (FontFormatException | IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to load font resource: " + name);
		}
		return null;
	}
}
