package application;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public abstract class Cell {

	// variables that should be available to subclasses
	int xPos;
	int yPos;
	Paint currentState;
	Paint updatedState;
	Cell[][] Matrix;
	String gridEdgeType;	

	/**
	 * Every frame, after the updateState is set, then all cells are updated
	 */
	void update(){
		currentState = updatedState;
		updatedState = null;
	}

	/**
	 * Each subclass should know how to set its updatedState
	 * @param i
	 * @param j
	 * @param cellMatrix
	 */
	protected abstract void updateCell(int i, int j, Cell[][] cellMatrix);

	/**
	 * Each class should know how to set its current state
	 * @param s
	 */
	abstract void setCurrentState(String s);

	/**
	 * Setting X and Y start positions happens at generation
	 * @param x
	 */
	void setXPos(int x){
		xPos = x;
	}

	/**
	 * 
	 * @param y
	 */
	void setYPos(int y){
		yPos = y;
	}
	
	/**
	 * 
	 * @param s
	 */
	void setGridEdgeType(String s) {
		gridEdgeType = s;
	}

	/**
	 * 
	 * @param i
	 * @param j
	 * @param color
	 * @return
	 */
	protected ArrayList findCardinalDirectionNeighbors(int i, int j, Color color) {
		ArrayList<Cell> list = new ArrayList<Cell>();
		if (checkBounds(i+1,j)) {
			if (this.Matrix[i + 1][j].currentState == color) {//|| Matrix[i+1][j].updatedState == color) {
				list.add(Matrix[i + 1][j]);
			} 
		}
		if (checkBounds(i - 1,j)) {
			if(this.Matrix[i - 1][j].currentState == color) {//|| Matrix[i-1][j].updatedState == color) {
				list.add(Matrix[i - 1][j]);
			}
		} 
		if (checkBounds(i,j+1)) {
			if (this.Matrix[i][j+1].currentState == color) {//|| Matrix[i][j+1].updatedState == color) {
				list.add(Matrix[i][j+1]);
			}
		} 
		if (checkBounds(i,j - 1)) {
			if (this.Matrix[i][j-1].currentState == color) {//|| Matrix[i][j-1].updatedState == color) {
				list.add(Matrix[i][j - 1]);
			}
		} 		
		return list;
	}

	/**
	 * 
	 * @param i
	 * @param j
	 * @param currentColor
	 * @param updatedColor
	 * @return
	 */
	protected ArrayList findCardinalDirectionNeighbors(int i, int j, Color currentColor, Color updatedColor) {
		ArrayList<Cell> list = new ArrayList<Cell>();
		if (checkBounds(i+1,j)) {
			if ((this.Matrix[i + 1][j].currentState == currentColor && Matrix[i+1][j].updatedState==null) || Matrix[i+1][j].updatedState == updatedColor) {
				list.add(Matrix[i + 1][j]);
			} 
		}
		if (checkBounds(i - 1,j)) {
			if((this.Matrix[i - 1][j].currentState == currentColor && Matrix[i-1][j].updatedState==null) || Matrix[i-1][j].updatedState == updatedColor) {
				list.add(Matrix[i - 1][j]);
			}
		} 
		if (checkBounds(i,j+1)) {
			if ((this.Matrix[i][j+1].currentState == currentColor && Matrix[i][j+1].updatedState==null) || Matrix[i][j+1].updatedState == updatedColor) {
				list.add(Matrix[i][j+1]);
			}
		} 
		if (checkBounds(i,j - 1)) {
			if ((this.Matrix[i][j-1].currentState == currentColor && Matrix[i][j-1].updatedState==null) || Matrix[i][j-1].updatedState == updatedColor) {
				list.add(Matrix[i][j - 1]);
			}
		} 		
		return list;
	}

	/**
	 * 
	 * @param i
	 * @param j
	 * @param color
	 * @return
	 */
	protected ArrayList findSquareNeighbors(int i, int j, Color color){
		ArrayList<Cell> list = new ArrayList<Cell>();
		int[] x = new int[] {-1, 0, 1, -1, 1, -1, 0, 1};
		int[] y = new int[] {-1, -1, -1, 0, 0, 1, 1, 1};
		for(int k = 0; k < x.length; k++){
			if(checkBounds(i + x[k],j + y[k])) {
				if (Matrix[i + x[k]][j + y[k]].currentState == color) {
					list.add(Matrix[i + x[k]][j + y[k]]);
				}
			}
		}
		return list;
	}

	/**
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	private boolean checkBounds(int i, int j) {
		return (i < ApplicationConstants.NUM_OF_COLUMNS && i >= 0 && j < ApplicationConstants.NUM_OF_ROWS && j >= 0);
	}

	/**
	 * 
	 * @param i
	 * @param j
	 * @param color
	 * @return
	 */
	protected ArrayList findCardinalToroidalNeighbors(int i, int j, Color color) {
		ArrayList<Cell> list = findCardinalDirectionNeighbors(i, j, color);
		if (!checkBounds(i+1,j)) {
			if (this.Matrix[0][j].currentState == color) {
				list.add(Matrix[0][j]);
			} 
		}
		if (!checkBounds(i - 1,j)) {
			if(this.Matrix[ApplicationConstants.NUM_OF_COLUMNS - 1][j].currentState == color) {
				list.add(Matrix[ApplicationConstants.NUM_OF_COLUMNS - 1][j]);
			}
		} 
		if (!checkBounds(i,j+1)) {
			if (this.Matrix[i][0].currentState == color) {
				list.add(Matrix[i][0]);
			}
		} 
		if (!checkBounds(i,j - 1)) {
			if (this.Matrix[i][ApplicationConstants.NUM_OF_ROWS - 1].currentState == color) {
				list.add(Matrix[i][ApplicationConstants.NUM_OF_ROWS - 1]);
			}
		} 		
		return list;
	}

	/**
	 * 
	 * @param i
	 * @param j
	 * @param currentColor
	 * @param updatedColor
	 * @return
	 */
	protected ArrayList findCardinalToroidalNeighbors(int i, int j, Color currentColor, Color updatedColor) {
		ArrayList<Cell> list = findCardinalDirectionNeighbors(i, j, currentColor, updatedColor);

		if (!checkBounds(i+1,j)) {
			if ((this.Matrix[0][j].currentState == currentColor && Matrix[0][j].updatedState==null) || Matrix[0][j].updatedState == updatedColor) {
				list.add(Matrix[0][j]);
			} 
		}
		if (!checkBounds(i - 1,j)) {
			if((this.Matrix[ApplicationConstants.NUM_OF_COLUMNS - 1][j].currentState == currentColor && Matrix[ApplicationConstants.NUM_OF_COLUMNS - 1][j].updatedState==null) || Matrix[ApplicationConstants.NUM_OF_COLUMNS - 1][j].updatedState == updatedColor) {
				list.add(Matrix[ApplicationConstants.NUM_OF_COLUMNS - 1][j]);
			}
		} 
		if (!checkBounds(i,j+1)) {
			if ((this.Matrix[i][0].currentState == currentColor && Matrix[i][0].updatedState==null) || Matrix[i][0].updatedState == updatedColor) {
				list.add(Matrix[i][0]);
			}
		} 
		if (!checkBounds(i,j - 1)) {
			if ((this.Matrix[i][ApplicationConstants.NUM_OF_ROWS - 1].currentState == currentColor && Matrix[i][ApplicationConstants.NUM_OF_ROWS - 1].updatedState==null) || Matrix[i][ApplicationConstants.NUM_OF_ROWS - 1].updatedState == updatedColor) {
				list.add(Matrix[i][ApplicationConstants.NUM_OF_ROWS - 1]);
			}
		} 		
		return list;
	}
	
	/**
	 * 
	 * @param i
	 * @param j
	 * @param color
	 * @return
	 */
	protected ArrayList findToroidalSquareNeighbors(int i, int j, Color color){
		ArrayList<Cell> list = findSquareNeighbors(i, j, color);
		int[] x = new int[] {-1, 0, 1, -1, 1, -1, 0, 1};
		int[] y = new int[] {-1, -1, -1, 0, 0, 1, 1, 1};
		for(int k = 0; k < x.length; k++){
			if(checkBounds(i + x[k],j + y[k])) {
				if (Matrix[i + x[k]][j + y[k]].currentState == color) {
					list.add(Matrix[i + x[k]][j + y[k]]);
				}
			}
		}
		return list;
	}

	/**
	 * For printing purposes
	 */
	@Override
	public String toString() {
		return "Cell: " + "\n\tX: " + xPos + "\n\tY: " + yPos + "\n\tState: " + currentState;
	}
}
