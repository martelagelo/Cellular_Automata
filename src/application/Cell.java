// This entire file is part of my masterpiece
// Pranava K. Raparla

package application;

import java.util.*;
import javafx.scene.paint.*;

/**
 * Version 2
 * Date: 9/29/2014
 * 
 * @author Michael Deng
 * @author Pranava Raparla
 * @author David Zhang
 *
 */
public abstract class Cell {

	// variables that should be available to subclasses
	public int xPos;
	public int yPos;
	public int cellID;
	protected Paint currentState;
	protected Paint updatedState;
	protected double threshold;
	protected String gridEdgeType;
	protected String gridLocationShape;
	protected List<Color> cellColors;
	public Map<Integer, Cell> neighbors;
	
	/**
	 * Constructor for Cell class that should be the same for all subclasses.
	 * @param x: The x position of the cell
	 * @param y: The y position of the cell
	 * @param current: The current state of the cell
	 * @param updated: The updated state of the cell
	 * @param thresh: The cell's threshold for neighbors
	 * @param edge: The boundary type of the cell
	 * @param shape: The shape of the individual cell
	 */
	public Cell(int x, int y, Paint current, Paint updated, double thresh, String edge, String shape, List<Color> colors) {
		xPos = x;
		yPos = y;
		currentState = current;
		updatedState = updated;
		threshold = thresh;
		gridEdgeType = edge;
		gridLocationShape = shape;
		cellColors = colors;
		neighbors = new HashMap<Integer, Cell>();
	}
		
	/**
	 * Updates cell from currentState to updatedState.
	 */
	public void update() {
		currentState = updatedState;
		updatedState = null;
	}
	
	/**
	 * Queues the next state. Each subclass should know how to set its own updatedState.
	 */
	protected abstract void setUpdatedState();
	
	/**
	 * 
	 * @return currentState
	 */
	public Color getCurrentState() {
		return (Color) currentState;
	}
	
	/**
	 * 
	 * @return updatedState
	 */
	public Color getUpdatedState() {
		return (Color) updatedState;
	}

	/**
	 * Setting X and Y start positions happens at generation
	 * @param x: The x position of the current cell
	 */
	public int getXPos() {
		return xPos;
	}

	/**
	 * Getting X and Y start positions happens at generation
	 * @param y: The y position of the current cell
	 */
	public int getYPos() {
		return yPos;
	}
	
	/**
	 * Setting X and Y start positions happens at generation
	 * @param x: The x position of the current cell
	 */
	public void setXPos(int x) {
		xPos = x;
	}

	/**
	 * Setting X and Y start positions happens at generation
	 * @param y: The y position of the current cell
	 */
	public void setYPos(int y) {
		yPos = y;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getCellID() {
		return cellID;
	}
	
	/**
	 * Setting the ID for the cell from within the Grid class
	 * @param id: The ID of the cell in the Grid
	 */
	public void setCellID(int id) {
		cellID = id;
	}
	
	/**
	 * Counts up the neighbors of the current cell with a certain color
	 * @param color: The color we want to find
	 * @return: A list of cells that have that certain color
	 */
	protected List findWantedNeighbors(Color color) {
		List<Cell> list = new ArrayList<Cell>();
		for (Integer key : neighbors.keySet())
			if (neighbors.get(key).currentState == color)
				list.add(neighbors.get(key));
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
		for (Integer key : neighbors.keySet())
			if ((neighbors.get(key).getCurrentState() == currentColor && neighbors.get(key).getUpdatedState() == null) || neighbors.get(key).getUpdatedState() == updatedColor)
				list.add(neighbors.get(key));
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
		for (Integer key : otherCell.neighbors.keySet())
			if ((otherCell.neighbors.get(key).getCurrentState() == currentColor && otherCell.neighbors.get(key).getUpdatedState() == null) || otherCell.neighbors.get(key).getUpdatedState() == updatedColor)
				list.add(otherCell.neighbors.get(key));
		return list;
	}

	/**
	 * Overrides toString() method to help with printing.
	 */
	@Override
	public String toString() {
		return "Cell: " + "\n\tX: " + xPos + "\n\tY: " + yPos + "\n\tState: " + currentState;
	}

}
