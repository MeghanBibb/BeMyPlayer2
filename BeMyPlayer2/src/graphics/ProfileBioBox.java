package graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import model.ResourceManager;

public class ProfileBioBox extends JLabel{

	static BufferedImage image = null;

	static {
		image = ResourceManager.loadImage("bio_background.png");
	}
	
	public ProfileBioBox() {
		super();
		this.setBorder(new EmptyBorder(30, 30, 30, 30));
		this.setFont(Fonts.getFont(12f));
	}

	public ProfileBioBox(String text) {
		super(text);
		this.setBorder(new EmptyBorder(30, 30, 30, 30));
		this.setFont(Fonts.getFont(12f));
	}
	@Override
	protected void paintComponent(Graphics g) {  
		Graphics2D g2 = (Graphics2D)g;
	    this.setMinimumSize(new Dimension((this.getWidth()), this.getHeight()));
	    RoundRectangle2D rect = new RoundRectangle2D.Float(-10, 0, this.getWidth(), this.getHeight(), 10, 10);
	
	    g2.setClip(rect);
	    g2.clip(rect);
	        
	    g.drawImage(image.getScaledInstance((int)this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH), -10, 0, null);
	        
	    super.paintComponent(g);
	 } 
	
}
