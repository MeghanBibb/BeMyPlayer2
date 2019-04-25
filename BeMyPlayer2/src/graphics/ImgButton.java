package graphics;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import model.ResourceManager;

public class ImgButton extends JButton{

	/**
	 * default serial ID
	 */
	private static final long serialVersionUID = 1L;
	static BufferedImage image = null;
	static Border b2 = null;


	static {
		image = ResourceManager.loadImage("yellow_button.png");
	}
	
	public ImgButton(String text){
		super(text);
		this.setOpaque(false);
		this.setBackground((new java.awt.Color(255, 255, 255, 0)));
		this.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.setContentAreaFilled(false);
	}
	
	public ImgButton(){
		super();
		this.setOpaque(false);
		this.setBackground((new java.awt.Color(255, 255, 255, 0)));
		this.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.setContentAreaFilled(false);
	}
	
    protected void paintComponent(Graphics g) {  
        
    	Graphics2D g2 = (Graphics2D)g;
    	
        this.setMinimumSize(new Dimension((this.getWidth()), this.getHeight()));
        RoundRectangle2D rect = new RoundRectangle2D.Float(0, 0, this.getWidth(), this.getHeight(), 10, 10);
        //rect.setRoundRect(0, 0, this.getWidth(), this.getHeight(), 10, 10);
    
        /*
         * RoundRectangle2D r = new RoundRectangle2D.Float(this.getAlignmentX(), this.getAlignmentY(), this.getWidth(), this.getHeight(), 7, 7);
		g.setClip(r);
	    super.paintComponent(g);
	    
		Image backgroundImage;
		backgroundImage = ResourceManager.loadImage("profile_brief_background.png");
		g.drawImage(backgroundImage.getScaledInstance(this.getWidth()+20, this.getHeight(), Image.SCALE_SMOOTH), 0, 0, null);	
         */
        
        g2.setClip(rect);
        g2.clip(rect);
        
        g2.drawImage(image.getScaledInstance((int) ((this.getMinimumSize().getWidth())*3)/2, (this.getHeight()*3)/2, Image.SCALE_SMOOTH), -10, -5, null);
        
 
        
        super.paintComponent(g2);
    }  

}
