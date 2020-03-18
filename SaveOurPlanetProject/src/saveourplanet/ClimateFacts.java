package saveourplanet;

import java.util.Random;
/* Square to generate facts
* @author rwhite, nsweeney, jpatterson and dgillespie
*/
public class ClimateFacts extends Square {

	public ClimateFacts() {
		// TODO Auto-generated constructor stub
	}

	public ClimateFacts(String name, int squareId) {
		super(name, squareId);
	}
	
	public static void displayClimateFacts() {
		Random random = new Random();
		int randomNum = random.nextInt(16);
		
		switch(randomNum) {
		
		case 0:
			System.out.println("Did you know, the average wildlife populations have dropped by 60 per cent in just over 40 years\n");
			break;
		case 1:
			System.out.println("Did you know, there is more carbon dioxide in our atmosphere than any time in human history\n");
			break;
		case 2:
			System.out.println("Did you know, an area of coastal ecosystems larger than New York City is destroyed every year\n");
			break;
		case 3:
			System.out.println("Did you know, the 20 warmest years on record have been in the past 22 years\n");
			break;
		case 4:
			System.out.println("Did you know, more than 1 million species are at risk of extinction by climate change\n");
			break;
		case 5: 
			System.out.println("If everyone in the world lived the way people do in the U.S., it would take five Earths to provide enough resources for everyone\n");
			break;
		case 6: 
			System.out.println("Average global sea level is expected to rise 7 – 23 inches before the end of this century\n");
			break;
		case 7:
			System.out.println("More than a million species face potential extinction as a result of disappearing habitats, changing ecosystems, and acidifying oceans\n");
			break;
		case 8:
			System.out.println("The Arctic region may have its first completely ice-free summer by 2040\n");
			break;
		case 9:
			System.out.println("99.84% of the land in the state of California is suffering from drought\n");
			break;
		case 10:
			System.out.println("According to a recent report by Oxfam, climate change could push food prices by 50-60 percent more by 2030\n");
			break;
		case 11:
			System.out.println("The golden Toad is the first species to go extinct due to climate change\n");
			break;
		case 12:
			System.out.println("Climate change could be irreversible by 2030\n");
			break;
		case 13:
			System.out.println("2014 was the world’s hottest year on record, surpassing the previous record set in 2010, tied with 2005\n");
		break;
		case 14:
			System.out.println("Over 100 million people living in coastal regions will be displaced by just a one-yard rise in sea levels\n");
		break;
		case 15:
			System.out.println("In 1896, Swedish scientist Svante Arrhenius was the first to say that burning fossil fuels may eventually result in global warming\n");
		break;
		
		}
	}

}
