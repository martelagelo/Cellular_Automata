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

	//For now, 3 is the limit for both.


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
	 * @param i
	 * @param j
	 * @param color
	 * @return
	 */
	protected ArrayList findCardinalNeighbors(int i, int j, Color color) {
		ArrayList<Cell> list = new ArrayList<Cell>();
		if (checkBounds(i+1,j)) {
			if (this.Matrix[i + 1][j].currentState == color) {
				list.add(Matrix[i + 1][j]);
			} 
		}
		if (checkBounds(i - 1,j)) {
			if(this.Matrix[i - 1][j].currentState == color) {
				list.add(Matrix[i - 1][j]);
			}
		} 
		if (checkBounds(i,j+1)) {
			if (this.Matrix[i][j+1].currentState == color) {
				list.add(Matrix[i][j+1]);
			}
		} 
		if (checkBounds(i,j - 1)) {
			if (this.Matrix[i][j-1].currentState == color) {
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
	protected ArrayList findCardinalNeighbors(int i, int j, Color currentColor, Color updatedColor) {
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
	protected ArrayList findCornerNeighbors(int i, int j, Color color){
		ArrayList<Cell> list = new ArrayList<Cell>();
		int[] x = new int[] {-1, 1, -1, 1};
		int[] y = new int[] {-1, -1, 1, 1};
		for(int k = 0; k < x.length; k++){
			if(checkBounds(i + x[k],j + y[k])) {
				if (Matrix[i + x[k]][j + y[k]].currentState == color) {
					list.add(Matrix[i + x[k]][j + y[k]]);
				}
			}
		}
		return list;
	}


	protected ArrayList findSquareNeighbors(int i, int j, Color color) {
		ArrayList<Cell> list = findCardinalNeighbors(i, j, color);
		list.addAll(findCornerNeighbors(i, j, color));
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
	protected ArrayList findToroidalCardinalNeighbors(int i, int j, Color color) {
		ArrayList<Cell> list = findCardinalNeighbors(i, j, color);
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
	protected ArrayList findToroidalCardinalNeighbors(int i, int j, Color currentColor, Color updatedColor) {
		ArrayList<Cell> list = findCardinalNeighbors(i, j, currentColor, updatedColor);

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
	protected ArrayList findToroidalCornerNeighbors(int i, int j, Color color){
		ArrayList<Cell> list = findCornerNeighbors(i, j, color);
		if (!checkBounds(i + 1,j + 1)) {
			if (i == ApplicationConstants.NUM_OF_COLUMNS - 1 && j == ApplicationConstants.NUM_OF_ROWS - 1) {
				if (this.Matrix[0][0].currentState == color) {
					list.add(Matrix[0][0]);
				}
			}
			else if (i == ApplicationConstants.NUM_OF_COLUMNS - 1 && j != ApplicationConstants.NUM_OF_ROWS - 1) {
				if (this.Matrix[0][j+1].currentState == color) {
					list.add(Matrix[0][j+1]);
				}
			}
			else if (i != ApplicationConstants.NUM_OF_COLUMNS - 1 && j == ApplicationConstants.NUM_OF_ROWS - 1){
				if (this.Matrix[i+1][0].currentState == color) {
					list.add(Matrix[i+1][0]);
				}
			}
		}
		if (!checkBounds(i - 1,j + 1)) {
			if (i == 0 && j == ApplicationConstants.NUM_OF_ROWS - 1) {
				if(this.Matrix[ApplicationConstants.NUM_OF_COLUMNS - 1][0].currentState == color) {
					list.add(Matrix[ApplicationConstants.NUM_OF_COLUMNS - 1][0]);
				}
			}
			else if (i != 0 && j == ApplicationConstants.NUM_OF_ROWS - 1) {
				if(this.Matrix[i-1][0].currentState == color) {
					list.add(Matrix[i-1][0]);
				}
			}
			else if (i == 0 && j != ApplicationConstants.NUM_OF_ROWS - 1) {
				if(this.Matrix[ApplicationConstants.NUM_OF_COLUMNS - 1][j + 1].currentState == color) {
					list.add(Matrix[ApplicationConstants.NUM_OF_COLUMNS - 1][j + 1]);
				}
			}
		} 
		if (!checkBounds(i + 1,j - 1)) {
			if (i == ApplicationConstants.NUM_OF_COLUMNS - 1 && j == 0) {
				if (this.Matrix[0][ApplicationConstants.NUM_OF_ROWS - 1].currentState == color) {
					list.add(Matrix[0][ApplicationConstants.NUM_OF_ROWS - 1]);
				}
			}
			else if (i != ApplicationConstants.NUM_OF_COLUMNS - 1 && j == 0) {
				if (this.Matrix[i + 1][ApplicationConstants.NUM_OF_ROWS - 1].currentState == color) {
					list.add(Matrix[i + 1][ApplicationConstants.NUM_OF_ROWS - 1]);
				}
			}
			else if (i == ApplicationConstants.NUM_OF_COLUMNS - 1 && j != 0) {
				if (this.Matrix[0][j - 1].currentState == color) {
					list.add(Matrix[0][j - 1]);
				}
			}
		} 
		if (!checkBounds(i - 1,j - 1)) {
			if(i == 0 && j == 0) {
				if (this.Matrix[ApplicationConstants.NUM_OF_COLUMNS - 1][ApplicationConstants.NUM_OF_ROWS - 1].currentState == color) {
					list.add(Matrix[ApplicationConstants.NUM_OF_COLUMNS - 1][ApplicationConstants.NUM_OF_ROWS - 1]);
				}
			}
			else if (i != 0 && j == 0) {
				if (this.Matrix[i - 1][ApplicationConstants.NUM_OF_ROWS - 1].currentState == color) {
					list.add(Matrix[i - 1][ApplicationConstants.NUM_OF_ROWS - 1]);
				}
			}
			else if (i == 0 && j != 0) {
				if (this.Matrix[ApplicationConstants.NUM_OF_COLUMNS - 1][j - 1].currentState == color) {
					list.add(Matrix[ApplicationConstants.NUM_OF_COLUMNS - 1][j - 1]);
				}
			}
		} 	
		return list;
	}

	protected ArrayList findToroidalSquareNeighbors(int i, int j, Color color) {
		ArrayList<Cell> list = findToroidalCardinalNeighbors(i, j, color);
		list.addAll(findToroidalCornerNeighbors(i, j, color));
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
