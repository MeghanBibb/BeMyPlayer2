package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ViewMatchesController implements ActionListener{
//	action commands 	
	public static final String NEXT = "next";
	public static final String BACK="back";
	public static final String SUBMIT = "submit";
	
	private ViewMatchesModel viewMatchesModel;
	private JPanel viewMatchesPanel;
	private int pageNum;
	private Account a;
	//private JFrame copyFrame;
	
	public void launchViewMatches(JFrame j,Account a) {
		this.a = a;
		ViewMatchesView.startViewMatches(this,j,a);
	}
	//	check command 
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand() == BACK) {
			System.out.println("back");
			GraphicsController.launchHomePage();
		}
	}
	public JPanel getViewMatchesPanel() {
		return viewMatchesPanel;
	}
	public void setViewMatchesPanel(JPanel viewMatchesPanel) {
		this.viewMatchesPanel = viewMatchesPanel;
	}
	public ViewMatchesModel getViewMatchesModel() {
		return viewMatchesModel;
	}
	public void setViewMatchesModel(ViewMatchesModel viewMatchesModel) {
		this.viewMatchesModel = viewMatchesModel;
	}
}
