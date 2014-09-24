package application;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class AntCell extends Cell {

	private boolean hasFoodItem = false;
	private int homePheromones;
	private int foodPheromones;
	private AntCell ;
	private AntCell foodPile;
	
	@Override
	public void updateCell(int i, int j, Cell[][] cellMatrix) {
		xPos = i;
		yPos = j;
		Matrix = cellMatrix;
	}

	private void antForage() {
		if (currentState == Color.RED) {
			 updatedState = Color.RED;
		}
		else if (currentState == Color.GREEN) {
			updatedState = Color.GREEN;
		}
		else if (hasFoodItem) {
			returnToNest();
		} 
		else {
			findFoodSource();
		}
	}
	
	private void returnToNest() {
		AntCell foodCell = findParticularCell(Color.GREEN);
		if (foodCell == null) {
			
		}
	}
	
	private void findFoodSource() {
		AntCell foodCell = findParticularCell(Color.GREEN);
		if (foodCell == null) {
			
		}
	}
	
	private Cell findNeighbors (Color color){
		ArrayList<AntCell> list = new ArrayList<AntCell>();
		for(int i = xPos-1; i <= xPos+1; i++){
			for(int j = yPos-1; j <= yPos+1; j++){
				if (Matrix[i][j].currentState == color) {
					return Matrix[i][j];
				}
			}
		}
		return null;
	}
	
	private AntCell findParticularCell(Color color) {
		
	}
	
	private boolean checkBounds(int i, int j){
		return (i < ApplicationConstants.NUM_OF_COLUMNS && i >= 0 && j < ApplicationConstants.NUM_OF_ROWS && j >= 0);
	}
	
	@Override
	void setCurrentState(String s) {
		// TODO Auto-generated method stub
		
	}

}
