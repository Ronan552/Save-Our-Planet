package saveourplanet;

import java.util.ArrayList;

/**
 * Methods containing actions relating to the player
 * 
 * @author rwhite, nsweeney, jpatterson and dgillespie
 *
 */

public class PlayerUtil {

	/**
	 * ArrayList to contain players
	 */
	public static ArrayList<Player> PLAYER_LIST = new ArrayList<Player>();

	/**
	 * Generates arrayList of required number of players (between 2-4 inclusive)
	 * 
	 * @param players
	 * @return
	 */
	public static void generatePlayers(ArrayList<Player> players) {

		int numberOfPlayers;
		int count = 0;

		do {
			System.out.println("Choose number of players....(min2 - max4)");
			numberOfPlayers = Functionality.userIntResponse();

			if (numberOfPlayers >= 2 && numberOfPlayers <= 4) {
				for (int loop = 1; loop <= numberOfPlayers; loop++) {
					Player p = new Player();
					p.setName("Player" + loop);
					players.add(p);
				}
				count++;

			} else if (numberOfPlayers < 2) {
				System.out.println("The number inputted is below the minimum of 2 player");
			} else if (numberOfPlayers > 4) {
				System.out.println("The number inputted is above the maximum of 4 players");
			} else {
				System.out.println("Please input a number between 2-4 inclusive");
			}

		} while (count == 0);

	}

	/**
	 * Assigns player names to the arrayList.
	 * 
	 * @param players
	 */
	public static void assignPlayerNames(ArrayList<Player> players) {
		String userName = null;
		int loop1 = 0;
		boolean error = false;
		for (Player p : players) {

			System.out.println("Please assign name to " + p.getName());

			do {
				error = false;
				try {
					userName = Functionality.userStringResponse();
					p.setName(userName);
				} catch (IllegalArgumentException e) {
					error = true;
					System.out.println(e.getMessage());
					System.out.println("Please choose an alternative name....");
				}
			} while (error);

			for (Player p2 : players) {

				loop1 = 0;
				do {
					if (p.getName().equalsIgnoreCase(p2.getName()) && p.getPlayerId() != p2.getPlayerId()) {

						System.out.println("The name " + p.getName() + " has already been assigned to another player.");

						System.out.println("Please choose a different name.");

						do {
							error = false;
							try {
								userName = Functionality.userStringResponse();
								p.setName(userName);
							} catch (IllegalArgumentException e) {
								error = true;
								System.out.println(e.getMessage());
								System.out.println("Please choose an alternative name....");
							}
						} while (error);

					} else {
						loop1++;
					}

				} while (loop1 == 0);
			}

		}
	}

	/**
	 * Uses enhanced for loop to display info on players in PLAYER ArrayList
	 * 
	 * @param players
	 */
	public static void displayAllPlayerInfo(ArrayList<Player> players) {
		for (Player p : players) {
			p.displayAll();
		}
	}
}
