package graphics;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.JLabel;

public class TransparentImage extends JLabel{
	public TransparentImage(Icon image) {
		super(image);
	}
	
	public TransparentImage() {
		super();
	}
	
	@Override
	  protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
	    super.paintComponent(g2d);
	}
	
}
