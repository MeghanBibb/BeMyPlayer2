package graphics;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SupportController extends PageController{
    public static final String SUBMIT = "submit";
    public static final String BACK = "back";

    private SupportModel supportModel = null;
    private JPanel supportPanel = null;

    public void launchPage(JFrame mainFrame, String back) {
    	if(back != null) {
        	backPage = back;
    	}
        SupportView.startSupportPage(this,mainFrame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case SUBMIT:
                if(validateSupInfo()) {
                    System.out.println("Submit");
                    //	logic for updating information here
                    GraphicsController.processPage(PageCreator.HOME_PAGE,backPage);
                }
                break;
            case BACK:
                System.out.println("Back");
                GraphicsController.processPage(PageCreator.HOME_PAGE,backPage);
        }
    }

    public boolean validateSupInfo() {
        boolean valid = true;

        //	CHECK FIELDS ARE NOT EMPTY OR SQL COMMANDS TO DELETE OUR TABLES
        //	VALIDATION FROM CREATE ACCOUNT PAGE + DATABASE VALIDATION

        return valid;
    }

    public SupportModel getSupportModel() {
        return supportModel;
    }

    public void setSupportModel(SupportModel supportModel) {
        this.supportModel = supportModel;
    }

    public JPanel getSupportPanel() {
        return supportPanel;
    }

    public void setSupportPanel(JPanel supportPanel) {
        this.supportPanel = supportPanel;
    }
}
