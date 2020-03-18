package saveourplanet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClimateFactsTest {

	ClimateFacts climateFacts = new ClimateFacts();
	
	String name;
	int squareID;
	
	@BeforeEach
	void setUp() throws Exception {
		
		name = "Climate Facts";
		squareID=7;
	}

	@Test
	void testClimateFacts() {
		assertNotNull(climateFacts);
	}

	@Test
	void testClimateFactsStringInt() {
		
		ClimateFacts cf = new ClimateFacts(name, squareID);
		
		assertEquals(name, cf.getName());
		assertEquals(squareID, cf.getSquareId());
		
	}

	
}
