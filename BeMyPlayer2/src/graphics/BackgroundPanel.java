package graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import model.ResourceManager;

/**
 * The Class BackgroundPanel.
 */
public class BackgroundPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The logger. */
	/** The base image. */
	public static BufferedImage baseImage = ResourceManager.loadImage("background.png");
	
	
	/** The scaled background. */
	private Image scaledBackground;
	
	/** The updated. */
	private boolean updated = false;
	
	/**
	 * Instantiates a new background panel.
	 *
	 * @param object the object
	 */
	public BackgroundPanel(Object object) {
		super(null);
		scaledBackground = baseImage;
		updated = false;
	}
	
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	  protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	  
	    if(!updated && this.getHeight() > 0 && this.getWidth() > 0) {
	    	resize();
	    	this.updated = true;
	    }
		g.drawImage(scaledBackground, 0, 0, null);
		
	}
	
	/**
	 * Resize.
	 */
	private void resize() {
		int newWidth = 0, newHeight = 0;
		double baseAspect = (double) baseImage.getHeight() / (double) baseImage.getWidth();
		double fitAspect = (double) this.getHeight() / (double) this.getWidth();
		
		if(fitAspect > baseAspect) {
			newHeight = this.getHeight();
			newWidth = 1 + (int)(this.getHeight() / baseAspect);
		}else {
			newWidth = this.getWidth();
			newHeight = 1 + (int)(this.getWidth() * baseAspect);
		}
				
		scaledBackground = baseImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
	}
	
	/**
	 * Update.
	 */
	public void update() {
		updated = false;
	}
}
