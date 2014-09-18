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
	
	void SetXPos(int x){
		xPos = x;
	}
	
	void SetYPos(int y){
		yPos = y;
	}
	
	abstract void setCurrentState(String s);
	
	
	
}
