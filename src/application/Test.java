package application;

import static org.junit.Assert.*;

public class Test {


	@org.junit.Test
	public void testWaTorFishEaten(){
		WaTorCell wator = new WaTorCell();
		int numberOfFishToEat = wator.numberOfFish();
		int numberOfEmptyAfter = wator.numberOfEmptyTest;
		assertEquals(numberOfFishToEat, numberOfEmptyAfter);
		
	}
	
}
