package application;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public abstract class Cell {
	
	// variables that should be available to subclasses
	int xPos;
	int yPos;
	Paint currentState;
	Paint updatedState;
	Cell[][] Matrix;
	
	//For now, 3 is the limit for both.
	

	// Every frame, after the updateState is set, then all cells are updated
	void update(){
		currentState = updatedState;
		updatedState = null;
	}
	
	// Each subclass should know how to set its updatedState
	protected abstract void updateCell(int i, int j, Cell[][] cellMatrix);
	
	// Each class should know how to set its current state
	abstract void setCurrentState(String s);
	
	// Setting X and Y start positions happens at generation
	void setXPos(int x){
		xPos = x;
	}
	void setYPos(int y){
		yPos = y;
	}
	
	protected ArrayList findCardinalDirectionNeighbors(int i, int j, Color color) {
		ArrayList<Cell> list = new ArrayList<Cell>();
		System.out.println("YOOOOOOO");
		if (checkBounds(i+1,j)) {
			if ((this.Matrix[i + 1][j].currentState == color && Matrix[i+1][j].updatedState==null) || Matrix[i+1][j].updatedState == color) {
				list.add((Cell) Matrix[i + 1][j]);
			} 
		}
		if (checkBounds(i - 1,j)) {
			if((this.Matrix[i - 1][j].currentState == color && Matrix[i-1][j].updatedState==null) || Matrix[i-1][j].updatedState == color) {
				list.add((Cell) Matrix[i - 1][j]);
			}
		} 
		if (checkBounds(i,j+1)) {
			if ((this.Matrix[i][j+1].currentState == color && Matrix[i][j+1].updatedState==null) || Matrix[i][j+1].updatedState == color) {
				list.add((Cell) Matrix[i][j+1]);
			}
		} 
		if (checkBounds(i,j - 1)) {
			if ((this.Matrix[i][j-1].currentState == color && Matrix[i][j-1].updatedState==null) || Matrix[i][j-1].updatedState == color) {
				list.add((Cell) Matrix[i][j - 1]);
			}
		} 		
		return list;
	}
	
	private boolean checkBounds(int i, int j) {
		return (i < ApplicationConstants.NUM_OF_COLUMNS && i >= 0 && j < ApplicationConstants.NUM_OF_ROWS && j >= 0);
	}
	
	
	// For printing purposes
	@Override
	public String toString() {
		return "Cell: " + "\n\tX: " + xPos + "\n\tY: " + yPos + "\n\tState: " + currentState;
	}
}
