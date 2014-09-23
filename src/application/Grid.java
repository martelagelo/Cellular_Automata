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

/**
 * Version 1
 * Date: 9/14/2014
 * 
 * @author Michael Deng
 * @author Pranava Raparla
 * @author David Zhang
 *
 */
public class Grid {

	Cell[][] cellMatrix = new Cell[ApplicationConstants.NUM_OF_COLUMNS][ApplicationConstants.NUM_OF_ROWS];
	private Group root;


	/**
	 * Updates the cell matrix and the gridpane
	 * @param gridpane: The physical grid that displays on the application
	 */
	public void updateGrid(GridPane gridpane){
		updateCellMatrix();
		repopulateGridPane(gridpane);
	}

	/**
	 * Populates the values in the individual cells in the cellMatrix
	 * @param i: The x position of the cell
	 * @param j: The y position of the cell
	 * @param initialColor: The initial color of the cell
	 */
	public void initializeAndPopulateMatrix(int i, int j, Paint initialColor){
		cellMatrix[i][j] = new GameOfLifeCell();
		cellMatrix[i][j].xPos = i;
		cellMatrix[i][j].yPos = j;
		cellMatrix[i][j].currentState = initialColor; //Some value that will be inputed from the XML file.
		cellMatrix[i][j].updatedState = cellMatrix[i][j].currentState;
	}

	/**
	 * Populates the values in the individual cells in the cellMatrix
	 * @param i: The x position of the cell
	 * @param j: The y position of the cell
	 * @param initialColor: The initial color of the cell
	 */
	public void initializeAndPopulateMatrix(int i, int j, Paint initialColor, Cell cell){
		cellMatrix[i][j] = cell;
		cellMatrix[i][j].currentState = initialColor; //Some value that will be inputed from the XML file.
	}

	/**
	 * Populates the values in the individual cell of the cellMatrix
	 * @param cell: The cell to populate the CellMatrix cell
	 */
	public void initializeAndPopulateMatrix(Cell cell){
		cellMatrix[cell.xPos][cell.yPos] = cell;
	}


	/**
	 * Updates the states of the cells in the cell matrix
	 */
	private void updateCellMatrix(){
		for(int j = 0; j < ApplicationConstants.NUM_OF_ROWS; j++) {
			for(int i = 0; i < ApplicationConstants.NUM_OF_COLUMNS; i++) {
				cellMatrix[i][j].updateCell(i, j, cellMatrix);
			}
		}
	}

	/**
	 * Repopulates the gridpane with the updatedCells from the cell Matrix
	 * @param gridpane: The physical grid that displays on the application
	 */
	private void repopulateGridPane(GridPane gridpane){
		ObservableList<Node> list = gridpane.getChildren();
		for(int i = 0; i < ApplicationConstants.NUM_OF_COLUMNS; i++) {
			for(int j = 0; j < ApplicationConstants.NUM_OF_ROWS; j++) {
				cellMatrix[i][j].currentState = cellMatrix[i][j].updatedState;
				cellMatrix[i][j].updatedState = null;
				Rectangle r = (Rectangle) list.get(i*ApplicationConstants.NUM_OF_ROWS + j);
				r.setFill(cellMatrix[i][j].currentState);
			}	
		}	
	}

	/**
	 * Grabs the root value from the application loop
	 * @param root: The stack that holds a pages modules
	 */
	void setRoot(Group root) {
		this.root = root;
	}

	public void changeCellState(Node node) {
		int i = (int) ((node.getLayoutX() - 5)/ApplicationConstants.CELL_WIDTH);
		int j = (int) ((node.getLayoutY() - 5)/ApplicationConstants.CELL_WIDTH);
		if (cellMatrix[i][j].currentState == Color.BLACK) { 
			cellMatrix[i][j].currentState = Color.WHITE;
			cellMatrix[i][j].updatedState = Color.WHITE;
		} else {
			cellMatrix[i][j].currentState = Color.BLACK;
			cellMatrix[i][j].updatedState = Color.BLACK;
		}
		Rectangle r = (Rectangle) node;
		r.setFill(cellMatrix[i][j].currentState);
	}

}
