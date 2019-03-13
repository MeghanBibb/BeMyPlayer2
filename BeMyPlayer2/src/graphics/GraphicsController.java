package graphics;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GraphicsController {
	private static JFrame mainFrame;

	GraphicsController(Account a) {
//			init default jframe as base frame
			this.mainFrame = (new JFrame("BeMyPlayer2"));
			this.mainFrame.setSize(400, 300);
			this.mainFrame.setMaximumSize(new Dimension(500,400));
			this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.mainFrame.getContentPane().setLayout(null);
			this.mainFrame.setResizable(false);
			
			/*
			LoginPageController controller = new LoginPageController();
			controller.launchLoginPage(this.mainFrame);
			
			this.mainFrame.removeAll();
			
			*/
			CreateAccountPageController createPageController = new CreateAccountPageController();
			createPageController.launchCreateAccountPage(this.mainFrame,a);
			/*
			 * 
			 * frame.getContentPane().removeAll();
				frame.getContentPane().add(mainPanel);
				frame.revalidate();
			 */
	}
	public static void main(String[] args) {
		Account a = new Account();
		GraphicsController g = new GraphicsController(a);
	}
	public static JFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	
}
