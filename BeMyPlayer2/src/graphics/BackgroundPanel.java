package graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.ResourceManager;

public class BackgroundPanel extends JPanel {
	
	public static BufferedImage baseImage = ResourceManager.loadImage("background.png");
	
	
	private Image scaledBackground;
	private boolean updated = false;
	private int offsetX = 0, offsetY = 0;
	
	public BackgroundPanel(Object object) {
		super(null);
		scaledBackground = baseImage;
		updated = false;
	}
	
	
	@Override
	  protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	  
	    if(!updated && this.getHeight() > 0 && this.getWidth() > 0) {
	    	resize();
	    	this.updated = true;
	    }
		g.drawImage(scaledBackground, 0, 0, null);
		
	}
	
	private void resize() {
		
		offsetX = 0;
		offsetY = 0;
		int newWidth = 0, newHeight = 0;
		double baseAspect = (double) baseImage.getHeight() / (double) baseImage.getWidth();
		double fitAspect = (double) this.getHeight() / (double) this.getWidth();
		
		if(fitAspect > baseAspect) {
			newHeight = this.getHeight();
			newWidth = 1 + (int)(this.getHeight() / baseAspect);
			offsetX = newWidth - this.getWidth();
		}else {
			newWidth = this.getWidth();
			newHeight = 1 + (int)(this.getWidth() * baseAspect);
			offsetY = newHeight - this.getHeight();
		}
				
		scaledBackground = baseImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
	}
	
	public void update() {
		updated = false;
	}
}
