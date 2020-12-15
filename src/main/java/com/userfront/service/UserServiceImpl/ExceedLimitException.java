package exceptions;

public class ExceedLimitException extends TransactionAbortedException {
	//OVERVIEW: exception subclasses from TransactionAbortedException

	public ExceedLimitException(String msg) {
		//EFFECTS: display error message
		super(msg);
	}
}
