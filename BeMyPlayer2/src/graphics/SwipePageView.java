package graphics;

import javax.swing.JFrame;

public class SwipePageView {
	public static void launchSwipePage(JFrame frame) {
		SwipePageController pageController = new SwipePageController(new SwipePageModel(frame));
		frame.setVisible(true);
	}
}