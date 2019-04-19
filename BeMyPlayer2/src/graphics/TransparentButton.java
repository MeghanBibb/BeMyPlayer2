package graphics;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

// TODO: Auto-generated Javadoc
/**
 * The Class TransparentButton.
 */
public class TransparentButton extends JButton{
	
    /**
     * Instantiates a new transparent button.
     *
     * @param text the text
     */
    public TransparentButton(String text) {

        super(text);

        setOpaque(false);

    }
	
    /* (non-Javadoc)
     * @see javax.swing.JComponent#paint(java.awt.Graphics)
     */
    public void paint(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

        super.paint(g2);

        g2.dispose();

    }
}
