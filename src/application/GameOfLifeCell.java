// This code is part of my masterpiece
// Pranava K. Raparla

package application;

import java.util.*;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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
	 * Constructor that calls super class constructor.
	 * @param x: The x position of the cell
	 * @param y: The y position of the cell
	 * @param current: The current state of the cell
	 * @param updated: The updated state of the cell
	 * @param thresh: The cell's threshold for neighbors
	 * @param edge: The boundary type of the cell
	 * @param shape: The shape of the individual cell
	 */
	public GameOfLifeCell(int x, int y, Paint current, Paint updated, double thresh, String edge, String shape, List<Color> colors) { 
		super(x, y, current, updated, thresh, edge, shape, colors);
	}
	
	/**
	 * Finds neighbors and calls helper method for updating itself
	 */
	@Override
	protected void setUpdatedState() {
		lifeUpdate(findWantedNeighbors(cellColors.get(0)).size());	
	}
	
	/**
	 * Updates the state of the cell based on neighbor list
	 * @param count: The number of alive neighbors
	 */
	private void lifeUpdate(int count){
		if(count == 2)
			update();
		else if (count == 3)
			updatedState = cellColors.get(0);
		else
			updatedState = cellColors.get(1);
	}
}
