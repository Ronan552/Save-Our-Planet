package saveourplanet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * methods relating to functionality of the game
 * @author nsweeney
 *
 */
public class Functionality {
	
	/**
	 * returns lineNum from file as a string.
	 * @param fileName
	 * @return
	 */
	public static String readFile(String fileName, int lineNum) {
		String line = null;
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);

			for(int loop=1; loop<=lineNum; loop++) {
				
				if(loop==lineNum) {
					line=br.readLine();
				}else {
					br.readLine();
				}
			}
			
			br.close();
			fr.close();
			
			
		}catch (FileNotFoundException e) {
			System.out.println("Please ensure file name is correct");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return line;
	}
	
	/**
	 * Imports Scanner
	 */
	private static Scanner sc;
	
	/**
	 * Displays options menu / home screen
	 * @return
	 */
	public static int displayOptionsMenu() {
		
		int choice;
		
		//The options menu that the player encounters when the game is started
		System.out.printf("%s\n", "Welcome to Save Our Planet, please select an option:");
		System.out.printf("%d. %s\n", 1, "Start Game");
		System.out.printf("%d. %s\n", 2, "Rules");
		System.out.printf("%d. %s\n", 3, "Quit");
		
		

		choice = Functionality.userIntResponse();
		
		return choice;
		
		
	}

	/**
	 * Returns user response as a String
	 * @return
	 */
	public static String userStringResponse() {
		
		
		String userResponse = null;
		sc = new Scanner(System.in);
		userResponse = sc.nextLine();

		return userResponse;
	}
	
	/**
	 * returns user response as an Int
	 * @return
	 */
	public static int userIntResponse() {
		int userInt, count;
		sc = new Scanner(System.in);
		
		userInt=0; count=0;  
		
		do {
			count=0;
		try {
			userInt = sc.nextInt();
			
		}catch (InputMismatchException e) {
			System.out.println("Please enter a number");
			count++;
			sc.nextLine();
		}
		}while(count!=0);

		return userInt;
	}
		
	/**
	 * Recognises user yes or no response. Ignores case an also accepts y or n.
	 * @return
	 */
	public static boolean yesOrNo() {
		boolean yes = false;
		int count = 1;
		System.out.println("(y/n?)");
		String userResponse = userStringResponse();
		do {
			if (userResponse.equalsIgnoreCase("y") || userResponse.equalsIgnoreCase("yes")) {
				yes = true;
				count = 0;
			} else if (userResponse.equalsIgnoreCase("n") || userResponse.equalsIgnoreCase("no")) {
				yes = false;
				count = 0;
			} else {
				if(count!=0) {
					System.out.println("Please input y or n");
					count++;
					userResponse = userStringResponse();
				}
			}
		} while (count != 0);
		return yes;
	}

	/**
	 * Use when prompting user to press enter. 
	 */
	public static void pressEnter() {
		
		System.out.println("Press Enter");
		sc = new Scanner(System.in);

		sc.nextLine();
		
	}
		
	/**
	 * Provides screen break between each player turn
	 */
	public static void nextPlayerScreenBreak() {
		System.out.println();
		System.out.println("***********************************************");
		System.out.println("******************NEXT*************************");
		System.out.println("*****************PLAYER************************");
		System.out.println("*****************PLEASE************************");
		System.out.println("***********************************************");
		System.out.println();
	}

	/**
	 * Provides screen break when all players have taken their turn
	 */
	public static void endOfRoundScreenBreak() {
		System.out.println();
		System.out.println("***********************************************");
		System.out.println("******************END**************************");
		System.out.println("******************OF***************************");
		System.out.println("***************** ROUND************************");
		System.out.println("***********************************************");
		System.out.println();
		
	}
	
	public static void nextRoundOrQuitGame() {
		
		int count = 1;
		
		
		
		do {
			System.out.println("Enter 'y' to start next round....");
			System.out.println("Otherwise press q to exit the game");
			String userResponse = userStringResponse();
			if (userResponse.equalsIgnoreCase("y") || userResponse.equalsIgnoreCase("yes")) {
				count = 0;
			} else if (userResponse.equalsIgnoreCase("q")||userResponse.equalsIgnoreCase("quit")){
				System.out.println("Are you sure you want to quit game?");
				System.out.println("Progress will not be saved......");

				if(yesOrNo()) {
					System.out.println("You have quit the game!");
					System.exit(1);
				} else {
					count++;
				}
				
			}else if(count!=0) {
					System.out.println("Please input y or n");
					count++;
				}
			
		} while (count != 0);
	}
	


		
		
	
	
}

