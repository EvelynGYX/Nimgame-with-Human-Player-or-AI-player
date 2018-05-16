/*
	COMP90041 Programming and Software Development
	Semester 1, 2018
	Author's Name: Yuxin Guo
	User Name: yuxing4
	ID: 875698
	ProjectC, 16/05/2018
	This class represents the NimAIPlayer.
*/
import java.util.Random;
import java.util.Scanner;

/*
	NimAIPlayer.java
	
	This class is provided as a skeleton code for the tasks of 
	Sections 2.4, 2.5 and 2.6 in Project C. Add code (do NOT delete any) to it
	to finish the tasks. 
*/

public class NimAIPlayer extends NimPlayer implements Testable {
	// you may further extend a class or implement an interface
	// to accomplish the tasks.
	/*
	 * public NimAIPlayer() { super(); }
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int MIN_NUMBER_OF_STONE_AVALIBALE = 1;
	private static final int NUMBER_OF_STONE_ZERO = 0;
	private static final int Int_1 = 1;
	
	// Constructor and initialization
	NimAIPlayer(String username, String familyName, String givenName) {
		super(username, familyName, givenName);
	}

	// return the number of stones removed by the player
	public int removeStone(Scanner keyboard, int maxValidNumberToMove, int currentStoneCount) {
		int numberToRemove = 0;
		Random rand = new Random();
		System.out.println(getGivenName() + "'s turn - remove how many?");
		numberToRemove = (currentStoneCount - 1) % (maxValidNumberToMove + 1);	//the formula
		if (currentStoneCount == MIN_NUMBER_OF_STONE_AVALIBALE)
			numberToRemove = MIN_NUMBER_OF_STONE_AVALIBALE;
		else if (numberToRemove == NUMBER_OF_STONE_ZERO)
			numberToRemove = rand.nextInt(maxValidNumberToMove - Int_1) + Int_1;			//random selection
		return numberToRemove;
	}

	public String advancedMove(boolean[] available, String lastMove) {
		// the implementation of the victory
		// guaranteed strategy designed by you
		String move = "";

		return move;
	}
}
