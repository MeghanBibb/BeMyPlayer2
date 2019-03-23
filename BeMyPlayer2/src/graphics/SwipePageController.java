package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwipePageController {
	SwipePageModel model;

	public SwipePageController(SwipePageModel model) {
		model.backButton.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    			System.out.println("back");
	    			GraphicsController.launchHomePage();
	    	}
	    });
	}
	
}
