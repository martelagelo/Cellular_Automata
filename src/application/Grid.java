package application;

import java.util.Random;

import org.w3c.dom.NodeList;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


public class Grid {
	
	Cell[][] cellMatrix = new Cell[ApplicationConstants.NUM_OF_COLUMNS][ApplicationConstants.NUM_OF_ROWS];
	private Group root;
	
	public void updateGrid(GridPane gridpane){
		updateCellMatrix();
		repopulateGridPane(gridpane);
	}
	
	/**
	 * 
	 * @param i
	 * @param j
	 * @param initialColor
	 */
	public void initializeAndPopulateMatrix(int i, int j, Paint initialColor){
		cellMatrix[i][j] = new FireCell();
		cellMatrix[i][j].currentState = initialColor; //Some value that will be inputed from the XML file.
	}
	
	private void updateCellMatrix(){
		for(int i = 0; i < ApplicationConstants.NUM_OF_COLUMNS; i++) {
			for(int j = 0; j < ApplicationConstants.NUM_OF_ROWS; j++) {
				cellMatrix[i][j].updateCell(i, j, cellMatrix);
			}
		}
	}
	
	private void repopulateGridPane(GridPane gridpane){
		ObservableList<Node> list = gridpane.getChildren();
		for(int i = 0; i < ApplicationConstants.NUM_OF_COLUMNS; i++) {
			for(int j = 0; j < ApplicationConstants.NUM_OF_ROWS; j++) {
				cellMatrix[i][j].currentState = cellMatrix[i][j].updatedState;
				Rectangle r = (Rectangle) list.get(i*ApplicationConstants.NUM_OF_ROWS + j);
				r.setFill(cellMatrix[i][j].currentState);
			}	
		}	
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
