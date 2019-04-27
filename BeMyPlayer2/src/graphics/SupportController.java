package graphics;

import javax.swing.*;

import firebase.DBFailureException;
import model.ClientManager;
import model.Issue;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * The Class SupportController.
 */
public class SupportController extends PageController{
    
    /** The Constant SUBMIT. */
    public static final String SUBMIT = "submit";
    
    /** The Constant BACK. */
    public static final String BACK = "back";

    /** The support model. */
    private SupportModel supportModel = null;
    
    /** The support panel. */
    private JPanel supportPanel = null;
    
    /** The logger. */
    private static Logger logger = Logger.getLogger(SupportController.class.getName());
    
    /* (non-Javadoc)
     * @see graphics.PageController#launchPage(javax.swing.JFrame, java.lang.String)
     */
    public void launchPage(JFrame mainFrame, String back) {
    	if(back != null) {
        	backPage = back;
    	}
        SupportView.startSupportPage(this,mainFrame);
    }

    /* (non-Javadoc)
     * @see graphics.PageController#actionPerformed(java.awt.event.ActionEvent)
     */
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
                		supportLogger.info("User: " + ClientManager.getActiveAccount().getEmail() + " noted issue " + this.getSupportModel().getProbArea().getSelectedItem().toString()+ " with description " + this.getSupportModel().getDescription().getText());
                	
                		Issue iss = new Issue(this.getSupportModel().getDescription().getText(), (String) this.getSupportModel().getProbArea().getSelectedItem(), ClientManager.getActiveUserID());
                	try {
                		ClientManager.addIssue(iss);
                	} catch (DBFailureException exc1) {
                		exc1.printStackTrace();
                		supportLogger.fine("could not load database");
                	}
                    GraphicsController.processPage(PageCreator.HOME_PAGE,backPage);
                }
                break;
            case BACK:
                logger.info("Back");
                GraphicsController.processPage(PageCreator.HOME_PAGE,backPage);
        }
    }

    /**
     * Validate sup info.
     *
     * @return true, if successful
     */
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

        if (this.supportModel.getDescription().getText().contains(";")){
            valid = false;
            warnings.add("No Semi-Colons\n");
        }

        if(valid == false) {
        	InvalidPopup p = new InvalidPopup(this.supportPanel,warnings);
        }
        return valid;
    }

    /**
     * Gets the support model.
     *
     * @return the support model
     */
    public SupportModel getSupportModel() {
        return supportModel;
    }

    /**
     * Sets the support model.
     *
     * @param supportModel the new support model
     */
    public void setSupportModel(SupportModel supportModel) {
        this.supportModel = supportModel;
    }

    /**
     * Gets the support panel.
     *
     * @return the support panel
     */
    public JPanel getSupportPanel() {
        return supportPanel;
    }

    /**
     * Sets the support panel.
     *
     * @param supportPanel the new support panel
     */
    public void setSupportPanel(JPanel supportPanel) {
        this.supportPanel = supportPanel;
    }
}

