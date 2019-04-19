package graphics;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JLabel;

import model.ResourceManager;

// TODO: Auto-generated Javadoc
/**
 * The Class Fonts.
 */
public class Fonts {
	
	/** The Constant DEFAULT. */
	public static final Font DEFAULT = new JLabel().getFont();
	
	/** The Constant MONOSPACED. */
	public static final Font MONOSPACED = new Font("Monospaced",Font.BOLD,14);
	
	/** The Constant JETSET. */
	public static final Font JETSET = ResourceManager.loadFont("JetSet.ttf");
	
	/** The Constant FONT_CLASS_DEFAULT_FONT. */
	private static final Font FONT_CLASS_DEFAULT_FONT = JETSET;
	
	/*		
	static Font font = null;
	static {
        try {
        	InputStream myStream = new BufferedInputStream(new FileInputStream("JetSet.ttf"));
			font = Font.createFont(Font.TRUETYPE_FONT, myStream);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
	/**
	 * Gets the font.
	 *
	 * @return the font
	 */
	static Font getFont() {
		return FONT_CLASS_DEFAULT_FONT;
	}
	
	/**
	 * Gets the font.
	 *
	 * @param size the size
	 * @return the font
	 */
	static Font getFont(float size) {
        return FONT_CLASS_DEFAULT_FONT.deriveFont(Font.PLAIN, size);
	}
	
	/**
	 * Gets the font.
	 *
	 * @param font the font
	 * @param size the size
	 * @return the font
	 */
	static Font getFont(Font font, float size) {
        return font.deriveFont(Font.PLAIN, size);
	}
}
