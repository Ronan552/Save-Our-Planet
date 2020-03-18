package saveourplanet;


import java.util.ArrayList;

/**
 * Class containing the main method and playerTurn method from which the game runs.
 * @author nsweeney
 *
 */
public class PlaySaveOurPlanet {
		
	public static void main(String[] args) {
		
		int switchNum;
		
		do{

		switchNum = Functionality.displayOptionsMenu();

		switch(switchNum){

		case 1 : BoardSetUp.startGame(PlayerUtil.PLAYER_LIST);
		break;
		case 2 : BoardSetUp.gameRules("SaveOurPlanetRules.txt");
		Functionality.pressEnter();
		switchNum=0;
		break;
		case 3 :System.out.println("You have quit the game!");
		System.exit(1);
		break; 
		default : 
		System.out.println("Please enter a number between 1-4");
		Functionality.pressEnter();
		}

		}while(switchNum<1 || switchNum>3);
		
		BoardSetUp.populateFieldSquares("FieldSquares.csv", 2);
		
		BoardSetUp.BOARD = BoardSetUp.populateBoardSquares(BoardSetUp.FIELD_SQUARE, BoardSetUp.START_SQUARE, BoardSetUp.CLIMATE_FACTS_SQUARE);
		
		playerTurn(PlayerUtil.PLAYER_LIST, BoardSetUp.FIELD_SQUARE, BoardSetUp.BOARD);
	}

	/**
	 * series of if/if-else statements within do-while loop determining options available to each player on their turn
	 */
	public static void playerTurn(ArrayList<Player> players, ArrayList<FieldSquare> fieldSquares, ArrayList<Square> board) {
		
			double playerBalance=0;

		do {
		
			for (Player p : players) {
					
				playerBalance=p.getEcoPoints();
				
				// player position on board determined. Dice roll incorporated here also
				GamePlayUtil.playerLands(p, board);

				// determines if square is avail to buy
				if (GamePlayUtil.isPropertyAvailable(p, fieldSquares)) {

					if (GamePlayUtil.doesPlayerOwnField(p, fieldSquares)) {
						System.out.println("You can either buy the current square or develop an existing property.");
						System.out.println("Press y to buy or n to develop existing property");
						if(Functionality.yesOrNo()) {
							// player has option to buy
							GamePlayUtil.buyProperty(p, fieldSquares);
						}else {
							// option to either buy current sq or develop field if player owns a field
							GamePlayUtil.propertiesEligibleForDev(p,fieldSquares, GamePlayUtil.ownedFieldsList(p, fieldSquares));	
						}
					} else {
						// player has option to buy
						GamePlayUtil.buyProperty(p, fieldSquares);
					}

					// determines if player owns square landed on
				} else if (GamePlayUtil.doesPlayerOwnProperty(p, fieldSquares)) {

					if (GamePlayUtil.doesPlayerOwnField(p, fieldSquares)) {
						System.out.println("Would you like to develop a property?");
						if (Functionality.yesOrNo()) {
							GamePlayUtil.propertiesEligibleForDev(p,fieldSquares, GamePlayUtil.ownedFieldsList(p, fieldSquares));
						} else {

						}
					}

					// determines if rent is required
				} else if (GamePlayUtil.IsRentRequired(p, fieldSquares)) {

					// method to deduct rent and transfer to sq owner
					GamePlayUtil.payRent(p, players, fieldSquares, board);
					
					if (GamePlayUtil.doesPlayerOwnField(p, fieldSquares)) {
						System.out.println("Would you like to develop a property?");
						if (Functionality.yesOrNo()) {
							GamePlayUtil.propertiesEligibleForDev(p,fieldSquares, GamePlayUtil.ownedFieldsList(p, fieldSquares));
						} else {

						}
					}

				} else if(p.getSquareId()==7) {
					ClimateFacts.displayClimateFacts();
					Functionality.pressEnter();
				
				}//else if(p.getSquareId()==1) {
					
					//System.out.println("You landed on square 1");
					//System.out.println("The UN have found you tampering with your car to improve emissions readings");
					//if(p.getEcoPoints()<=50) {
					//	System.out.printf("%s, you cannot afford the fine. Cheating the system has cost you, you are now bankrupt");
					//	p.setEcoPoints(0);
					//	GamePlayUtil.gameOver(p, players, fieldSquares, board);
					//}else {
					//	System.out.println("You have been fined 50 Eco points.");
					//	p.setEcoPoints(p.getEcoPoints()-50);
					//  System.out.printf("Your new balance is %.0f,\n",p.getEcoPoints());
					//}
				//}
				
				if(p.getPlayerId()==players.size()) {
					Functionality.endOfRoundScreenBreak();
				} else {
					Functionality.nextPlayerScreenBreak();	
				}
				
			}
				Functionality.pressEnter();
				System.out.println("Game state after round");
				PlayerUtil.displayAllPlayerInfo(players);
				GamePlayUtil.squareOwners(board);
				Functionality.nextRoundOrQuitGame();
			

		}while(playerBalance>=0);
	
	
	}
	
public static void mainMenuOrQuitGame(ArrayList<Player>players, ArrayList<FieldSquare> fieldsquares, ArrayList<Square>board) {
		
		int count = 1;
		
		do {
			System.out.println("Enter 'y' to return to main menu....");
			System.out.println("Otherwise press q to exit the game");
			String userResponse = Functionality.userStringResponse();
			if (userResponse.equalsIgnoreCase("y") || userResponse.equalsIgnoreCase("yes")) {
				players.removeAll(players);
				fieldsquares.removeAll(fieldsquares);
				board.removeAll(board);
				main(null);
			} else if (userResponse.equalsIgnoreCase("q")||userResponse.equalsIgnoreCase("quit")){
				System.out.println("Are you sure you want to quit game?");

				if(Functionality.yesOrNo()) {
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

		

