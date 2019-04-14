package graphics;

import java.awt.event.ActionEvent;

import firebase.DBFailureException;
import model.InformationExpert;
import model.Match;
import model.MatchStatus;
import model.MatchType;

public class SwipeRightController extends SwipeButtonController{
	SwipePageController controller;
	
	public SwipeRightController(SwipePageController controller) {
		super("Right");
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Match thisMatch;
			if((thisMatch = InformationExpert.getMatch(InformationExpert.getActiveAccount().getAccountProfile(), InformationExpert.getOtherProfile())) != null) {
				/* CHECK TO MAKE SURE MATCH TYPE IS THE SAME IE: loveType on loveSwipePage*/
				//if(thisMatch.getType == controller.getType){
				thisMatch.setClientMatchStatus(MatchStatus.SWIPE_RIGHT);	
				//}
				    
				//update match
				InformationExpert.updateMatch(thisMatch);
				
			} else {
				//create new match
				thisMatch = new Match(InformationExpert.getActiveAccount().getAccountProfile(), InformationExpert.getOtherProfile());
				thisMatch.setClientMatchStatus(MatchStatus.SWIPE_RIGHT);
				thisMatch.setOtherMatchStatus(MatchStatus.NO_MATCH);
				/*SET MATCH TYPE TO FRIEND OR LOVE DEPENDING ON PAGE*/
				thisMatch.setType(MatchType.LOVE_MATCH);
				System.out.println("HERE");
				
				//add match
				InformationExpert.addMatch(thisMatch);
			}
		} catch (DBFailureException e1) {
			System.out.println("here");
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {

			try {
				controller.setProfile(InformationExpert.getUserAccountWithProfile("LfiDeQ0WNQEnNyZ1c94J").getAccountProfile());
			} catch (DBFailureException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("cant load next account");
			}
		}
		
	}

}