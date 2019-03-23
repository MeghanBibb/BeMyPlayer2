package graphics;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Account;

public class ViewMatchesController implements ActionListener{
//	action commands 	
	public static final String NEXT = "next";
	public static final String BACK="back";
	public static final String SUBMIT = "submit";
	public static final String PROFILE = "profileclick";
	private ViewMatchesModel viewMatchesModel;
	private JPanel viewMatchesPanel;
	private int pageNum;
	private Account a;
	private JFrame copyFrame;
	private ProfileBriefModel brief = null;
	public void launchViewMatches(JFrame j,Account a) {
		this.a = a;
		this.copyFrame = j;
		ViewMatchesView.startViewMatches(this,j,a);
	}
	//	check command 
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand() == BACK) {
			System.out.println("back");
			GraphicsController.launchHomePage();
		}
		else if(e.getActionCommand() == PROFILE) {
			String text = ((JButton) e.getSource()).getName();
			System.out.println("launch profile brief for: " + text);
			if(brief == null) {
				brief = new ProfileBriefModel(text,new Rectangle(250,120,215,245));
			}
			else {
				this.copyFrame.remove(brief);
				brief = new ProfileBriefModel(text,new Rectangle(250,120,215,245));
			}
			this.copyFrame.add(brief);
			this.copyFrame.repaint();
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
