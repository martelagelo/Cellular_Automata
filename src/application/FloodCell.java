package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
/**
 * Version 1
 * Date: 9/25/2014
 * 
 * Used for simple grid tests and neighbor logic testing
 * Floods the grid with a certain color
 * 
 * @author Michael Deng
 * @author Pranava Raparla
 * @author David Zhang
 *
 */
public class FloodCell extends Cell{

	private boolean OKToFlood = false;
	private List<WaTorCell> neighborsList = new ArrayList<WaTorCell>();
	
	/**
	 * Updates the state of the cell
	 */
	@Override
	protected void updateCell(int i, int j, Cell[][] cellMatrix) {
		OKToFlood = false;
		xPos = i;
		yPos = j;	
		floodSelf();
	}
	
	/**
	 * Checks to see if the current cell is next to a flooded cell
	 * If so, flood the current cell
	 */
	private void floodSelf() {
		if (findWantedNeighbors(Color.BLACK).size() != 0) {
			OKToFlood = true;
		}
		setUpdatedState();
	}
	
	/**
	 * Flood the current cell if it should be flooded
	 */
	private void setUpdatedState() {
		if (OKToFlood) {
			updatedState = Color.BLACK;
		}
		else {
			updatedState = currentState;
		}
	}

}
