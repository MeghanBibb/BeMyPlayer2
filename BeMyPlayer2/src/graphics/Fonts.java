package graphics;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Fonts {
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
	static Font getFont(Float size) {
        font = font.deriveFont(Font.PLAIN, size);
		return font;
	}
}
