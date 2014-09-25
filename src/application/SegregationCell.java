package application;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class SegregationCell extends Cell {

	private double same = 0;
	private double different = 0;

	public SegregationCell(){
		//same = 0;
		//different = 0;
		threshold = 0.5;
		xPos = 0;
		//currentState = Color.WHITE;
	}
	
	private double percentageCalc(){
		for(int i = xPos-1; i <= xPos+1; i++){
			for(int j = yPos-1; j <= yPos+1; j++){
				if(i >= 0 && j >= 0 && i < ApplicationConstants.NUM_OF_COLUMNS && j < ApplicationConstants.NUM_OF_ROWS){
					if(Matrix[i][j].currentState==Matrix[xPos][yPos].currentState){
						same++;
					} else {
						different++;							
					}
				}							
			}
		}
		same--;
		Double d = (same/(same+different));
		return d;
	}

	
	
	/*
	 * Method for updating and moving the cell that is dissatisfied 
	 */
	private void cellMover(double percentage){
		if (Matrix[xPos][yPos].currentState == Color.WHITE && Matrix[xPos][yPos].updatedState == null) {
			Matrix[xPos][yPos].updatedState = Matrix[xPos][yPos].currentState;
		} else if (Matrix[xPos][yPos].currentState == Color.WHITE && Matrix[xPos][yPos].updatedState != null) {
		} else if (percentage < threshold){
			Boolean positionFound = false;
			outerloop:
			for(int b = yPos; b < ApplicationConstants.NUM_OF_ROWS; b++){
				for(int a = 0; a < ApplicationConstants.NUM_OF_COLUMNS; a++){
					if(b == yPos && a <= xPos) {// check if in part of row before current cell
						continue; // continue to next iteration if true
					} else if (Matrix[a][b].currentState==Color.WHITE && Matrix[a][b].updatedState == null){
						Matrix[a][b].updatedState = Matrix[xPos][yPos].currentState;
						Matrix[xPos][yPos].updatedState = Color.WHITE;
						positionFound = true;
						break outerloop;
					} 
				}
			}
			if(!positionFound) {
				Matrix[xPos][yPos].updatedState = Matrix[xPos][yPos].currentState;
			}
		} else {
			Matrix[xPos][yPos].updatedState = Matrix[xPos][yPos].currentState;	
		}
	}

	@Override
	void setCurrentState(String s) {

		switch(s.toUpperCase()) {
		case "WHITE":
			currentState = Color.WHITE;
			break;
		case "RED":
			currentState = Color.RED;
			break;
		case "BLUE":
			currentState = Color.BLUE;
			break;
		}
	}

	@Override
	public void updateCell(int i, int j, Cell[][] cellMatrix) {
		same = 0;
		different = 0;
		super.Matrix = cellMatrix;
		super.xPos = i;
		super.yPos = j;
		cellMover(percentageCalc());
	}
}

