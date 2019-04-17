package graphics;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.JLabel;

// TODO: Auto-generated Javadoc
/**
 * The Class TransparentImage.
 */
public class TransparentImage extends JLabel{
	
	/**
	 * Instantiates a new transparent image.
	 *
	 * @param image the image
	 */
	public TransparentImage(Icon image) {
		super(image);
	}
	
	/**
	 * Instantiates a new transparent image.
	 */
	public TransparentImage() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	  protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
	    super.paintComponent(g2d);
	}
	
}
