package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

/**
 * Version 1
 * Date: 9/14/2014
 * 
 * @author Michael Deng
 * @author Pranava Raparla
 * @author David Zhang
 *
 */
public class GameOfLifeCell extends Cell {

	/**
	 * Updates the state of this particular cell
	 */
	@Override
	public void updateCell(int i, int j) {
		xPos = i;
		yPos = j;
		lifeUpdate(findWantedNeighbors(Color.BLACK).size());			
	}
	
	/**
	 * Updates the state of the cell
	 * @param count: The number of alive neighbors
	 */
	private void lifeUpdate(int count){
		if(count == 2)
			updatedState = currentState;
		else if (count == 3)
			updatedState = Color.BLACK;
		else
			updatedState = Color.WHITE;
	}

	@Override
	protected void updateCell(int i, int j, Cell[][] cellMatrix) {
		// TODO Auto-generated method stub
	}
}
