package application;

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
	public abstract void updateCell(int i, int j, Cell[][] cellMatrix);
	
	// Each class should know how to set its current state
	abstract void setCurrentState(String s);
	
	// Setting X and Y start positions happens at generation
	void setXPos(int x){
		xPos = x;
	}
	void setYPos(int y){
		yPos = y;
	}
	
	// For printing purposes
	@Override
	public String toString() {
		return "Cell: " + "\n\tX: " + xPos + "\n\tY: " + yPos + "\n\tState: " + currentState;
	}
}
