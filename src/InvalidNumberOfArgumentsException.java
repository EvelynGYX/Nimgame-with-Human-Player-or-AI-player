/*
	COMP90041 Programming and Software Development
	Semester 1, 2018
	Author's Name: Yuxin Guo
	User Name: yuxing4
	ID: 875698
	ProjectC, 16/05/2018
	This class represents the InvalidNumberOfArgumentsException.
*/
public class InvalidNumberOfArgumentsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numberOfArguments;

	// Constructor and initialization
	public InvalidNumberOfArgumentsException(int numberOfArguments) {
		super("Incorrect number of arguments supplied to command.");
		this.numberOfArguments = numberOfArguments;
	}

	public InvalidNumberOfArgumentsException() {
		super("Incorrect number of arguments supplied to command.");
	}

	public InvalidNumberOfArgumentsException(String message) {
		super(message);
	}

	public int getNumberOfArguments() {
		return numberOfArguments;
	}
}
