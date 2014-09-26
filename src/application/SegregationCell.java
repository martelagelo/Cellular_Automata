package application;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Version 1
 * 
 * Date: 9/14/2014
 * 
 * @author Michael Deng
 * @author Pranava Raparla
 * @author David Zhang
 *
 */
public class SegregationCell extends Cell {

	private double same = 0;
	
	/**
	 * Finds the percentage of the same colored neighbors out of the total amount of neighbors
	 * @return: The percentage of same colored neighbors
	 */
	private double percentageCalc(){
		same = findWantedNeighbors((Color) currentState).size();
		Double d = (same/8);
		return d;
	}
	
	/**
	 * Method for updating and moving the cell that is dissatisfied 
	 * @param percentage
	 */
	private void cellMover(double percentage){
		if (currentState == Color.WHITE && updatedState == null) updatedState = currentState;
		else if (currentState == Color.WHITE && updatedState != null) {} 
		else if (percentage < threshold){
			Boolean positionFound = false;
			outerloop:
			for(int b = yPos; b < ApplicationConstants.NUM_OF_ROWS; b++){
				for(int a = 0; a < ApplicationConstants.NUM_OF_COLUMNS; a++){
					if(b == yPos && a <= xPos) {
						continue; 
					} else if (Matrix[a][b].currentState==Color.WHITE && Matrix[a][b].updatedState == null){
						Matrix[a][b].updatedState = currentState;
						updatedState = Color.WHITE;
						positionFound = true;
						break outerloop;
					} 
				}
			}
			if(!positionFound) updatedState = currentState;
		}
		else updatedState = currentState;	
	}

	
	/**
	 * Updates the cell state
	 */
	@Override
	protected void updateCell(int i, int j, Cell[][] cellMatrix) {
		same = 0;
		threshold = 0.5;
		super.Matrix = cellMatrix;
		super.xPos = i;
		super.yPos = j;
		cellMover(percentageCalc());
	}

	
}

