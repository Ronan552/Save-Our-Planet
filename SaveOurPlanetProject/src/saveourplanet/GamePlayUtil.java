package saveourplanet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Methods involving actions relating to FieldSquares
 * 
 * @author user
 *
 */
public class GamePlayUtil {
	
	/**
	 * Determines if property landed on is available to buy
	 * 
	 * @param p
	 * @return
	 */
	public static boolean isPropertyAvailable(Player p, ArrayList<FieldSquare> fieldSquares) {
		boolean isAvailable = false;
		for (FieldSquare fs : fieldSquares) { 

			if (fs.getSquareId() == p.getSquareId()) {
				if (fs.getOwnerName().equalsIgnoreCase("For Sale")) {
					System.out.println(fs.getName() + " is available to buy.");
					isAvailable = true;
				}
			}
		}
		return isAvailable;
	}

	/**
	 * Determines if current player owns the property
	 * 
	 * @param p
	 * @return
	 */
	public static boolean doesPlayerOwnProperty(Player p, ArrayList<FieldSquare> fieldSquares) {
		boolean playerOwns = false;
		for (FieldSquare fs : fieldSquares) {
			if (fs.getSquareId() == p.getSquareId()) {
				if (fs.getOwnerName().equalsIgnoreCase(p.getName())) {
					System.out.println("You own " + fs.getName());
					playerOwns = true;
				}
			}

		}
		return playerOwns;
	}

	/**
	 * Enables player to buy property
	 * 
	 * @param p
	 */
	public static void buyProperty(Player p, ArrayList<FieldSquare> fieldSquares) {
		for (FieldSquare fs : fieldSquares) {
			if (fs.getSquareId() == p.getSquareId() && p.getEcoPoints() >= fs.getBuyPrice()) {
				propertyStatus(p, fieldSquares);
				System.out.printf("%s, your current eco points balance is %.0f. Would you like to buy %s for %.0f eco points?\n", p.getName(),
						p.getEcoPoints(), fs.getName(), fs.getBuyPrice());
				if (Functionality.yesOrNo()) {

					System.out.printf("%s, you have bought %s for %.0f eco points\n", p.getName(), fs.getName(), fs.getBuyPrice());

					p.setEcoPoints(p.getEcoPoints() - fs.getBuyPrice());

					fs.setOwnerName(p.getName());

					System.out.printf("Your new balance is %.0f eco points.\n", p.getEcoPoints());

				} else {
					System.out.println("You have decided not to buy");
				}

			} else if (fs.getSquareId() == p.getSquareId() && p.getEcoPoints() <= fs.getBuyPrice()) {
				System.out.printf("%s, you cannot afford %s.\n", p.getName(), fs.getName());
			}
		}
	}

	/**
	 * A method which will display all details about a particular square on the
	 * board
	 * 
	 * @param square
	 */
	public static void propertyStatus(Player p, ArrayList<FieldSquare> fieldSquares) {
		for (FieldSquare fs : fieldSquares) {
			if (fs.getSquareId() == p.getSquareId()) {
				fs.displayAll();
				System.out.println();
				System.out
						.println("You must own all properties in the " + fs.getField() + " field in order to develop.");
				System.out.print("Other properties in this field are: \n");
				for (FieldSquare fs2 : BoardSetUp.FIELD_SQUARE) {
					if (fs2.getField().equals(fs.getField()) && !fs2.getName().equals(fs.getName())) {
						if (fs2.getOwnerName().equalsIgnoreCase("For Sale")) {
							System.out.println("--" + fs2.getName() + " (currently not owned).");
						} else {
							System.out.println("--" + fs2.getName() + " is owned by " + fs2.getOwnerName());
						}

					}
				}
				System.out.print("\n");
				System.out.println("*********************************");
			}
		}
	}

	/**
	 * Determines if rent is due on the squareId in question
	 * 
	 * @param p
	 * @return
	 */
	public static boolean IsRentRequired(Player p, ArrayList<FieldSquare> fieldSquares) {
		boolean rentReq = false;

		for (FieldSquare fs : fieldSquares) {
			if (fs.getSquareId() == p.getSquareId()) {
				if (!fs.getOwnerName().equalsIgnoreCase(p.getName())
						&& !fs.getOwnerName().equalsIgnoreCase("For Sale")) {
					rentReq = true;
				}
			}
		}
		return rentReq;
	}

