package graphics;

import javax.swing.*;

import model.InformationExpert;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;
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
                if(validateSupInfo() == true) {
                    logger.info("Submit");
                    //	logic for updating information here
                    Logger supportLogger = Logger.getLogger(SupportController.class.getName());
                		try {
                			InputStream configFile = GraphicsController.class.getClassLoader().getResourceAsStream("supportLogger.properties");
                			LogManager.getLogManager().readConfiguration(configFile);
                			configFile.close();
                		} catch (IOException ex) {
                			System.out.println("WARNING: Could not open configuration file");
                		    System.out.println("WARNING: Logging not configured (console output only)");
                		}
                		supportLogger.info("User: " + InformationExpert.getActiveAccount().getEmail() + " noted issue " + this.getSupportModel().getProbArea().getSelectedItem().toString()+ " with description " + this.getSupportModel().getDescription().getText());
                	
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
        List<String> warnings = new ArrayList<>();
        if(this.supportModel.getProbArea().getSelectedIndex() ==0) {
        	valid = false;
        	warnings.add("Please select issue type\n");
        }
        if(this.supportModel.getDescription().getText().equals("")) {
        	valid = false;
        	warnings.add("Please enter a description\n");
        }
        if(valid == false) {
        	InvalidPopup p = new InvalidPopup(this.supportPanel,warnings);
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

