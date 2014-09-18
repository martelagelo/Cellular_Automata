package application;

import java.util.Random;

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
public class FireCell extends Cell {

	private Boolean isNextToFire = false;
	private double burningChance = 0;

	/**
	 * Updates this particular cell based off its surroundings
	 */
	public void updateCell(int column, int row, Cell[][] grid) {
		isNextToFire = false;
		super.Matrix = grid;
		super.xPos = column;
		super.yPos = row;
		findFireNeighbors();
		calculateBurnChance();
		updateState();
	}

	/**
	 * Find if any of the neighbors are on fire
	 */
	private void findFireNeighbors(){
		if(checkNeighbor(xPos+1,yPos) || checkNeighbor(xPos-1,yPos)|| checkNeighbor(xPos, yPos + 1) || checkNeighbor(xPos, yPos - 1)) {
			isNextToFire = true;
		}
	}
	
	/**
	 * Checks if a particular neighbor is on fire
	 * @param i: The x position of the neighbor
	 * @param j: The y position of the neighbor
	 * @return: True if on fire
	 */
	private boolean checkNeighbor(int i, int j) {
		return (i < ApplicationConstants.NUM_OF_COLUMNS && i >= 0 && j < ApplicationConstants.NUM_OF_ROWS && j >= 0 && Matrix[i][j].currentState == Color.RED);
	}

	/**
	 * Calculates the chance of the current cell burning
	 */
	private void calculateBurnChance(){
		if (isNextToFire && super.currentState == Color.GREEN) {
			burningChance = .40;
		} else {
			burningChance = 0.0;
		}
	}

	/**
	 * Updates the state of the cell based off of the burning chance
	 */
	private void updateState() {
		Random rand = new Random();
		if (super.currentState == Color.RED) {
			super.updatedState = Color.YELLOW;
		}
		else if (rand.nextFloat() < burningChance) {
			super.updatedState = Color.RED;
		} else {
			super.updatedState = super.currentState;
		}
	}

	@Override
	void setCurrentState(String s) {
		// TODO Auto-generated method stub

	}

}
