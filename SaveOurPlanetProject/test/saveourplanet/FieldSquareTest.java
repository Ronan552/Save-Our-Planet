package saveourplanet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FieldSquareTest {

	//creating the FieldSquare object
		FieldSquare fieldSquare = new FieldSquare();
		//creating an instance of the player
		Player player = new Player();
		
		//Declare data for the player name  
		String validName; 
		
		//Declare data for the development names 
		String minorDevName, majorDevName;
		
		//Declare data for the square 
		int  minorDevCount,   majorDevCount, squareID;
		double buyPrice, majorDevCost, minorDevCost, rent, baseRent;
	
		@BeforeEach
	void setUp() throws Exception {
		
		squareID=1;
		
		validName = "ValidName";
		minorDevName = "Solar"; 
		majorDevName="Solar Field";
		
		majorDevCount=1;
		buyPrice=40;
		minorDevCost=30;
		majorDevCost=60;
		rent=24;
		
	}



	@Test
	void testFieldSquare() {
		assertNotNull(fieldSquare);
	}

	@Test
	void testFieldSquareStringIntFieldsDoubleDoubleDoubleDoubleStringString() {
		
		FieldSquare fs = new FieldSquare(validName, squareID, Fields.LIFESTYLE, buyPrice, minorDevCost, majorDevCost, baseRent, minorDevName, majorDevName);
		assertEquals(validName, fs.getName());
		assertEquals(squareID, fs.getSquareId());
		assertEquals(buyPrice, fs.getBuyPrice());
		assertEquals(minorDevCost, fs.getMinorDevCost());
		assertEquals(minorDevName, fs.getMinorDevName());
		assertEquals(majorDevName, fs.getMajorDevName());
		assertEquals(majorDevCost, fs.getMajorDevCost());
		assertEquals(Fields.LIFESTYLE, fs.getField());
		assertEquals(baseRent, fs.getBaseRent());
		
	}

	@Test
	void testGetSetMajorDevCount() {
		
		fieldSquare.setMajorDevCount(majorDevCount);
		assertEquals(majorDevCount, fieldSquare.getMajorDevCount());
	}

	@Test
	void testGetBaseRent() {
		 fieldSquare.setBaseRent(30);
		 assertEquals(30, fieldSquare.getBaseRent());
	}

	@Test
	void testGetMinorDevCount() {
		fieldSquare.setMinorDevCount(2);
		assertEquals(2, fieldSquare.getMinorDevCount());	}


	@Test
	void testGetSetMajorDevName() {
		fieldSquare.setMajorDevName("Solar Panel");
		assertEquals("Solar Panel", fieldSquare.getMajorDevName());
	}


	@Test
	void testGetSetRent() {
		fieldSquare.setRent(24);
		assertEquals(24, fieldSquare.getRent());
	}


	@Test
	void testGetSetMinorDevName() {
		fieldSquare.setMinorDevName("Solar Panel");
		assertEquals("Solar Panel",fieldSquare.getMinorDevName());
	}


	@Test
	void testGetSetField() {
		fieldSquare.setField(Fields.LIFESTYLE);
		assertEquals(Fields.LIFESTYLE, fieldSquare.getField());
	}

	

	@Test
	void testGetSetOwnerName() {
		fieldSquare.setOwnerName(player.getName());
		assertEquals(player.getName(), fieldSquare.getOwnerName());
	}


	@Test
	void testGetSetBuyPrice() {
		fieldSquare.setBuyPrice(70);
		assertEquals(70, fieldSquare.getBuyPrice());
	}

	

	@Test
	void testGetSetMinorDevCost() {
		fieldSquare.setMinorDevCost(120);
		assertEquals(120, fieldSquare.getMinorDevCost());
	}

	

	@Test
	void testGetSetMajorDevCost() {
		fieldSquare.setMajorDevCost(240);
		 assertEquals(240, fieldSquare.getMajorDevCost());
	}


	@Test
	void testMinorDev() {
		fieldSquare.minorDev();
		assertEquals(1, fieldSquare.getMinorDevCount());
		assertEquals(fieldSquare.getBaseRent()*1.1, fieldSquare.getRent());
		
		fieldSquare.setMajorDevCount(1);
		fieldSquare.minorDev();
		assertEquals(2, fieldSquare.getMinorDevCount());
		assertEquals(fieldSquare.getBaseRent()*1.2, fieldSquare.getRent());
		
		fieldSquare.setMajorDevCount(2);
		fieldSquare.minorDev();
		assertEquals(3, fieldSquare.getMinorDevCount());
		assertEquals(fieldSquare.getBaseRent()*1.3, fieldSquare.getRent());		
	}

	@Test
	void testMajorDev() {
		fieldSquare.majorDev();
		assertEquals(1, fieldSquare.getMajorDevCount());
		assertEquals(fieldSquare.getBaseRent()*1.5, fieldSquare.getRent());
		
	}

}
