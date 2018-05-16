/*
	COMP90041 Programming and Software Development
	Semester 1, 2018
	Author's Name: Yuxin Guo
	User Name: yuxing4
	ID: 875698
	ProjectC, 16/05/2018
	This class represents the Nim system.
*/
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Nimsys {
	private static final int NUMBER_OF_PLAYERS = 100;
	private static final int NONE_INDEX = -1;
	private static final int INDEX_TYPE_OF_COMMAND = 0;
	private static final int INDEX_DETAIL_OF_COMMAND = 1;
	private static final int INDEX_USERNAME = 0;
	private static final int INDEX_FAMILY_NAME = 1;
	private static final int INDEX_GIVEN_NAME = 2;
	private static final int MAX_INDEX_OF_RANKINGS = 9;
	private static final int INDEX_FIRST_USERNAME = 0;
	private static final int INDEX_SECOND_USERNAME = 1;
	private static final int INDEX_INITIALSTONES = 0;
	private static final int INDEX_UPPERBOUND = 1;
	private static final int INDEX_USERNAME1 = 2;
	private static final int INDEX_USERNAME2 = 3;
	private static final int NUMBER_OF_ARGUMENTS_4 = 4;
	private static final int NUMBER_OF_ARGUMENTS_3 = 3;
	private static final int NUMBER_OF_ARGUMENTS_0 = 0;

	private NimPlayer[] playerCollection;
	private int currentIndex;
	private NimGame game;

	// Constructor and initialization
	Nimsys() {
		this.playerCollection = new NimPlayer[NUMBER_OF_PLAYERS];
		this.currentIndex = NONE_INDEX;
		this.game = null;
	}

	public NimPlayer[] getPlayerCollection() {
		return playerCollection;
	}

	public void setPlayerCollection(NimPlayer[] playerCollection) {
		this.playerCollection = playerCollection;
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	public NimGame getGame() {
		return game;
	}

	public void setGame(NimGame game) {
		this.game = game;
	}

	// the main method where the program starts
	public static void main(String[] args) {
		Nimsys system = new Nimsys();
		system.input();
		boolean input = true;
		String syntax;
		Scanner keyboard = new Scanner(System.in);

		System.out.print("Welcome to Nim\n");

		while (input) {
			System.out.print("\n$");
			syntax = keyboard.nextLine();
			try {
				system.processNimsys(syntax, keyboard);
			} catch (InvalidCommandException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	// method to analyze the syntax and process the data according to the specific
	// command
	private void processNimsys(String syntax, Scanner keyboard) throws InvalidCommandException {
		String[] command = new String[2];

		if (syntax.contains(" ")) {
			command = syntax.split(" ");
		} else {
			command[INDEX_TYPE_OF_COMMAND] = syntax;
			command[INDEX_DETAIL_OF_COMMAND] = null;
		}
		try {
			if (command[INDEX_TYPE_OF_COMMAND].equals("addplayer"))
				addPlayer(command[INDEX_DETAIL_OF_COMMAND]);
			else if (command[INDEX_TYPE_OF_COMMAND].equals("addaiplayer"))
				addAIPayer(command[INDEX_DETAIL_OF_COMMAND]);
			else if (command[INDEX_TYPE_OF_COMMAND].equals("removeplayer"))
				removePlayer(command[INDEX_DETAIL_OF_COMMAND], keyboard);
			else if (command[INDEX_TYPE_OF_COMMAND].equals("editplayer"))
				editPlayer(command[INDEX_DETAIL_OF_COMMAND]);
			else if (command[INDEX_TYPE_OF_COMMAND].equals("resetstats"))
				resetStats(command[INDEX_DETAIL_OF_COMMAND], keyboard);
			else if (command[INDEX_TYPE_OF_COMMAND].equals("displayplayer"))
				displayPlayer(command[INDEX_DETAIL_OF_COMMAND]);
			else if (command[INDEX_TYPE_OF_COMMAND].equals("rankings"))
				rankings(command[INDEX_DETAIL_OF_COMMAND]);
			else if (command[INDEX_TYPE_OF_COMMAND].equals("startgame"))
				startGame(command[INDEX_DETAIL_OF_COMMAND], keyboard);
			else if (command[INDEX_TYPE_OF_COMMAND].equals("exit")) {
				output();
				System.out.println();
				System.exit(0);
			} else {
				throw new InvalidCommandException(command[INDEX_TYPE_OF_COMMAND]);
			}
		} catch (InvalidNumberOfArgumentsException e) {
			System.out.println(e.getMessage());
		}
	}

	// input statistics file
	private void input() {
		ObjectInputStream in = null;
		int index = NONE_INDEX;
		try {
			File f = new File("players.dat");
			if (f.exists()) {
				in = new ObjectInputStream(new FileInputStream(f));
				while (true) {
					try {
						NimPlayer player = (NimPlayer) in.readObject();
						playerCollection[++index] = player;
					} catch (EOFException e) {
						break;
					}
				}
			}
		} catch (FileNotFoundException e) {
			//System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			currentIndex = index;
			if (in != null)
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	// output statistics file
	private void output() {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream("players.dat"));
			for (int i = 0; i <= currentIndex; i++) {
				out.writeObject(playerCollection[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	// method to get player's index according to username
	private int getPlayerIndex(String username) {
		String name;
		for (int i = 0; i <= currentIndex; i++) {
			name = playerCollection[i].getUsername();
			if (name.equals(username))
				return i;
		}
		return NONE_INDEX;
	}

	// method to add players
	private void addPlayer(String namesToAdd) throws InvalidNumberOfArgumentsException {
		if (namesToAdd != null) {
			String[] names = namesToAdd.split(",");
			if (names.length == NUMBER_OF_ARGUMENTS_3) {
				if (getPlayerIndex(names[INDEX_USERNAME]) == NONE_INDEX) {
					playerCollection[++currentIndex] = new NimHumanPlayer(names[INDEX_USERNAME],
							names[INDEX_FAMILY_NAME], names[INDEX_GIVEN_NAME]);
				} else
					System.out.println("The player already exists.");
			} else {
				throw new InvalidNumberOfArgumentsException(names.length);
			}
		} else
			throw new InvalidNumberOfArgumentsException(NUMBER_OF_ARGUMENTS_0);
	}

	// method to add AI players
	private void addAIPayer(String namesToAdd) throws InvalidNumberOfArgumentsException {
		if (namesToAdd != null) {
			String[] names = namesToAdd.split(",");
			if (names.length == NUMBER_OF_ARGUMENTS_3) {
				if (getPlayerIndex(names[INDEX_USERNAME]) == NONE_INDEX) {
					playerCollection[++currentIndex] = new NimAIPlayer(names[INDEX_USERNAME], names[INDEX_FAMILY_NAME],
							names[INDEX_GIVEN_NAME]);
				} else
					System.out.println("The player already exists.");
			} else {
				throw new InvalidNumberOfArgumentsException(names.length);
			}
		} else
			throw new InvalidNumberOfArgumentsException(NUMBER_OF_ARGUMENTS_0);

	}

	// method to remove player
	private void removePlayer(String nameToRemove, Scanner keyboard) {
		if ((nameToRemove) == null) {
			System.out.print("Are you sure you want to remove all players? (y/n)\n");
			if (keyboard.nextLine().equals("y"))
				removePlayerInCollection();
		} else {
			int index = getPlayerIndex(nameToRemove);
			if (index == NONE_INDEX)
				System.out.println("The player does not exist.");
			else
				removePlayerInCollection(index);
		}
	}

	// method to remove the given player
	private void removePlayerInCollection(int index) {
		for (int i = index; i <= currentIndex - 1; i++) {
			playerCollection[i] = playerCollection[i + 1];
		}
		playerCollection[currentIndex] = null;
		--currentIndex;
	}

	// Overloading, method to remove all players
	private void removePlayerInCollection() {
		for (int i = 0; i <= currentIndex; i++) {
			playerCollection[i] = null;
		}
		currentIndex = NONE_INDEX;
	}

	// method to edit player
	private void editPlayer(String nameToEdit) throws InvalidNumberOfArgumentsException {
		if (nameToEdit != null) {
			String[] names = nameToEdit.split(",");
			if (names.length == NUMBER_OF_ARGUMENTS_3) {
				int index = getPlayerIndex(names[INDEX_USERNAME]);
				if (index != NONE_INDEX)
					playerCollection[index].editFullName(names[INDEX_FAMILY_NAME], names[INDEX_GIVEN_NAME]);
				else
					System.out.println("The player does not exist.");
			} else {
				throw new InvalidNumberOfArgumentsException(names.length);
			}
		} else
			throw new InvalidNumberOfArgumentsException(NUMBER_OF_ARGUMENTS_0);
	}

	// method to reset statistics
	private void resetStats(String nameToResetStats, Scanner keyboard) {
		if ((nameToResetStats) == null) {
			System.out.print("Are you sure you want to reset all player statistics? (y/n)\n");
			if (keyboard.nextLine().equals("y"))
				resetStatsAll();
		} else {
			int index = getPlayerIndex(nameToResetStats);
			if (index == NONE_INDEX)
				System.out.println("The player does not exist.");
			else
				playerCollection[index].resetStatistics();
		}
	}

	// method to reset statistics of all players
	private void resetStatsAll() {
		for (int i = 0; i <= currentIndex; i++) {
			playerCollection[i].resetStatistics();
		}
	}

	// method to display players
	private void displayPlayer(String nameToDisplay) {
		if ((nameToDisplay) == null)
			displayAll();
		else {
			int index = getPlayerIndex(nameToDisplay);
			if (index == NONE_INDEX)
				System.out.println("The player does not exist.");
			else
				System.out.println(playerCollection[index].toString());
		}
	}

	// display information of all players
	private void displayAll() {
		String username;
		for (int i = 0; i <= currentIndex; i++) {
			username = sortPlayerByUsername()[i];
			NimPlayer player = playerCollection[getPlayerIndex(username)];
			System.out.println(player.toString());
		}
	}

	// method to sort the collection of the usernames of the players for display
	private String[] sortPlayerByUsername() {
		int size = currentIndex + 1;
		String[] sortedUsername = new String[size];
		for (int i = 0; i <= currentIndex; i++) {
			sortedUsername[i] = playerCollection[i].getUsername();
		}
		Arrays.sort(sortedUsername);
		return sortedUsername;
	}

	// method to rank the players
	private void rankings(String order) {
		if (order == null)
			rankingsDesc();
		else if (order.equals("desc"))
			rankingsDesc();
		else if (order.equals("asc"))
			rankingsAsc();
	}

	// method to print rankings
	private void printRankings(NimPlayer[] temPlayerCollection) {
		if (currentIndex < MAX_INDEX_OF_RANKINGS) {
			for (int i = 0; i <= currentIndex; i++) {
				temPlayerCollection[i].printRankingsStatistics();
			}
		} else {
			for (int i = 0; i <= MAX_INDEX_OF_RANKINGS; i++) {
				temPlayerCollection[i].printRankingsStatistics();
			}
		}
	}

	// Overloading, method to sort 2 players by username and determine the order
	// for ranking
	private boolean sortPlayerByUsername(NimPlayer player1, NimPlayer player2) {
		String[] sortedUsername = new String[2];
		sortedUsername[INDEX_FIRST_USERNAME] = player1.getUsername();
		sortedUsername[INDEX_SECOND_USERNAME] = player2.getUsername();
		Arrays.sort(sortedUsername);
		if (sortedUsername[INDEX_FIRST_USERNAME].equals(player1.getUsername()))
			return true;
		else
			return false;
	}

	// method using selection sort to rank by desc and print rankings by desc
	private void rankingsDesc() {
		int size = currentIndex + 1;
		int max;
		NimPlayer[] temPlayerCollection = new NimPlayer[size];
		NimPlayer temPlayer = null;
		for (int i = 0; i <= currentIndex; i++) {
			temPlayerCollection[i] = playerCollection[i];
		}
		for (int i = 0; i <= currentIndex - 1; i++) {
			max = i;
			for (int j = i + 1; j <= currentIndex; j++) {
				if (temPlayerCollection[j].getWinningRatios() > temPlayerCollection[max].getWinningRatios())
					max = j;
				else if (temPlayerCollection[max].getWinningRatios() == temPlayerCollection[j].getWinningRatios()) {
					if (!sortPlayerByUsername(temPlayerCollection[max], temPlayerCollection[j]))
						max = j;
				}
			}
			temPlayer = temPlayerCollection[i];
			temPlayerCollection[i] = temPlayerCollection[max];
			temPlayerCollection[max] = temPlayer;
		}
		printRankings(temPlayerCollection);
	}

	// method using selection sort to rank by asc and print rankings by asc
	private void rankingsAsc() {
		int size = currentIndex + 1;
		int min;
		NimPlayer[] temPlayerCollection = new NimPlayer[size];
		NimPlayer temPlayer = null;
		for (int i = 0; i <= currentIndex; i++) {
			temPlayerCollection[i] = playerCollection[i];
		}
		for (int i = 0; i <= currentIndex - 1; i++) {
			min = i;
			for (int j = i + 1; j <= currentIndex; j++) {
				if (temPlayerCollection[j].getWinningRatios() < temPlayerCollection[min].getWinningRatios())
					min = j;
				else if (temPlayerCollection[j].getWinningRatios() == temPlayerCollection[min].getWinningRatios()) {
					if (sortPlayerByUsername(temPlayerCollection[j], temPlayerCollection[min]))
						min = j;
				}
			}
			temPlayer = temPlayerCollection[i];
			temPlayerCollection[i] = temPlayerCollection[min];
			temPlayerCollection[min] = temPlayer;
		}
		printRankings(temPlayerCollection);
	}

	// method to start game
	private void startGame(String initialfigures, Scanner keyboard) throws InvalidNumberOfArgumentsException {
		if (initialfigures != null) {
			String[] figures = initialfigures.split(",");
			if (figures.length == NUMBER_OF_ARGUMENTS_4) {
				if (getPlayerIndex(figures[INDEX_USERNAME1]) == NONE_INDEX
						|| getPlayerIndex(figures[INDEX_USERNAME2]) == NONE_INDEX)
					System.out.println("One of the players does not exist.");
				else {
					int initialStones = Integer.parseInt(figures[INDEX_INITIALSTONES]);
					int upperBound = Integer.parseInt(figures[INDEX_UPPERBOUND]);
					NimPlayer player1 = playerCollection[getPlayerIndex(figures[INDEX_USERNAME1])];
					NimPlayer player2 = playerCollection[getPlayerIndex(figures[INDEX_USERNAME2])];
					game = new NimGame(initialStones, upperBound, player1, player2);
					game.startNim(keyboard);
					if (player1 instanceof NimHumanPlayer || player2 instanceof NimHumanPlayer)
						keyboard.nextLine();
					game = null;
				}
			} else {
				throw new InvalidNumberOfArgumentsException(figures.length);
			}
		} else
			throw new InvalidNumberOfArgumentsException(NUMBER_OF_ARGUMENTS_0);
	}
}
