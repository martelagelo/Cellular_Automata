package application;

import java.util.Random;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


public class Grid {
	
	Cell[][] cellMatrix = new Cell[ApplicationConstants.NUM_OF_COLUMNS][ApplicationConstants.NUM_OF_ROWS];
	
	void updateGrid(GridPane gridpane){
		updateCellMatrix();
		repopulateGridPane(gridpane);
		
	}
	
	public void initializeAndPopulateMatrix(int i, int j, Paint initialColor){
		cellMatrix[i][j] = new GameOfLife();
		cellMatrix[i][j].currentState = initialColor; //Some value that will be inputed from the XML file.
		System.out.println(cellMatrix[i][j].currentState);
	}
	
	private void updateCellMatrix(){
		for(int i = 0; i < ApplicationConstants.NUM_OF_COLUMNS; i++) {
			for(int j = 0; j < ApplicationConstants.NUM_OF_ROWS; j++) {
				cellMatrix[i][j].updateCell(i, j, cellMatrix);
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
		rect.setFill(cell.updatedState);
		//rect.setFill(generateRandomColor());
		return rect;
	}
	
	private Paint generateRandomColor() {
		Random rand = new Random();
		int i = rand.nextInt(100);
		if (i < 25) {
			return Color.RED;
		} else if (i > 75) {
			return Color.BLUE;
		} else {
			return Color.WHITE;
		}
	}
}
