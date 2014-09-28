package application;

import static org.junit.Assert.*;

import org.junit.Test;

public class NeighborFinderTester {

	@Test
	public void testNeighborFindingCheckBounds() {
		NeighborFinder neighborFinder = new NeighborFinder();
		
		assertEquals(false , neighborFinder.checkBounds(0, -1));
		assertEquals(true, neighborFinder.checkBounds(39, 43));
		assertEquals(true, neighborFinder.checkBounds(4, 0));
		assertEquals(false, neighborFinder.checkBounds(39, ApplicationConstants.NUM_OF_ROWS + 1));
	}
	
	@Test
	public void testNeighborFindingFindYValue() {
		NeighborFinder neighborFinder = new NeighborFinder();
		
		assertEquals(40, neighborFinder.findYValue(39, 1));
		assertEquals(0, neighborFinder.findYValue(ApplicationConstants.NUM_OF_ROWS - 1, 3));
		assertEquals(ApplicationConstants.NUM_OF_ROWS - 1, neighborFinder.findYValue(0, -5));
	}
	
	@Test
	public void testNeighborFindingFindXValue() {
		NeighborFinder neighborFinder = new NeighborFinder();
		
		assertEquals(0, neighborFinder.findYValue(39));
		assertEquals(0, neighborFinder.findYValue(ApplicationConstants.NUM_OF_ROWS - 1));
		assertEquals(ApplicationConstants.NUM_OF_ROWS - 1, neighborFinder.findYValue(0));
	}
}
