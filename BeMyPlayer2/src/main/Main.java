package main;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import graphics.BackgroundMusic;
import graphics.GraphicsController;
import model.ClientManager;

public class Main {
	

	private static Logger logger = Logger.getLogger(GraphicsController.class.getName());
	
	static {
		try {
			InputStream configFile = GraphicsController.class.getClassLoader().getResourceAsStream("logger.properties");
			LogManager.getLogManager().readConfiguration(configFile);
			configFile.close();
		} catch (IOException ex) {
			System.out.println("WARNING: Could not open configuration file");
		    System.out.println("WARNING: Logging not configured (console output only)");
		}
		logger.info("running graphic controller");
	}

	public static void main(String[] args) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		}
		catch (Exception ex) {logger.warning("Failed to load look and feel for main");}
		
		// See the updated Account object in the model package...
		BackgroundMusic.getInstance().music();
		ClientManager.initialize();
		GraphicsController g = new GraphicsController();
	}

}
