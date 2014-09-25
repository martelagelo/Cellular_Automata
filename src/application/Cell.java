package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Version 1
 * Date: 9/14/2014
 * 
 * @author Michael Deng
 * @author Pranava Raparla
 * @author David Zhang
 *
 */
public abstract class Cell {

	// variables that should be available to subclasses
	int xPos;
	int yPos;
	Paint currentState;
	Paint updatedState;
	double threshold;
	String gridEdgeType;
	String gridLocationShape;
	Cell[][] Matrix;
	Map<Integer, Cell> neighbors = new HashMap<Integer, Cell>();
	int cellID;


	/**
	 * Updates the current cell
	 * @param i: The x position of the current cell
	 * @param j: The y position of the current cell
	 * @param cellMatrix: The cell matrix holding all the cells
	 */
	protected abstract void updateCell(int i, int j, Cell[][] cellMatrix);
	
	/**
	 * Updates the current cell
	 * @param i: The x position of the current cell
	 * @param j: The y position of the current cell
	 */
	protected abstract void updateCell(int i, int j);

	/**
	 * Sets the current state of the current cell
	 * @param s: The string that decides the state
	 */
	abstract void setCurrentState(String s);

	/**
	 * Setting X and Y start positions happens at generation
	 * @param x: The x position of the current cell
	 */
	void setXPos(int x){
		xPos = x;
	}

	/**
	 * Setting X and Y start positions happens at generation
	 * @param y: The y position of the current cell
	 */
	void setYPos(int y){
		yPos = y;
	}
	/**
	 * Called upon creation in XML reader
	 * @param num: The threshold value 
	 */
	public void setThreshold(double num){
		threshold = num;
	}
	
	/**
	 * Sets the edge of grid
	 * @param s: The string that sets the edge
	 */
	void setGridEdgeType(String s) {
		gridEdgeType = s;
	}
	
	/**
	 * Sets the grid location shape
	 * @param s: The string that sets the shape
	 */
	void setGridLocationShape(String s) {
		gridLocationShape = s;
	}

	/**
	 * Counts up the neighbors of the current cell with a certain color
	 * @param color: The color we want to find
	 * @return: A list of cells that have that certain color
	 */
	protected List findWantedNeighbors(Color color) {
		List<Cell> list = new ArrayList<Cell>();
		for (Integer key : neighbors.keySet()) {
			if (neighbors.get(key).currentState == color) {
				list.add(neighbors.get(key));
			}
		}
		return list;
	}
	
	/**
	 * Counts up the neighbors of the current cell with a certain color
	 * @param currentColor: The current state of the cell
	 * @param updatedColor: The updated state of the cell
	 * @return: A list of cells that have that certain color
	 */
	protected List findWantedNeighbors(Color currentColor, Color updatedColor) {
		List<Cell> list = new ArrayList<Cell>();
		for (Integer key : neighbors.keySet()) {
			if ((neighbors.get(key).currentState == currentColor && neighbors.get(key).updatedState == null) || neighbors.get(key).updatedState == updatedColor) {
				list.add(neighbors.get(key));
			}
		}
		return list;
	}
	
	/**
	 * Counts up the neighbors of a referenced cell with a certain color
	 * @param otherCell: The cell being referenced
	 * @param currentColor: The current state of the cell
	 * @param updatedColor: The updated state of the cell
	 * @return: A list of cells that have that certain color
	 */
	protected List findWantedNeighbors(Cell otherCell, Color currentColor, Color updatedColor) {
		List<Cell> list = new ArrayList<Cell>();
		for (Integer key : otherCell.neighbors.keySet()) {
			if ((otherCell.neighbors.get(key).currentState == currentColor && otherCell.neighbors.get(key).updatedState == null) || otherCell.neighbors.get(key).updatedState == updatedColor) {
				list.add(otherCell.neighbors.get(key));
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
