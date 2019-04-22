package graphics;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

// TODO: Auto-generated Javadoc
/**
 * The Class CircularImage.
 */
public class CircularImage extends JLabel{
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(CircularImage.class.getName());
	/**
	 * Instantiates a new circular image.
	 *
	 * @param image the image
	 */
	public CircularImage(Icon image) {
		super(image);
	}
	
	/**
	 * Instantiates a new circular image.
	 */
	public CircularImage() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	  protected void paintComponent(Graphics g) {
		Ellipse2D r = new Ellipse2D.Float(this.getAlignmentX(), this.getAlignmentY(), this.getWidth(), this.getHeight());
		g.setClip(r);
	    super.paintComponent(g);
	}
	
}