	/**
	 * Outputs list of all properties and owners
	 */
	public static void squareOwners(ArrayList<Square> board) {
		System.out.println("---------------------+-------------------------------+----------------+----------------+--------------------------|");
		System.out.printf("%-20s | %-30s| %-15s| %-15s| %-24s |\n", "SQUARE NUMBER", "SQUARE NAME", "OWNER NAME", "FIELD", "ECO CONTRIBUTION DUE");
		System.out.println("---------------------+-------------------------------+----------------+----------------+--------------------------|");
			for(Square sq : board) {
			if(sq.getClass().equals(FieldSquare.class)) {
				System.out.printf("%-20d | %-30s| %-15s| %-15s| %-24.0f |\n", sq.getSquareId(), sq.getName(), ((FieldSquare) sq).getOwnerName(),
						((FieldSquare) sq).getField(), rentDue((FieldSquare)sq));
				System.out.println("---------------------+-------------------------------+----------------+----------------+--------------------------|");
			}else {
				System.out.printf("%-20d | %-30s| %-15s| %-15s| %-24s |\n", sq.getSquareId(), sq.getName(), "Not Applicable",
						"Not Applicable", "Not Applicable");
				System.out.println("---------------------+-------------------------------+----------------+----------------+--------------------------|");
			}
		}
	}

	/**
	 * Converts playerSq to fieldSq name and returns as String
	 * 
	 * @param p
	 * @return
	 */
	public static String getBoardSqName(Player p, ArrayList<Square> board) {
		String name = null;

		for (Square sq : board) {
			if (p.getSquareId() == sq.getSquareId()) {
				name = sq.getName();
			}
		}

		return name;
	}

	/**
	 * Determines if player owns entire field
	 * 
	 * @param p
	 * @return
	 */
	public static boolean doesPlayerOwnField(Player p, ArrayList<FieldSquare> fieldSquares) {
		boolean yes = false;
		ArrayList<FieldSquare> lifestyle = new ArrayList<FieldSquare>();
		ArrayList<FieldSquare> recycle = new ArrayList<FieldSquare>();
		ArrayList<FieldSquare> reduce = new ArrayList<FieldSquare>();
		ArrayList<FieldSquare> renewables = new ArrayList<FieldSquare>();
		ArrayList<FieldSquare> playerOwns = new ArrayList<FieldSquare>();

		// add sqaures to arrays of their field type
		for (FieldSquare fs : fieldSquares) {
			if (fs.getField().equals(Fields.LIFESTYLE)) {
				lifestyle.add(fs);
			} else if (fs.getField().equals(Fields.RECYCLE)) {
				recycle.add(fs);
			} else if (fs.getField().equals(Fields.REDUCE)) {
				reduce.add(fs);
			} else if (fs.getField().equals(Fields.RENEWABLES)) {
				renewables.add(fs);
			}
		}

		/*
		 * for each iteration, the loopArray is set equal to each Field type. /the
		 * enhanced for loop iterates through the loop array and if the owner for each
		 * property /is the equal to that of the playerId, the playerOwns array is added
		 * with one instance of an object /of that field type.
		 */
		ArrayList<FieldSquare> loopArray = new ArrayList<FieldSquare>();
		for (int loop = 0; loop < 4; loop++) {
			if (loop == 0) {
				loopArray = lifestyle;
			} else if (loop == 1) {
				loopArray = recycle;
			} else if (loop == 2) {
				loopArray = reduce;
			} else if (loop == 3) {
				loopArray = renewables;
			}

			int count = 0;
			for (FieldSquare fs : loopArray) {
				if (fs.getOwnerName().equalsIgnoreCase(p.getName())) {
					count++;
				}
				if (count == loopArray.size()) {
					playerOwns.add(fs);
					yes = true;
				}
			}
		}

		if (playerOwns.size() > 1) {
			System.out.println(
					"You own more than one complete field.");
		} else if (playerOwns.size() == 1) {
			System.out.println("You own " + playerOwns.get(0).getField() + " and can now develop properties on this field.");
		}

		return yes;
	}

