package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class SwipePageController extends PageController {
	SwipePageModel model;

	public SwipePageController() {
	}

	@Override
	public void launchPage(JFrame mainFrame, String back) {
		if(back != null) {
			backPage = back;
		}

		this.model = new SwipePageModel(mainFrame);
		model.backButton.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    			System.out.println("back");
	    			GraphicsController.processPage(PageCreator.HOME_PAGE,backPage);
	    	}
	    });
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
