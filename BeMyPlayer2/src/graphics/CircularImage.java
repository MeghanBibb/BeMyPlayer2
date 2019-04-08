package graphics;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CircularImage extends JLabel{
	
	public CircularImage(Icon image) {
		super(image);
	}
	
	@Override
	  protected void paintComponent(Graphics g) {
		Ellipse2D r = new Ellipse2D.Float(this.getAlignmentX(), this.getAlignmentY(), this.getWidth(), this.getHeight());
		
		g.setClip(r);
	    super.paintComponent(g);
	}
	
}
