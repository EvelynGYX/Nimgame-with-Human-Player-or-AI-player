
/*
	COMP90041 Programming and Software Development
	Semester 1, 2018
	Author's Name: Yuxin Guo
	User Name: yuxing4
	ID: 875698
	ProjectC, 16/05/2018
	This class represents the NimHumanPlayer.
*/
import java.util.InputMismatchException;
import java.util.Scanner;

public class NimHumanPlayer extends NimPlayer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructor and initialization
	NimHumanPlayer(String username, String familyName, String givenName) {
		super(username, familyName, givenName);
	}

	// return the number of stones removed by the player
	public int removeStone(Scanner keyboard, int maxValidNumberToMove, int currentStoneCount) {
		boolean done = false;
		int numberToRemove = 0;
		while (!done) {
			try {
				System.out.println(getGivenName() + "'s turn - remove how many?");
				numberToRemove = keyboard.nextInt();
				done = true;
			} catch (InputMismatchException e) {
				keyboard.nextLine();
				System.out.println(
						"\nInvalid move. You must remove between 1 and " + maxValidNumberToMove + " stones.\n");
			}
		}
		return numberToRemove;
	}
}
