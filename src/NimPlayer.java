
/*
	COMP90041 Programming and Software Development
	Semester 1, 2018
	Author's Name: Yuxin Guo
	User Name: yuxing4
	ID: 875698
	ProjectC, 16/05/2018
	This class represents the player who plays Nim.
*/
import java.io.Serializable;
import java.util.Scanner;

public abstract class NimPlayer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final double RATIO_MULTIPLICAND_100 = 100.0;
	private static final double INT_TO_DOUBLE = 1.0;
	private static final int MAX_NUMBER_OF_GAME_PLAYED = 99;
	private static final int MIN_DOUBLE_DIGIT = 10;
	private static final int ZERO_INT = 0;

	private String username;
	private String givenName;
	private String familyName;
	private int numberOfGamesPlayed;
	private int numberOfGamesWon;

	// Constructor and initialization
	NimPlayer(String username, String familyName, String givenName) {
		this.username = username;
		this.givenName = givenName;
		this.familyName = familyName;
		this.numberOfGamesPlayed = 0;
		this.numberOfGamesWon = 0;
	}

	public String getUsername() {
		return username;
	}

	// Accessor
	public String getGivenName() {
		return givenName;
	}

	// Mutator
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public int getNumberOfGamesPlayed() {
		return numberOfGamesPlayed;
	}

	public void setNumberOfGamesPlayed(int numberOfGamesPlayed) {
		this.numberOfGamesPlayed = numberOfGamesPlayed;
	}

	public int getNumberOfGamesWon() {
		return numberOfGamesWon;
	}

	public void setNumberOfGamesWon(int numberOfGamesWon) {
		this.numberOfGamesWon = numberOfGamesWon;
	}

	public abstract int removeStone(Scanner keyboard, int maxValidNumberToMove, int currentStoneCount);

	// method to display the information of player
	@Override
	public String toString() {
		return getUsername() + "," + getGivenName() + "," + getFamilyName() + "," + getNumberOfGamesPlayed() + " games,"
				+ getNumberOfGamesWon() + " wins";
	}

	// method to get full name
	public String getFullName() {
		return getGivenName() + " " + getFamilyName();
	}

	// method to edit full name
	public void editFullName(String familyName, String givenName) {
		this.familyName = familyName;
		this.givenName = givenName;
	}

	// method to reset statistics
	public void resetStatistics() {
		this.numberOfGamesPlayed = 0;
		this.numberOfGamesWon = 0;
	}

	// method to update the number of games played
	public void addNumberOfGamesPlayed() {
		++this.numberOfGamesPlayed;
	}

	// method to update the number of games won
	public void addNumberOfGamesWon() {
		++this.numberOfGamesWon;
	}

	// method to print winning ratio
	private void printWinningRatios() {
		int ratios = (int) Math.round(getWinningRatios() * RATIO_MULTIPLICAND_100);
		System.out.printf("%-5s", ratios + "%");
		System.out.print("| ");
	}

	// method to calculate winning ratio
	public double getWinningRatios() {
		if (getNumberOfGamesPlayed() == ZERO_INT)
			return 0.0;
		else {
			return getNumberOfGamesWon() * INT_TO_DOUBLE / getNumberOfGamesPlayed();
		}
	}

	// method to print number of games played
	private void printNumberOfGamesPlayed() {
		if (getNumberOfGamesPlayed() >= MIN_DOUBLE_DIGIT
				&& getNumberOfGamesPlayed() <= MAX_NUMBER_OF_GAME_PLAYED)
			System.out.print(getNumberOfGamesPlayed() + " games | ");
		else
			System.out.print("0" + getNumberOfGamesPlayed() + " games | ");
	}

	// method to display the ranking information of player
	public void printRankingsStatistics() {
		printWinningRatios();
		printNumberOfGamesPlayed();
		System.out.print(getFullName() + "\n");
	}
}
