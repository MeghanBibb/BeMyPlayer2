package graphics;

import java.awt.*;

import javax.swing.JFrame;

public class SwipePageModel extends JFrame {
	
	private Dimension pageSize;
	private Color background;
	SwipeButton left;
	SwipeButton right;
	GridBagConstraints constraints = new GridBagConstraints();
	GridBagLayout layout = new GridBagLayout();
	
	public SwipePageModel(){
		
		super();
		
		this.pageSize = new Dimension(700, 400);
		this.background = new Color(128,0,0);
		this.left = new SwipeButton(new SwipeLeftController());
		this.right = new SwipeButton(new SwipeRightController());
		
		this.setLayout(layout);
		this.setMinimumSize(pageSize);
		
		constraints.fill = GridBagConstraints.WEST;
		constraints.weightx = 5.0;
		constraints.weighty = 5.0;
		constraints.ipadx = 40;
		constraints.ipady = 50;
		layout.setConstraints(this.left, constraints);
		this.add(this.left);
		
		constraints.fill = GridBagConstraints.EAST;
		constraints.weightx = 5.0;
		constraints.weighty = 5.0;
		constraints.ipadx = 40;
		constraints.ipady = 50;
		layout.setConstraints(this.right, constraints);
		this.add(this.right);
		
		this.setTitle("Start Swiping");
		this.getContentPane().setBackground(background);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}
