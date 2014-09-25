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
		lifeUpdate(findToroidalSquareNeighbors(xPos,yPos,Color.BLACK).size());					
	}

	
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

	@Override
	void setCurrentState(String s) {
		// TODO Auto-generated method stub
		
	}
	
	


}
