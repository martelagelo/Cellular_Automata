package application;

import java.util.ArrayList;
import java.util.List;
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
	// use threshold for burningChance
	private double threshold = 0;
	
	private List<WaTorCell3> fireNeighbors = new ArrayList<WaTorCell3>();

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
		fireNeighbors = findToroidalCornerNeighbors(xPos, yPos, Color.RED);
		if (fireNeighbors.size() != 0) {
			isNextToFire = true;
		}
	}

	/**
	 * Calculates the chance of the current cell burning
	 */
	private void calculateBurnChance(){
		if (isNextToFire && super.currentState == Color.GREEN) {
			threshold = .50;

		} else {
			threshold = 0.0;
		}
	}

	/**
	 * Updates the state of the cell based off of the burning chance
	 */
	private void updateState() {
		if (super.currentState == Color.RED) {
			super.updatedState = Color.YELLOW;
		}
		else if (ApplicationConstants.rand.nextFloat() < threshold) {
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
