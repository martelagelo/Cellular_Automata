package application;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class GameOfLifeCell {
	
	Paint currentState;
	Paint updatedState;
	
	void updateCell(int column, int row, GridPane grid){
		Node node = grid.getChildren().get(((row * ApplicationConstants.NUM_OF_COLUMNS) - 1) + (column + 1));
	}
	
	
}
