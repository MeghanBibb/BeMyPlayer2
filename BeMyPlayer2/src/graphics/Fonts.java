package graphics;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Fonts {
	static Font getFont(Float size) {
		Font font = null;
		try {
			
			InputStream myStream = new BufferedInputStream(new FileInputStream("JetSet.ttf"));
            font = Font.createFont(Font.TRUETYPE_FONT, myStream);
            font = font.deriveFont(Font.PLAIN, size);

		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return font;
	}
}
