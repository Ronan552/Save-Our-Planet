package saveourplanet;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardSetUpTest {

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

	// Declare fieldsquare objects
	FieldSquare fs1, fs2, fs3;

	// Declare data for Players
	String playerName1, playerName2, playerName3;

	// Declare Player objects
	Player p1, p2, p3;

	// Declare ArrayLists
	ArrayList<Player> players;
	ArrayList<FieldSquare> fieldSquares;
	ArrayList<Square> board;
	
	//declare 2 non-field squares
	ClimateFacts climateFacts;
	StartSquare startSquare;

	@BeforeEach
	void setUp() throws Exception {

		// FieldSquare data
		name1 = "Name1"; name2 = "Name2"; name3 = "Name3";
		squareId1 = 2; squareId2 = 3; squareId3 = 4;
		field1 = Fields.RENEWABLES; field2 = Fields.LIFESTYLE; field3 = Fields.RECYCLE;
		buyPrice1 = 100; buyPrice2 = 200; buyPrice3 = 300;
		minorDevCost1 = 10; minorDevCost2 = 20;	minorDevCost3 = 30;
		majorDevCost1 = 40; majorDevCost2 = 50; majorDevCost3 = 60;
		rent1 = 20; rent2 = 40; rent3 = 60;
		minorDevName1 = "MinorDev1"; minorDevName2 = "MinorDev2"; minorDevName3 = "MinorDev3";
		majorDevName1 = "MajorDev1"; majorDevName2 = "MajorDev2"; majorDevName3 = "MajorDev3";

		fs1 = new FieldSquare(name1, squareId1, field1, buyPrice1, minorDevCost1, majorDevCost1, rent1, minorDevName1,
				majorDevName1);
		fs2 = new FieldSquare(name2, squareId2, field2, buyPrice2, minorDevCost2, majorDevCost2, rent2, minorDevName2,
				majorDevName2);
		fs3 = new FieldSquare(name3, squareId3, field3, buyPrice3, minorDevCost3, majorDevCost3, rent3, minorDevName3,
				majorDevName3);

		// Player data field
		playerName1 = "Player1"; playerName2 = "Player2"; playerName3 = "Player3";
		p1 = new Player(playerName1); p2 = new Player(playerName2); p3 = new Player(playerName3);

		players = new ArrayList<Player>();
		fieldSquares = new ArrayList<FieldSquare>();
		players.add(p1); players.add(p2); players.add(p3);
		fieldSquares.add(fs1); fieldSquares.add(fs2); fieldSquares.add(fs3);

		board = new ArrayList<Square>();
		board.add(fs1); board.add(fs2); board.add(fs3);
		
		climateFacts = new ClimateFacts("Climate Facts", 7);
		startSquare = new StartSquare();
	}

	@Test
	void testPopulateBoardSquares() {
		ArrayList<Square> boardSqaures = new ArrayList<Square>();
		boardSqaures = BoardSetUp.populateBoardSquares(fieldSquares, startSquare, climateFacts);
		
		assertTrue(boardSqaures.contains(fs1) && boardSqaures.contains(fs2) && boardSqaures.contains(fs3) && boardSqaures.contains(startSquare) && boardSqaures.contains(climateFacts));
	}

	

}
