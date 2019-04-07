package graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {
	public BackgroundPanel(Object object) {
		super(null);
	}

	@Override
	  protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	  ////
		BufferedImage backgroundImage;
		try {
			
			backgroundImage = ImageIO.read(new File("background.png"));
			g.drawImage(backgroundImage.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		////
	}
}
