/*
	COMP90041 Programming and Software Development
	Semester 1, 2018
	Author's Name: Yuxin Guo
	User Name: yuxing4
	ID: 875698
	ProjectC, 16/05/2018
	This class represents the InvalidMoveException.
*/
public class InvalidMoveException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numberOfStonesToMove;

	// Constructor and initialization
	public InvalidMoveException(int numberOfStonesToMove) {
		super("InvalidMoveException");
		this.numberOfStonesToMove = numberOfStonesToMove;
	}

	public InvalidMoveException() {
		super("InvalidMoveException");
	}

	public InvalidMoveException(String message) {
		super(message);
	}

	public int getNumberOfArguments() {
		return numberOfStonesToMove;
	}
}
