package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class GameOfLifeCell extends Cell {

	private int Alive;
	/**
	 * Updates the state of this particular cell
	 */
	@Override
	public void updateCell(int i, int j, Cell[][] cellMatrix) {
		Alive = 0;
		Matrix = cellMatrix;
		xPos = i;
		yPos = j;
		lifeUpdate(findSquareNeighbors(xPos,yPos,Color.BLACK).size());					
	}

	/**
	 * Calculates the number of alive cell neighbors
	 * @return: The number of alive neighbors
	 */
//	private int aliveCalculator(){
//		for(int i = xPos-1; i <= xPos+1; i++){
//			for(int j = yPos-1; j <= yPos+1; j++){
//				if(i >= 0 && j >= 0 && i < ApplicationConstants.NUM_OF_COLUMNS && j < ApplicationConstants.NUM_OF_ROWS && Matrix[i][j].currentState == Color.BLACK) {
//						Alive++;
//				}		
//			}
//		}
//		if (currentState == Color.BLACK) Alive --;
//		
//		return Alive;
//	}
	
	/**
	 * Updates the state of the cell
	 * @param count: The number of alive neighbors
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
