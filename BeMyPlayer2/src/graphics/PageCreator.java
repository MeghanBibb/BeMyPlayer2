package graphics;

/**
 * The Class PageCreator.
 */
public class PageCreator {
	
	/** The Constant CREATE_ACCOUNT_PAGE. */
	public static final String CREATE_ACCOUNT_PAGE = "create account";
	
	/** The Constant EDIT_ACCOUNT_PAGE. */
	public static final String EDIT_ACCOUNT_PAGE = "edit account";
	
	/** The Constant FORGOT_PASSWORD_PAGE. */
	public static final String FORGOT_PASSWORD_PAGE = "forgot password";
	
	/** The Constant HOME_PAGE. */
	public static final String HOME_PAGE = "home page";
	
	/** The Constant LOGIN_PAGE. */
	public static final String LOGIN_PAGE = "login page";
	
	/** The Constant MESSAGE_PAGE. */
	public static final String MESSAGE_PAGE = "message page";
	
	/** The Constant PAYMENT_PAGE. */
	public static final String PAYMENT_PAGE = "payment page";
	
	/** The Constant SUPPORT_PAGE. */
	public static final String SUPPORT_PAGE = "support page";
	
	/** The Constant SWIPE_PAGE. */
	public static final String SWIPE_PAGE = "swipe page";
	
	/** The Constant MATCHES_PAGE. */
	public static final String MATCHES_PAGE = "matches page";
	
	/** The Constant PROFILE_PAGE. */
	public static final String PROFILE_PAGE = "profile page";
	
	/** The Constant CREDITS_PAGE. */
	public static final String CREDITS_PAGE = "credits page";
	
	/**
	 * Gets the page.
	 *
	 * @param pageType the page type
	 * @return the page
	 */
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
		case CREDITS_PAGE: return new CreditsPageController();
		default: return null;
		}
	}

}
