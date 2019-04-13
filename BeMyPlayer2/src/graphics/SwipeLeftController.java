package graphics;

import java.awt.event.ActionEvent;

public class SwipeLeftController extends SwipeButtonController{
	SwipePageController controller;
	public SwipeLeftController(SwipePageController controller) {
		super("Left");
		this.controller = controller;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

}