package model;

public class PaymentInfo implements DBSerializable {
	
	public static final String _NUMBER = "payment_number",
			                   _USER_ID = "user_ID",
			                   _MONTH = "month",
			                   _YEAR = "year",
			                   _CVC = "cvc";
	
	private String paymentNumber;
	private String userID;
	private long expirationMonth;
	private long expirationYear;
	private long cvcNumber;
	
	public PaymentInfo(String paymentNumber, String userID, long month, long year, long cvc) {
		this.userID = userID;
		this.paymentNumber = paymentNumber;
		this.expirationMonth = month;
		this.expirationYear = year;
		this.cvcNumber = cvc;
	}
	
	public PaymentInfo(String userID) {
		this.userID = userID;
	}
	
	public String getID() {
		return this.userID;
	}

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
