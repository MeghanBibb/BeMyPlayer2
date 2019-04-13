package graphics;

import java.awt.event.ActionEvent;

import firebase.DBFailureException;
import model.InformationExpert;

public class SwipeRightController extends SwipeButtonController{
	SwipePageController controller;
	
	public SwipeRightController(SwipePageController controller) {
		super("Right");
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			controller.setProfile(InformationExpert.getUserAccountWithProfile("LfiDeQ0WNQEnNyZ1c94J").getAccountProfile());
		} catch (DBFailureException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}