package graphics;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class SupportController extends PageController{
    public static final String SUBMIT = "submit";
    public static final String BACK = "back";

    private SupportModel supportModel = null;
    private JPanel supportPanel = null;
    private static Logger logger = Logger.getLogger(SupportController.class.getName());
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
                    logger.info("Submit");
                    //	logic for updating information here
                    GraphicsController.processPage(PageCreator.HOME_PAGE,backPage);
                }
                break;
            case BACK:
                logger.info("Back");
                GraphicsController.processPage(PageCreator.HOME_PAGE,backPage);
        }
    }

    public boolean validateSupInfo() {
        boolean valid = true;

        //	CHECK FIELDS ARE NOT EMPTY OR SQL COMMANDS TO DELETE OUR TABLES
        //	VALIDATION FROM CREATE ACCOUNT PAGE + DATABASE VALIDATION
        if(this.supportModel.getDescription().getText().equals("")) {
        	valid = false;
        	InvalidPopup p = new InvalidPopup(this.supportPanel,"Please enter a description\n");
        }
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

