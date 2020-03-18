package saveourplanet;


/**
 * Superclass containing name and sqaureId
 * @author rwhite, nsweeney, jpatterson and dgillespie
 *
 */
public abstract class Square implements Comparable<Square> {
	
	private String name;
	private int squareId;
	
	/**
	 * default constructor
	 */
	public Square() {
		
	}
	
	
	/**
	 * @param name
	 * @param squareId
	 */
	public Square(String name, int squareId) {
		this.name=name;
		this.squareId=squareId;
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
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the squareId
	 */
	public int getSquareId() {
		return squareId;
	}
	
	/**
	 * @param squareId the squareId to set
	 */
	public void setSquareId(int squareId) throws IllegalArgumentException {
		if(squareId<=12 && squareId>=1) {
			this.squareId=squareId;
		}else {
			throw new IllegalArgumentException("Square ID must be in the range 1-12 inclusive");
		}
	}
	
	/**
	 * Outputs info of sq to screen.
	 */
	public void displayAll() {
		System.out.println("**************************************************");
		System.out.printf("%-15s: %s\n", "Square Name", this.name);
		System.out.printf("%-15s: %d\n", "Square Number", this.squareId);
	}


	@Override
	public int compareTo(Square o) {
		
		if(this.squareId == o.getSquareId()) {
			return 0;			
		}else if(this.squareId>o.getSquareId()) {
			return 1;
		}else {
			return -1;
		}
	}

}
