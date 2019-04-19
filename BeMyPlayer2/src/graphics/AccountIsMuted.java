package graphics;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import model.ResourceManager;

public class AccountIsMuted {
	public static void Warning(JFrame mainFrame) {
		
		 Timer time = new Timer(1000,new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	        	 GraphicsController.processPage(PageCreator.HOME_PAGE, PageController.backPage);
	         }
	         
	     });
		 
		 JLabel background = new JLabel(new ImageIcon(ResourceManager.loadImage("mute.png").getScaledInstance(500, 400, Image.SCALE_SMOOTH)));
		 background.setBounds(0, 0, 500, 400);
		 JLabel unmuteLabel = new JLabel("Please unmute your account");
		 unmuteLabel.setFont(Fonts.getFont(20f));
		 unmuteLabel.setForeground(Colors.Yellow);
		 unmuteLabel.setBounds(100, 300, 200, 50);
	     time.setRepeats(false);
	     mainFrame.getContentPane().removeAll();
	     
     	 JPanel panel = new JPanel();
     	 panel.setMinimumSize(new Dimension(500,400));
     	 panel.setMaximumSize(new Dimension(500,400));
     	 panel.setPreferredSize(new Dimension(500,400));
     	 panel.setLayout(null);
    	 panel.add(background);
    	 
    	 mainFrame.setContentPane(panel);
    	 mainFrame.getContentPane().add(unmuteLabel);
    	 mainFrame.pack();
    	 mainFrame.setVisible(true);
    	 time.start();
	     
	}

}
