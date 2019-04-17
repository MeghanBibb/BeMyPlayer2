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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

//import firebase.FireBaseAdapter;

public class ResourceManager {
	
	public static final String IMAGE_FOLDER = "BeMyPlayer2\\src\\main\\resources\\img\\";
	public static final String FONTS_FOLDER = "BeMyPlayer2/JetSet.ttf";
	
	private static Logger LOGGER = Logger.getLogger(ResourceManager.class.getName());
	
	public static BufferedImage loadImage(String name) {
		try {
			return ImageIO.read(new File("img\\background.png"));
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to load image resource: " + name);
			LOGGER.log(Level.SEVERE, "From Path: " + IMAGE_FOLDER + name);
		}
		return null;
	}
	
	public static Font loadFont(String name, float size) {
		InputStream fontStream;
		try {
			fontStream = new BufferedInputStream(new FileInputStream("JetSet.ttf"));
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
	
	public static Font loadFont(String name) {
		InputStream fontStream;
		try {
			fontStream = new BufferedInputStream(new FileInputStream("JetSet.ttf"));
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