	/**
	 * populates arrayList of all fields owned by player
	 * 
	 * @param p
	 * @return
	 */
	public static ArrayList<FieldSquare> ownedFieldsList(Player p, ArrayList<FieldSquare> fieldSquares) {

		ArrayList<FieldSquare> lifestyle = new ArrayList<FieldSquare>();
		ArrayList<FieldSquare> recycle = new ArrayList<FieldSquare>();
		ArrayList<FieldSquare> reduce = new ArrayList<FieldSquare>();
		ArrayList<FieldSquare> renewables = new ArrayList<FieldSquare>();
		ArrayList<FieldSquare> playerOwns = new ArrayList<FieldSquare>();

		for (FieldSquare fs : fieldSquares) {
			if (fs.getField().equals(Fields.LIFESTYLE)) {
				lifestyle.add(fs);
			} else if (fs.getField().equals(Fields.RECYCLE)) {
				recycle.add(fs);
			} else if (fs.getField().equals(Fields.REDUCE)) {
				reduce.add(fs);
			} else if (fs.getField().equals(Fields.RENEWABLES)) {
				renewables.add(fs);
			}
		}

		ArrayList<FieldSquare> loopArray = new ArrayList<FieldSquare>();
		for (int loop = 0; loop < 4; loop++) {
			if (loop == 0) {
				loopArray = lifestyle;
			} else if (loop == 1) {
				loopArray = recycle;
			} else if (loop == 2) {
				loopArray = reduce;
			} else if (loop == 3) {
				loopArray = renewables;
			}

			int count = 0;
			for (FieldSquare fs : loopArray) {
				if (fs.getOwnerName().equalsIgnoreCase(p.getName())) {
					count++;
				}
				if (count == loopArray.size()) {
					playerOwns.add(fs);
				}
			}

		}

		return playerOwns;
	}

