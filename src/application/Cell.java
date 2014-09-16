package application;

import javafx.scene.paint.Paint;

public abstract class Cell {
	
	int xPos;
	int yPos;
	Paint currentState;
	Paint updatedState;
	Cell[][] Matrix;
}
