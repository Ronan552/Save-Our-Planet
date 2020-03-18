/**
 * 
 */
package saveourplanet;

import java.util.Random;

/**
 * @author User
 *
 */
public class StartSquare extends Square {

	/**
	 * Default Constructor
	 */
	public StartSquare() {
		setName("UN Climate Summit");
		setSquareId(1);

	}
	
	public void factsOnUN() {
		Random random = new Random();
		int randomNum = random.nextInt(4);
		
		switch(randomNum) {
		
		case 0:
			System.out.println("Did you know.....The UN was founded after the Second World War to replace the League Of Nations which had been so ineffectual in preventing war.");
			break;
		case 1:
			System.out.println("Did you know, When the UN was founded there were 45 members. There are now 193.\n");
			break;
		case 2:
			System.out.println("Did you know, UN Peacekeeping forces comprise 116,919 field forces from 123 countries.\n");
			break;
		case 3:
			System.out.println("Did you know, The UN provides food to 90 million people…\n");
			break;
	}
	}	
	
}
