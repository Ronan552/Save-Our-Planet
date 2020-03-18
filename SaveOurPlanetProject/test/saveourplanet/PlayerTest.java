package saveourplanet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder (MethodSorters.NAME_ASCENDING) 

class PlayerTest {
	
	//test data
	String nameBlank, name0Chars, name16Chars, name15Chars, name1Char;
	Player player;

	@BeforeEach
	void setUp() throws Exception {
		nameBlank=" ";
		name0Chars="";
		name16Chars = "1234567898765432";
		name15Chars = "123456789123456";
		name1Char = "1";
		
		player = new Player();
				
	}
	
	@Test
	void testPlayer() {
		assertNotNull(player);
	}
	
	@Test
	void testPlayerConstructorWithArgs() {
		Player player2 = new Player(name15Chars);
		assertNotNull(player2);
		assertEquals(name15Chars, player2.getName());
	}
	
	@Test
	void testPlayerConstructorWithInvalidArgs() {

		assertThrows(IllegalArgumentException.class, () -> {
			Player player2 =  new Player(name0Chars);
		});
	}
	
	@Test
	void testSetPlayerId() {
		assertEquals(1, player.getPlayerId());
	}
	
	
	@Test
	void testSetSquareIdGreaterThan12() {
		player.setSquareId(13);
		assertEquals(2, player.getSquareId());
		
		assertTrue(player.setSquareId(13));

	}
	
	@Test
	void testSetSquareIdLessThan12() {
		
		player.setSquareId(5);
		assertEquals(6, player.getSquareId());
		
		assertTrue(!player.setSquareId(5));

	}

	
	@Test
	void ztestInvalidName() {
		player = new Player();
		
		assertThrows(IllegalArgumentException.class, () ->{
			player.setName(name0Chars);
		});
		
		assertThrows(IllegalArgumentException.class, () ->{
			player.setName(name16Chars);
		});
		
		assertThrows(IllegalArgumentException.class, () ->{
			player.setName(nameBlank);
		});
	}

	
	@Test
	void testValidName() {
		
		player.setName(name1Char);
		assertEquals(name1Char, player.getName());
		
		player.setName(name15Chars);
		assertEquals(name15Chars, player.getName());
	}

	
	@Test
	void testSetEcoPoints() {
		
		assertEquals(1500, player.getEcoPoints());
		
		player.setEcoPoints(500);
		assertEquals(500, player.getEcoPoints());	
		
	}

}
