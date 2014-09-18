package application;

import javafx.scene.paint.Paint;

public abstract class Cell {
	
	int xPos;
	int yPos;
	Paint currentState;
	Paint updatedState;
	Cell[][] Matrix;
	
	void update(){
		currentState = updatedState;
		updatedState = null;
	}
	
	void setXPos(int x){
		xPos = x;
	}
	
	void setYPos(int y){
		yPos = y;
	}
	
	abstract void setCurrentState(String s);
	
}
