package graphics;

import javax.swing.JFrame;

public class SwipePageView {
	public static void launchSwipePage(JFrame frame) {
		SwipePageModel pageModel = new SwipePageModel(frame);
		frame.setVisible(true);
	}
}
