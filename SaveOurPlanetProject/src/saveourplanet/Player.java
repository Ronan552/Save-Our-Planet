package saveourplanet;

/**
 * Player class - all relevant info needed for players playing save our planet.
 * @author nsweeney
 *
 */
public class Player {
	/**
	 * counts the number of players instantiated
	 */
	static int COUNTER=0;
	
	/**
	 * must be between 1-15 characters inc
	 * cannot start with a blank space
	 * 
	 */
	private String name;
	
	/**
	 * Each player is defaulted to start with 300points
	 */
	private double ecoPoints;
	
	/**
	 * The board is numbered 1-12. All players start on sq1.
	 * The sq id is set to one when a player is instantiated.
	 * Sq position is reset to 1 after passing sq 12.
	 */
	private int squareId;
	
	/**
	 * set equal to counter which is incremented after a player is instantiated
	 */
	private int playerId;
	
	/**
	 * default constructor
	 */
	public Player() {
		this.ecoPoints=1500;
		this.squareId=1;
		COUNTER++;
		this.playerId=COUNTER;
	}
	
	/**
	 * @param name
	 */
	public Player(String name) {
		super();
		setName(name);
	}
	
	
	/**
	 * @return the playerId
	 */
	public int getPlayerId() {
		return playerId;
	}


	
	/**
	 * @return the squareId
	 */
	public int getSquareId() {
		return squareId;
	}

	/**
	 * once the sq id is greater than 12, one loop of the board has been completed. This activates the 
	 * first if statement and the sq id is deducted 12 to simulate starting from 1 on the board.
	 * The boolean is set to true to indicate the player has passed start/go.
	 * @param squareId the squareId to set
	 */
	public boolean setSquareId(int squareId) {
		boolean reset = false;
		if((this.squareId+squareId)>12) {
			int total = this.squareId+squareId;
			this.squareId=total-12;
			reset=true;
		}else {
			this.squareId = this.squareId + squareId;
		}

		return reset;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) throws IllegalArgumentException {
		
		if(name.startsWith(" ")) {
			throw new IllegalArgumentException("Name cannot start with a blank space");
			
		} else if(name.length()>15) {
			throw new IllegalArgumentException("Name cannot be longer than 15 characters");
		} else if(name.length()<1) {
			throw new IllegalArgumentException("Name must contain at least 1 character");
		} else {
			this.name = name;
		}

	}
	
	
	/**
	 * @return the balance
	 */
	public double getEcoPoints() {
		return ecoPoints;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setEcoPoints(double ecoPoints) {
		this.ecoPoints = ecoPoints;
	}

	/**
	 * A method to display all information about a player to screen
	 */
	public void displayAll() {
		System.out.printf("%-15s: %s\n", "Player Name", this.name);
		System.out.printf("%-15s: %.0f\n", "Eco Points", this.ecoPoints);
		System.out.printf("%-15s: %d\n", "Current square", this.squareId);
		System.out.printf("%-15s: %d\n", "PlayerId", this.playerId);
		System.out.println("-----------------------------------------");
	}
	
	

}
