package application;

import javafx.scene.paint.Color;

public class GameOfLife extends Cell {

	private int Alive;
	
	@Override
	public void updateCell(int i, int j, Cell[][] cellMatrix) {
		Alive = 0;
		Matrix = cellMatrix;
		xPos = i;
		yPos = j;
		lifeUpdate(aliveCalculator());					
	}

	/*
	 * Method for calculating alive neighbours
	 */

	private int aliveCalculator(){
		for(int i = xPos-1; i <= xPos+1; i++){
			for(int j = yPos-1; j <= yPos+1; j++){
				//System.out.println(i + "    " + j);
				if(i >= 0 && j >= 0 && i < ApplicationConstants.NUM_OF_COLUMNS && j < ApplicationConstants.NUM_OF_ROWS && Matrix[i][j].currentState == Color.BLACK) {
						Alive++;
				}		
			}
		}
		if (currentState == Color.BLACK) Alive --;
		
		//System.out.println("Alive: " + Alive);
		return Alive;
	}
	
	/*
	 * Updates state of current cell depending on Game of Life rules
	 */
	
	private void lifeUpdate(int count){
			if(count == 2){
				updatedState = currentState;
			} else if (count == 3) {
				updatedState = Color.BLACK;
			} else {
				updatedState = Color.WHITE;
			}
	}
}
