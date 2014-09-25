package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class GameOfLifeCell extends Cell {

	/**
	 * Updates the state of this particular cell
	 */
	@Override
	public void updateCell(int i, int j) {
		xPos = i;
		yPos = j;
		lifeUpdate(findWantedNeighbors(Color.BLACK).size());
		//lifeUpdate(findToroidalSquareNeighbors(xPos,yPos,Color.BLACK).size());					
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


	@Override
	protected void updateCell(int i, int j, Cell[][] cellMatrix) {
		// TODO Auto-generated method stub
		
	}
	
	


}
