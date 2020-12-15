package exceptions;

public class BelowMinimumBalanceException extends ExceedLimitException {
//OVERVIEW: exception subclasses from ExceedLimitException

	public BelowMinimumBalanceException(String msg) {
		//EFFECTS: display error message
		super(msg);
	}
}
