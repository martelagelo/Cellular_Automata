package application;

import java.util.Random;

import javafx.scene.paint.Color;

public class FireCell extends Cell {

	private Boolean isNextToFire = false;
	private double burningChance = 0;

	public void updateCell(int column, int row, Cell[][] grid) {
		isNextToFire = false;
		super.Matrix = grid;
		super.xPos = column;
		super.yPos = row;
		findFireNeighbors();
		calculateBurnChance();
		updateState();
	}

	private void findFireNeighbors(){
		if(checkNeighbor(xPos+1,yPos) || checkNeighbor(xPos-1,yPos)|| checkNeighbor(xPos, yPos + 1) || checkNeighbor(xPos, yPos - 1)) {
			isNextToFire = true;
		}
	}

	private boolean checkNeighbor(int i, int j) {
		return (i < ApplicationConstants.NUM_OF_COLUMNS && i >= 0 && j < ApplicationConstants.NUM_OF_ROWS && j >= 0 && Matrix[i][j].currentState == Color.RED);
	}

	private void calculateBurnChance(){
		if (isNextToFire && super.currentState == Color.GREEN) {
			burningChance = .50;
		} else {
			burningChance = 0.0;
		}
	}

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
