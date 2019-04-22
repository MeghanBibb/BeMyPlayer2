package graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.ResourceManager;

public class ImgButton extends JButton{

	static BufferedImage image = null;
	


	static {
		image = ResourceManager.loadImage("yellow_button.png");
	}
	
	public ImgButton(String text){
		super(text);
		this.setOpaque(false);
		this.setBackground((new java.awt.Color(255, 255, 255, 0)));
		this.setBorder(new EmptyBorder(0, 0, 0, 0));
	}
	
    protected void paintComponent(Graphics g) {  
          
        this.setMinimumSize(new Dimension((this.getWidth()), this.getHeight()));
        g.drawImage(image.getScaledInstance((int) (this.getMinimumSize().getWidth()), this.getHeight(), Image.SCALE_SMOOTH), 0, 0, null);  
        super.paintComponent(g);
    }  

}