	/**
	 * outputs list of properties eligible for development
	 * 
	 * @param p
	 */
	public static void propertiesEligibleForDev(Player p, ArrayList<FieldSquare> allProperties, ArrayList<FieldSquare> fieldsOwned){
		
		ArrayList<FieldSquare> options = new ArrayList<FieldSquare>();
		int dowhile;
		
		
		// populate the options array with properties eligible for development
		for(FieldSquare fs1 : fieldsOwned) {
			
			for (FieldSquare fs2 : allProperties) {
				if (fs1.getField().equals(fs2.getField())) {
					options.add(fs2);
				}
			}
		}
		
		Collections.sort(options);

		
		do {
			dowhile=0;
		
		//print table to console of eligible properties to develop
			
		System.out.println(p.getName() + ", properties can have a maximum of 3 minor developments.");
		System.out.println("Once a property has 3 minor developments, it is eligible for major development.");
		System.out.println("Only 1 major development is permissible per property");
		System.out.println("+------------+-----------------------------+-----------------+-------------------------+----------------------+-----------------------+-----------------------------+----------------------+----------------------+--------------------------------+");
		System.out.printf("| %-10s | %-27s | %-15s | %-23s | %-20s | %-20s  | %-27s | %-20s | %-20s | %-30s |\n", "Options", "Property", "Field", "Minor Dev Name", "Cost of Minor Dev", "Number of Minor Devs", "Major Dev Name", "Cost of Major Dev", "Number of major Devs", "Contribution with next upgrade");
		System.out.println("+------------+-----------------------------+-----------------+-------------------------+----------------------+-----------------------+-----------------------------+----------------------+----------------------+--------------------------------+");
		int count =0;
		for (FieldSquare fs : options) {
			count++;
			System.out.printf("| %-10d | %-27s | %-15s | %-23s | % -20.0f | % -20d  | %-27s | %-20.0f | %-20d | %-30.0f |\n", count, fs.getName(), fs.getField(), fs.getMinorDevName(), fs.getMinorDevCost(), fs.getMinorDevCount(), fs.getMajorDevName(), fs.getMajorDevCost(), fs.getMajorDevCount(), upgradeRent(fs));
			
		}
		System.out.println("+------------+-----------------------------+-----------------+-------------------------+----------------------+-----------------------+-----------------------------+----------------------+----------------------+--------------------------------+");
		
		
		System.out.println("Do you still wish to develop?");
		if(Functionality.yesOrNo()) {
			
		int userInt=12;
		do {
			if(userInt==12) {
				System.out.println("Please select a number for the property you wish to develop");
				userInt = Functionality.userIntResponse();
				userInt--;	
			}else {
				System.out.println("Please select a valid number");
				userInt = Functionality.userIntResponse();
				userInt--;
			}
		}while(userInt<0 || userInt>=options.size());
	
		//if a major development is already established
			if (options.get(userInt).getMajorDevCount()==1) {
				System.out.println("You cannot develop this property any further");
				System.out.println("Please select another property.");
				dowhile=1;
				}
		
		//if there is already max amount of minor developments
			else if (options.get(userInt).getMinorDevCount()==3) {
			
			System.out.println("You cannot place anymore minor developments on this site");
			System.out.println("Would you like to place a major dev on this square?");
			
			//option to user to place a major dev on the sq
			if(Functionality.yesOrNo()) {
				
				//check if user has enough points to create a major development
				if(p.getEcoPoints()>options.get(userInt).getMajorDevCost()) {
				options.get(userInt).majorDev();
				p.setEcoPoints(p.getEcoPoints()-options.get(userInt).getMajorDevCost());
				System.out.println("You have major developed this square to " + options.get(userInt).getMajorDevName() +".");
				System.out.println("Your new eco points balance is " +p.getEcoPoints());
			
					} else {System.out.println("You do not have enough eco points to place a major development on "+options.get(userInt).getName()+".");
							System.out.println("Please selecte another development site!");
							dowhile=1;
							}
				
				}else { 
				//if user chooses not to major dev, the options screen will appear again
				dowhile=1;
				}
			

			} 
			
			//if the square is eligible for minor development	
			else {
			
			System.out.printf("You want to place a minor development of %s on the %s square.\n",options.get(userInt).getMinorDevName(),options.get(userInt).getName());
			System.out.println("To confirm this is the square you wish to develop, press y. To return to options press n.");
			
			//giving user option to confirm or return to options
			if(Functionality.yesOrNo()) {
				//check if user can afford a minor development 
				if(p.getEcoPoints()>options.get(userInt).getMinorDevCost()) {
					options.get(userInt).minorDev();
					p.setEcoPoints(p.getEcoPoints()-options.get(userInt).getMinorDevCost());
					System.out.println("Minor development complete!");
					System.out.println("There is now "+options.get(userInt).getMinorDevCount()+" on this square");
					} else {
						System.out.println("You don't have enough eco points to place a minor development on "+options.get(userInt).getMinorDevName());
						System.out.println("Please select another development site");
						dowhile=1;
						}

			}else {
				dowhile=1;
			
			}
				
		}
	
		}else {
			dowhile=0;
		}
		
	}while(dowhile!=0);
}

	/**
	 * Actions the payments of rent if required and outputs to screen to inform player of amount and to whom.
	 * @param p
	 */
	public static void payRent(Player p, ArrayList<Player> players, ArrayList<FieldSquare> fieldSquares, ArrayList<Square> board) {
		
			for(FieldSquare fs : fieldSquares) {
			
			if(fs.getSquareId()==p.getSquareId()) {

				//deduct rent from player
				if(p.getEcoPoints()>fs.getRent()) {
					p.setEcoPoints(p.getEcoPoints() - fs.getRent());	
					
					for(Player p2 : players) {
						if(p2.getName().contentEquals(fs.getOwnerName())) {
							
						p2.setEcoPoints(p2.getEcoPoints()+fs.getRent());
						
						System.out.printf("%s, you have given %s a contribution of %.0f eco points\n", p.getName(), fs.getOwnerName(),
								fs.getRent());
					}

				}
					
				}else {
					System.out.println(p.getName() + " you cannot afford the eco-points contribution at this property. You have ruined the environment with your "
							+ "anti-green attitude.");
					p.setEcoPoints(0);
					gameOver(p, players, fieldSquares, board);
				}
			}
		}
	}

