/*
	COMP90041 Programming and Software Development
	Semester 1, 2018
	Author's Name: Yuxin Guo
	User Name: yuxing4
	ID: 875698
	ProjectC, 16/05/2018
	This class represents the InvalidCommandException.
*/
public class InvalidCommandException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructor
	public InvalidCommandException() {
		super("InvalidCommandException");
	}

	public InvalidCommandException(String message) {
		super("'" + message + "' is not a valid command.");
	}
}
