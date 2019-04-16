package graphics;

public class PageCreator {
	
	public static final String CREATE_ACCOUNT_PAGE = "create account";
	public static final String EDIT_ACCOUNT_PAGE = "edit account";
	public static final String FORGOT_PASSWORD_PAGE = "forgot password";
	public static final String HOME_PAGE = "home page";
	public static final String LOGIN_PAGE = "login page";
	public static final String MESSAGE_PAGE = "message page";
	public static final String PAYMENT_PAGE = "payment page";
	public static final String SUPPORT_PAGE = "support page";
	public static final String SWIPE_PAGE = "swipe page";
	public static final String MATCHES_PAGE = "matches page";
	public static final String PROFILE_PAGE = "profile page";
	
	public static PageController getPage(String pageType) {
		switch(pageType) {
		case CREATE_ACCOUNT_PAGE: return new CreateAccountPageController();
		case EDIT_ACCOUNT_PAGE: return new EditAccountPageController();
		case FORGOT_PASSWORD_PAGE: return new ForgotPassPageController();
		case HOME_PAGE: return new HomePageController();
		case LOGIN_PAGE: return new LoginPageController();
		case MESSAGE_PAGE: return new MessageController();
		case PAYMENT_PAGE: return new PaymentPageController();
		case SUPPORT_PAGE: return new SupportController();
		case SWIPE_PAGE: return new SwipePageController();
		case MATCHES_PAGE: return new ViewMatchesController();
		case PROFILE_PAGE: return new ProfilePageController();
		default: return null;
		}
	}

}
