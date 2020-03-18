package saveourplanet;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Methods relating to activities of the boardGame
 * @author nsweeney
 */
public class BoardSetUp {
	
	/**
	 * Startsquare object instantiated as a static object to allow access across all classes
	 */
	public static StartSquare START_SQUARE = new StartSquare();
	
	/**
	 * ClimateFacts object instantiated as a static object to allow access across all classes
	 */ 
	public static ClimateFacts CLIMATE_FACTS_SQUARE = new ClimateFacts("Climate Facts", 7);
	
	/**
	 * ArrayList to contain FieldSquares
	 */
	public static ArrayList<FieldSquare> FIELD_SQUARE = new ArrayList<FieldSquare>();
	
	/**
	 * Array containing all squares
	 */
	public static ArrayList<Square> BOARD = new ArrayList<Square>();


	/**
	 * A method which will print out the rules of the game to screen
	 */
	public static void gameRules(String fileName) {
		
		String line = Functionality.readFile(fileName, 1); 
		int count = 1;
		do {
				line = Functionality.readFile(fileName, count);
				System.out.println(line);
				count++;
				line = Functionality.readFile(fileName, count);
		}while(line!=null);
	}

	/**
	 * Populates all squares (1-12) to squares array
	 */
	public static ArrayList<Square> populateBoardSquares(ArrayList<FieldSquare> fieldSquares, Square startSquare, Square blankSquare) {
		ArrayList<Square> boardSquares = new ArrayList<Square>();
		for(FieldSquare fs : fieldSquares) {
			boardSquares.add(fs);
		}
		
		boardSquares.add(startSquare);
		boardSquares.add(blankSquare);
		
		Collections.sort(boardSquares);
		return boardSquares;
				
	}

	/**
	 * Reads .csv file containing FieldSquare details and populates static arrayList
	 * with FieldSquare objects
	 * 
	 * @param fileName
	 * @param startAtLine
	 */
	public static void populateFieldSquares(String fileName, int startAtLine) {

		String line;
		int count = startAtLine;
		do {
			line = Functionality.readFile(fileName, count);
			if (line != null) {

				String lineParse[] = line.split(",");

					FieldSquare fs = new FieldSquare(lineParse[0], Integer.parseInt(lineParse[1]),
							Fields.valueOf(lineParse[2]),
							Double.parseDouble(lineParse[3]),
							Double.parseDouble(lineParse[4]),
							Double.parseDouble(lineParse[5]),
							Double.parseDouble(lineParse[6]),
							lineParse[7],
							lineParse[8]);
						
				BoardSetUp.FIELD_SQUARE.add(fs);
			}
			count++;
		} while (line != null);

	}

	/**
	 * if statement asking player whether to start game. If yes, methods are executed to populate player arrayList.
	 * if no returns to home screen.
	 * @param players
	 */
	public static void startGame(ArrayList<Player> players) {
		System.out.println("Would you like to start a game?");
		if (Functionality.yesOrNo()) {
			PlayerUtil.generatePlayers(players);
			PlayerUtil.assignPlayerNames(players);
		} else {
			PlaySaveOurPlanet.main(null);
		}

	}

	
}



