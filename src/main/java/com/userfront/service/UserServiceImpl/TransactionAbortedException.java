package exceptions;

public class TransactionAbortedException extends Exception {
	//OVERVIEW: exception subclasses from Exception

	public TransactionAbortedException(String msg) {
		//EFFECTS: display error message
		super(msg);
	}

}
