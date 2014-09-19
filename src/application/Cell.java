package application;

import javafx.scene.paint.Paint;

public abstract class Cell {
	
	int xPos;
	int yPos;
	Paint currentState;
	Paint updatedState;
	Cell[][] Matrix;
	
	//For now, 3 is the limit for both.
	int turnsEating;
	int turnsHungry;
	
	
	void update(){
		currentState = updatedState;
		updatedState = null;
	}

	public abstract void updateCell(int i, int j, Cell[][] cellMatrix);
	
	void setXPos(int x){
		xPos = x;
	}
	
	void setYPos(int y){
		yPos = y;
	}
	
	abstract void setCurrentState(String s);
	
	@Override
	public String toString() {
		return "Cell: " + "\n\tX: " + xPos + "\n\tY: " + yPos + "\n\tState: " + currentState;
	}
}
