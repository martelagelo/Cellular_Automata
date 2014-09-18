package application;

import java.util.Random;

import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


public class Grid {
	
	Cell[][] cellMatrix = new Cell[ApplicationConstants.NUM_OF_COLUMNS][ApplicationConstants.NUM_OF_ROWS];
	private Group root;
	GridPane gridpane;
	
	
	public GridPane updateGrid(GridPane gridpane){
		this.gridpane = gridpane;
		updateCellMatrix();
		return repopulateGridPane();
	}
	
	/**
	 * 
	 * @param i
	 * @param j
	 * @param initialColor
	 */
	public void initializeAndPopulateMatrix(int i, int j, Paint initialColor){
		cellMatrix[i][j] = new GameOfLife();
		cellMatrix[i][j].currentState = initialColor; //Some value that will be inputed from the XML file.
	}
	
	private void updateCellMatrix(){
		for(int i = 0; i < ApplicationConstants.NUM_OF_COLUMNS; i++) {
			for(int j = 0; j < ApplicationConstants.NUM_OF_ROWS; j++) {
				cellMatrix[i][j].updateCell(i, j, cellMatrix);
			}
		}
	}
	
	private GridPane repopulateGridPane(){
		this.root.getChildren().remove(gridpane);
		gridpane = new GridPane();
		for(int i = 0; i < ApplicationConstants.NUM_OF_COLUMNS; i++) {
			for(int j = 0; j < ApplicationConstants.NUM_OF_ROWS; j++) {
				cellMatrix[i][j].currentState = cellMatrix[i][j].updatedState;
				System.out.println("currentState = " + cellMatrix[i][j].currentState);
				Rectangle rect = generateCell(cellMatrix[i][j]);
				System.out.println(rect.getFill());
				gridpane.add(rect, i, j, 1, 1);
			}	
		}
		this.root.getChildren().add(gridpane);
		gridpane.toBack();
		
		return gridpane;
		
	}
	
	/**
	 * Generates a rectangle that becomes a cell in the grid pane
	 * @param cell: The current cell in the matrix being referred to
	 * @returns: A rectangle that will populate a cell in the grid pane 
	 */
	private Rectangle generateCell(Cell cell){
		Rectangle rect = new Rectangle();
		rect.setWidth(ApplicationConstants.CELL_WIDTH);
		rect.setHeight(ApplicationConstants.CELL_WIDTH);
		rect.setFill(cell.updatedState);
		return rect;
	}
	
	void setRoot(Group root) {
		this.root = root;
	}

}
