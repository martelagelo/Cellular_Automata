package application;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


public class Grid {
	
	Cell[][] cellMatrix = new Cell[ApplicationConstants.NUM_OF_COLUMNS][ApplicationConstants.NUM_OF_ROWS];
	
	void updateGrid(GridPane gridpane){
		initializeAndPopulateMatrix();
		updateCellMatrix();
		repopulateGridPane(gridpane);
	}
	
	private void initializeAndPopulateMatrix(){
		for(int i = 0; i < ApplicationConstants.NUM_OF_COLUMNS; i++) {
			for(int j = 0; j < ApplicationConstants.NUM_OF_ROWS; j++) {
				cellMatrix[i][j] = new Segregation();
				cellMatrix[i][j].currentState = Color.WHITE; //Some value that will be inputed from the XML file.
			}
		}
	}
	
	private void updateCellMatrix(){
		for(int i = 0; i < ApplicationConstants.NUM_OF_COLUMNS; i++) {
			for(int j = 0; j < ApplicationConstants.NUM_OF_ROWS; j++) {
				//cellMatrix[i][j].updateCell(i, j, cellMatrix);
			}
		}
	}
	
	private void repopulateGridPane(GridPane gridpane){
		for(int i = 0; i < ApplicationConstants.NUM_OF_COLUMNS; i++) {
			for(int j = 0; j < ApplicationConstants.NUM_OF_ROWS; j++) {
				Rectangle rect = generateCell(cellMatrix[i][j]);
				gridpane.add(rect, i, j, 1, 1);
			}	
		}
	}
	
	private Rectangle generateCell(Cell cell){
		Rectangle rect = new Rectangle();
		rect.setWidth(ApplicationConstants.CELL_WIDTH);
		rect.setHeight(ApplicationConstants.CELL_WIDTH);
		rect.setFill(cell.updatedState);;
		return rect;
	}
}