	/**
	 * A method which will tell the user where they have landed based on their dice roll
	 * @param p
	 */
	public static void playerLands(Player p, ArrayList<Square> board) {

		
		System.out.println(p.getName() + ", your turn to roll the dice");
		Functionality.pressEnter();
		int diceRoll = rollDice(p);
		
		
		 if (p.setSquareId(diceRoll)){
			 if(p.getSquareId()==1) {
				 System.out.println(p.getName() + ", you have landed on [" + getBoardSqName(p, board) + "].\n");
				 p.setEcoPoints(p.getEcoPoints()+20);
				 System.out.printf("You have completed a loop of the board and recieved 20 eco points.\nYou now have %.0f eco points.\n", p.getEcoPoints());
				 BoardSetUp.START_SQUARE.factsOnUN();
			 } else {
				 System.out.println(p.getName() + ", you have landed on [" + getBoardSqName(p, board) + "].");
					p.setEcoPoints(p.getEcoPoints()+20);
					System.out.printf("You have also passed start and recieved 20 eco points.\nYou now have %.0f eco points.\n", p.getEcoPoints());
			 		}
			}
		
		else{
			System.out.println(p.getName() + ", you have landed on [" + getBoardSqName(p, board) + "].");
		}
		
	}
	
	/**
	 * 2 random numbers generated and total of the 2 numbers returned as an int.
	 * @param p
	 * @return
	 */
	public static int rollDice(Player p) {
		Random generator = new Random();
		int dice1, dice2, total;

		dice1 = generator.nextInt(5);
		dice1 += 1;
		dice2 = generator.nextInt(5);
		dice2 += 1;
		total = dice1 + dice2;

		// System.out.printf("%s: %d\n", "Dice 1", dice1);
		// System.out.printf("%s: %d\n", "Dice 2", dice2);
		System.out.printf("%s: %d + %d = %d\n", "Dice roll ", dice1, dice2, (total));

		return total;

	}
	
	/**
	 * returns as a double, the potential contributions collected if the next upgrade is actioned
	 * @param fs
	 * @return
	 */
	public static double upgradeRent(FieldSquare fs) {
		double upgradeRent=0;
		if(fs.getMinorDevCount()==0) {
			upgradeRent=fs.getBaseRent()*1.1;
		} else if(fs.getMinorDevCount()==1) {
			upgradeRent=fs.getBaseRent()*1.2;
		} else if(fs.getMinorDevCount()==2) {
			upgradeRent=fs.getBaseRent()*1.3;
		} else if(fs.getMinorDevCount()==3 && fs.getMajorDevCount()==0) {
			upgradeRent=fs.getBaseRent()*1.5;
		} else if(fs.getMajorDevCount()==1) {
			upgradeRent=0;
		}
		return upgradeRent;
		
	}

	/**
	 * Returns as a double the current rent due on all fieldSquares
	 * @param fs
	 * @return
	 */
	public static double rentDue(FieldSquare fs) {
		double rentDue=0;
		if(fs.getOwnerName().equalsIgnoreCase("For Sale")) {
			rentDue=0;
		}else {
			rentDue=fs.getRent();
		}
		return rentDue;
	}
	
	public static void gameOver(Player p, ArrayList<Player> players, ArrayList<FieldSquare> fieldsquares, ArrayList<Square> board) {
		
		System.out.println("--------------------");
		System.out.println("Here are the results");
		System.out.println("--------------------");
		PlayerUtil.displayAllPlayerInfo(players);
		
		double highestPoints=0;
		String name = null;
		for(Player p2 : players) {
			if(p2.getEcoPoints()>highestPoints) {
				highestPoints=p2.getEcoPoints();
				name = p2.getName();
			}
		}
		System.out.printf("%s has been crowned the best eco warrior with a finishing balance of %.0f eco points.\n", name, highestPoints);
		System.out.println("Well done " +name+"!!" );
		
		
		PlaySaveOurPlanet.mainMenuOrQuitGame(players, fieldsquares, board);
	}
}
