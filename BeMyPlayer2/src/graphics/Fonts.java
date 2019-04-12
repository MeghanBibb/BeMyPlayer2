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

public class Fonts {
	
	public static final Font DEFAULT = new JLabel().getFont();
	public static final Font MONOSPACED = new Font("Monospaced",Font.BOLD,14);
	public static final Font JETSET = ResourceManager.loadFont("JetSet.ttf");
	
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
	
	static Font getFont() {
		return FONT_CLASS_DEFAULT_FONT;
	}
	
	static Font getFont(float size) {
        return FONT_CLASS_DEFAULT_FONT.deriveFont(Font.PLAIN, size);
	}
	
	static Font getFont(Font font, float size) {
        return font.deriveFont(Font.PLAIN, size);
	}
}
