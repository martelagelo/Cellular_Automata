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
		System.out.println(xPos + "   " + yPos);
		findFireNeighbors();
		calculateBurnChance();
		updateState();
	}

	private void findFireNeighbors(){
		//System.out.println(super.Matrix[xPos + 1][yPos].currentState);
		if(checkNeighbor(xPos+1,yPos) || checkNeighbor(xPos-1,yPos)|| checkNeighbor(xPos, yPos + 1) || checkNeighbor(xPos, yPos - 1)) {
			isNextToFire = true;
		}
	}
	
	private boolean checkNeighbor(int i, int j) {
		return (i < ApplicationConstants.NUM_OF_COLUMNS && i > 0 && j < ApplicationConstants.NUM_OF_ROWS && j > 0 && Matrix[i][j].currentState == Color.RED);
	}

	private void calculateBurnChance(){
		if (isNextToFire) {
			if (super.currentState == Color.GREEN) {
				burningChance = .7;
			} else if (super.currentState == Color.YELLOW) {
				burningChance = .3;
			} else {
				burningChance = .8;
			}
		} else {
			if (super.currentState == Color.RED) {
				burningChance = 1.0;
			} else {
				burningChance = 0.0;
			}
		}
	}
	
	private void updateState() {
		Random rand = new Random();
		if (rand.nextFloat() <= burningChance) {
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
