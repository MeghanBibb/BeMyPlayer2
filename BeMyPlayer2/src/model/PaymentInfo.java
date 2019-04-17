package model;

// TODO: Auto-generated Javadoc
/**
 * The Class PaymentInfo.
 */
public class PaymentInfo implements DBSerializable {
	
	/** The Constant _CVC. */
	public static final String _NUMBER = "payment_number",
			                   _USER_ID = "user_ID",
			                   _MONTH = "month",
			                   _YEAR = "year",
			                   _CVC = "cvc";
	
	/** The payment number. */
	private String paymentNumber;
	
	/** The user ID. */
	private String userID;
	
	/** The expiration month. */
	private long expirationMonth;
	
	/** The expiration year. */
	private long expirationYear;
	
	/** The cvc number. */
	private long cvcNumber;
	
	/**
	 * Instantiates a new payment info.
	 *
	 * @param paymentNumber the payment number
	 * @param userID the user ID
	 * @param month the month
	 * @param year the year
	 * @param cvc the cvc
	 */
	public PaymentInfo(String paymentNumber, String userID, long month, long year, long cvc) {
		this.userID = userID;
		this.paymentNumber = paymentNumber;
		this.expirationMonth = month;
		this.expirationYear = year;
		this.cvcNumber = cvc;
	}
	
	/**
	 * Instantiates a new payment info.
	 *
	 * @param userID the user ID
	 */
	public PaymentInfo(String userID) {
		this.userID = userID;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getID() {
		return this.userID;
	}

	/* (non-Javadoc)
	 * @see model.DBSerializable#toDBPackage()
	 */
	@Override
	public DBDocumentPackage toDBPackage() {
		
		DBDocumentPackage p = new DBDocumentPackage(this.userID);
		p.addValue(_NUMBER, this.paymentNumber);
		p.addValue(_CVC, this.cvcNumber);
		p.addValue(_USER_ID, this.userID);
		p.addValue(_MONTH, this.expirationMonth);
		p.addValue(_YEAR, this.expirationYear);
		
		return p;
	}

	/* (non-Javadoc)
	 * @see model.DBSerializable#initializeFromPackage(model.DBDocumentPackage)
	 */
	@Override
	public void initializeFromPackage(DBDocumentPackage pkg) {
		for(String s : pkg.getValues().keySet()) {
			switch(s) {
				case _NUMBER:
					this.paymentNumber = (String) pkg.getValues().get(s);
					break;
				case _CVC:
					this.cvcNumber = (long) pkg.getValues().get(s);
					break;
				case _USER_ID:
					this.userID = (String) pkg.getValues().get(s);
					break;
				case _MONTH:
					this.expirationMonth = (long) pkg.getValues().get(s);
					break;
				case _YEAR:
					this.expirationYear = (long) pkg.getValues().get(s);
					break;
			}
		}
	}

	
	

}
