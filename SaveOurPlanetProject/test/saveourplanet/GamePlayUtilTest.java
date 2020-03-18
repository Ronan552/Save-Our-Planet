package saveourplanet;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GamePlayUtilTest {
	// Declare data for FieldSquare
	String name1, name2, name3;
	int squareId1, squareId2, squareId3;
	Fields field1, field2, field3;
	double buyPrice1, buyPrice2, buyPrice3;
	double minorDevCost1, minorDevCost2, minorDevCost3;
	double majorDevCost1, majorDevCost2, majorDevCost3;
	double rent1, rent2, rent3;
	String minorDevName1, minorDevName2, minorDevName3;
	String majorDevName1, majorDevName2, majorDevName3;
	
	//Declare fieldsquare objects
	FieldSquare fs1, fs2, fs3;
	
	//Declare data for Players
	String playerName1, playerName2, playerName3;
	
	// Declare Player objects
	Player p1, p2, p3;
	
	//Declare ArrayLists
	ArrayList<Player> players;
	ArrayList<FieldSquare> fieldSquares;
	ArrayList<Square> board;
	

	@BeforeEach
	void setUp() throws Exception {
		//FieldSquare data
		name1 = "Name1"; name2="Name2"; name3="Name3";
		squareId1=2; squareId2=3; squareId3=4;
		field1=Fields.RENEWABLES; field2=Fields.LIFESTYLE; field3=Fields.RECYCLE;
		buyPrice1=100; buyPrice2=200; buyPrice3=300;
		minorDevCost1=10; minorDevCost2=20; minorDevCost3=30;
		majorDevCost1=40; majorDevCost2=50; majorDevCost3=60;
		rent1=20; rent2=40; rent3=60;
		minorDevName1="MinorDev1"; minorDevName2="MinorDev2"; minorDevName3="MinorDev3";
		majorDevName1="MajorDev1"; majorDevName2="MajorDev2"; majorDevName3="MajorDev3";

		fs1 = new FieldSquare(name1, squareId1, field1, buyPrice1, minorDevCost1, majorDevCost1, rent1, minorDevName1, majorDevName1);
		fs2 = new FieldSquare(name2, squareId2, field2, buyPrice2, minorDevCost2, majorDevCost2, rent2, minorDevName2, majorDevName2);
		fs3 = new FieldSquare(name3, squareId3, field3, buyPrice3, minorDevCost3, majorDevCost3, rent3, minorDevName3, majorDevName3);
		
		//Player data field
		playerName1="Player1"; playerName2 = "Player2"; playerName3 = "Player3"; 
		p1 = new Player(playerName1); p2 = new Player(playerName2); p3 = new Player(playerName3);
		
		players = new ArrayList<Player>();
		fieldSquares = new ArrayList<FieldSquare>();
		players.add(p1); players.add(p2); players.add(p3);
		fieldSquares.add(fs1); fieldSquares.add(fs2); fieldSquares.add(fs3);
		
		board = new ArrayList<Square>();
		board.add(fs1); board.add(fs2); board.add(fs3);
	}

	@Test
	void testIsPropertyAvailable() {
		fs1.setOwnerName("For Sale");
		p1.setSquareId(2);
		assertTrue(GamePlayUtil.isPropertyAvailable(p1, fieldSquares));
	}
	
	
	@Test
	void testPropertyIsNotAvailable() {
		p1.setSquareId(2);
		fs1.setOwnerName(playerName1);
		assertTrue(!GamePlayUtil.isPropertyAvailable(p1, fieldSquares));
	}

	@Test
	void testPlayerDoesOwnProperty() {
		p1.setSquareId(2);
		fs1.setOwnerName(playerName1);
		assertTrue(GamePlayUtil.doesPlayerOwnProperty(p1, fieldSquares));
	}
	
	@Test
	void testPlayerDoesntOwnProperty() {
		assertTrue(!GamePlayUtil.doesPlayerOwnProperty(p1, fieldSquares));
	}

	
	@Test
	void testIsRentRequired() {
		p1.setSquareId(squareId1);
		fs1.setSquareId(squareId1);
		fs1.setOwnerName(playerName2);
		assertTrue(GamePlayUtil.IsRentRequired(p1, fieldSquares));
	}
	
	@Test
	void testifRentIsNotRequired() {
		p1.setSquareId(squareId1);
		fs1.setSquareId(squareId1);
		assertTrue(!GamePlayUtil.IsRentRequired(p1, fieldSquares));
	}

	@Test
	void testGetBoardSqName() {
		p1.setSquareId(squareId1);
		assertEquals(fs1.getName(), GamePlayUtil.getBoardSqName(p1, board));
	}

	@Test
	void testDoesPlayerOwnField() {
		fs1.setField(field1);
		fs1.setOwnerName(playerName1);
		fs2.setField(field1);
		fs2.setOwnerName(playerName1);
		fs3.setField(field1);
		fs3.setOwnerName(playerName1);
		
		assertTrue(GamePlayUtil.doesPlayerOwnField(p1, fieldSquares));
	}
	
	@Test
	void testPlayerDoesNotOwnField() {
		fs1.setField(field2);
		fs1.setOwnerName(playerName2);
		fs2.setField(field2);
		fs2.setOwnerName(playerName1);
		fs3.setField(field2);
		fs3.setOwnerName(playerName3);
		
		assertTrue(!GamePlayUtil.doesPlayerOwnField(p1, fieldSquares));
	}

	@Test
	void testOwnedFieldsList() {
		fs1.setField(field1);
		fs1.setOwnerName(playerName1);
		fs2.setField(field1);
		fs2.setOwnerName(playerName1);
		fs3.setField(field1);
		fs3.setOwnerName(playerName1);
		
		assertTrue(GamePlayUtil.ownedFieldsList(p1, fieldSquares).contains(fs3));
	}


	@Test
	void testRollDice() {
		assertTrue(GamePlayUtil.rollDice(p1)>1 && GamePlayUtil.rollDice(p1)<=12);
	}
	
	@Test
	void rentDue() {
		fs1.setOwnerName(playerName1);
		assertEquals(rent1, GamePlayUtil.rentDue(fs1));	
		
		fs1.setOwnerName("For Sale");
		assertEquals(0, GamePlayUtil.rentDue(fs1));
	}
	
	@Test
	void upgradeRent() {
		assertEquals(rent1*1.1, GamePlayUtil.upgradeRent(fs1));
	}
	
	@Test
	void upgradeRentwith1Minor() {
		fs1.minorDev();
		assertEquals((fs1.getBaseRent()*1.2), GamePlayUtil.upgradeRent(fs1));
	}
	
	@Test
	void upgradeRentwith2Minor() {
		fs1.minorDev();
		fs1.minorDev();
		assertEquals((fs1.getBaseRent()*1.3), GamePlayUtil.upgradeRent(fs1));
	}
	
	@Test
	void upgradeRentwith3Minor() {
		fs1.minorDev();
		fs1.minorDev();
		fs1.minorDev();
		assertEquals((fs1.getBaseRent()*1.5), GamePlayUtil.upgradeRent(fs1));
	}
	
	@Test
	void upgradeRentwith1Major() {
		fs1.minorDev();
		fs1.minorDev();
		fs1.minorDev();
		fs1.majorDev();
		assertEquals(0, GamePlayUtil.upgradeRent(fs1));
	}
	
	
	

}
