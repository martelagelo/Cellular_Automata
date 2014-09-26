package application;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class AntCell extends Cell {

	private boolean hasFoodItem = false;
	private int homePheromones;
	private int foodPheromones;
	//private AntCell ;
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
		Cell foodCell = findParticularCell(Color.GREEN);
		if (foodCell != null) {
			
		}
	}
	
	private void findFoodSource() {
		Cell foodCell = findParticularCell(Color.GREEN);
		if (foodCell == null) {
			
		}
	}
	
	private ArrayList findNeighbors (Color color){
		ArrayList<AntCell> list = new ArrayList<AntCell>();
		for(int i = xPos-1; i <= xPos+1; i++){
			for(int j = yPos-1; j <= yPos+1; j++){
				if (Matrix[i][j].currentState == color) {
					list.add((AntCell) Matrix[i][j]);
				}
			}
		}
		return list;
	}
	
	private Cell findParticularCell(Color color) {
		for(int i = xPos-1; i <= xPos+1; i++){
			for(int j = yPos-1; j <= yPos+1; j++){
				if (Matrix[i][j].currentState == color) {
					return Matrix[i][j];
				}
			}
		}
		return null;
	}
	
	private boolean checkBounds(int i, int j){
		return (i < ApplicationConstants.NUM_OF_COLUMNS && i >= 0 && j < ApplicationConstants.NUM_OF_ROWS && j >= 0);
	}
	
}
