package saveourplanet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StartSquareTest {
	
	//create an instance of the start square
	StartSquare startSquare = new StartSquare();
	
	int carbonTotal; 
	
	@BeforeEach
	void setUp() throws Exception {
		
		
		carbonTotal=20;
	}

	@Test
	void testStartSquare() {
		assertNotNull(startSquare);
	}

	

}
