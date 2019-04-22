package graphics;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
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
	}
	
	public ImgButton(){
		super();
		this.setOpaque(false);
		this.setBackground((new java.awt.Color(255, 255, 255, 0)));
		this.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
    protected void paintComponent(Graphics g) {  
        
        this.setMinimumSize(new Dimension((this.getWidth()), this.getHeight()));
        RoundRectangle2D rect = new RoundRectangle2D.Float();
        rect.setRoundRect(0, 0, this.getWidth(), this.getHeight(), 10, 10);
        g.setClip(rect);
        g.drawImage(image.getScaledInstance((int) ((this.getMinimumSize().getWidth())*3)/2, (this.getHeight()*3)/2, Image.SCALE_SMOOTH), -10, -5, null);
 
        super.paintComponent(g);
    }  

}
