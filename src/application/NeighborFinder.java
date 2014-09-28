package application;

import java.util.HashMap;
import java.util.Map;

public class NeighborFinder {
	
	Cell[][] cellMatrix = new Cell[ApplicationConstants.NUM_OF_COLUMNS][ApplicationConstants.NUM_OF_ROWS];
	Map<Integer, Cell> neighbors = new HashMap<Integer, Cell>();
	

	/**
	 * Creates a map of corner neighbors for a particular cell
	 * @param i: The x position of the particular cell
	 * @param j: The y position of the particular cell
	 * @return: The map of corner neighbors
	 */
	private Map createCornerNeighborsMap(int i, int j) {
		neighbors = new HashMap<Integer, Cell>();
		int[] x = new int[]{-1, 1, -1, 1};
		int[] y = new int[]{-1, -1, 1, 1};
		addCellsToMap(i, j, x, y, neighbors);
		return neighbors;
	}

	/**
	 * Creates a map of cardinal directional neighbors for a particular cell
	 * @param i: The x position of the particular cell
	 * @param j: The y position of the particular cell
	 * @return: The map of cardinal directional neighbors
	 */
	private Map createCardinalNeighborsMap(int i, int j) {
		neighbors = new HashMap<Integer, Cell>();
		int[] x = new int[]{0, 0, -1, 1};
		int[] y = new int[]{-1, 1, 0, 0};
		addCellsToMap(i, j, x, y, neighbors);
		return neighbors;
	}

	/**
	 * Creates a map of square neighbors for a particular cell
	 * @param i: The x position of the particular cell
	 * @param j: The y position of the particular cell
	 * @return: The map of square neighbors
	 */
	private Map createSquareNeighborsMap(int i, int j) {
		neighbors = createCardinalNeighborsMap(i, j);
		neighbors.putAll(createCornerNeighborsMap(i, j));
		return neighbors;
	}

	/**
	 * Creates a map of corner neighbors for a particular cell with toroidal capabilities
	 * @param i: The x position of the particular cell
	 * @param j: The y position of the particular cell
	 * @return: The map of corner neighbors
	 */
	private Map createToroidalCornerNeighborsMap(int i, int j) {
		neighbors = createCornerNeighborsMap(i, j);
		if (!checkBounds(i + 1,j + 1)) {
			if (i == ApplicationConstants.NUM_OF_COLUMNS - 1 && j == ApplicationConstants.NUM_OF_ROWS - 1) {
				neighbors.put(cellMatrix[0][0].cellID, cellMatrix[0][0]);
			}
			else if (i == ApplicationConstants.NUM_OF_COLUMNS - 1 && j != ApplicationConstants.NUM_OF_ROWS - 1) {
				neighbors.put(cellMatrix[0][j+1].cellID, cellMatrix[0][j+1]);
			}
			else if (i != ApplicationConstants.NUM_OF_COLUMNS - 1 && j == ApplicationConstants.NUM_OF_ROWS - 1) {
				neighbors.put(cellMatrix[i+1][0].cellID, cellMatrix[i+1][0]);
			}
		}
		if (!checkBounds(i - 1,j + 1)) {
			if (i == 0 && j == ApplicationConstants.NUM_OF_ROWS - 1) {
				neighbors.put(cellMatrix[ApplicationConstants.NUM_OF_COLUMNS - 1][0].cellID, cellMatrix[ApplicationConstants.NUM_OF_COLUMNS - 1][0]);
			}
			else if (i != 0 && j == ApplicationConstants.NUM_OF_ROWS - 1) {
				neighbors.put(cellMatrix[i-1][0].cellID, cellMatrix[i-1][0]);
			}
			else if (i == 0 && j != ApplicationConstants.NUM_OF_ROWS - 1) {
				neighbors.put(cellMatrix[ApplicationConstants.NUM_OF_COLUMNS - 1][j + 1].cellID, cellMatrix[ApplicationConstants.NUM_OF_COLUMNS - 1][j + 1]);
			}
		}
		if (!checkBounds(i + 1,j - 1)) {
			if (i == ApplicationConstants.NUM_OF_COLUMNS - 1 && j == 0) {
				neighbors.put(cellMatrix[0][ApplicationConstants.NUM_OF_ROWS - 1].cellID, cellMatrix[0][ApplicationConstants.NUM_OF_ROWS - 1]);
			}
			else if (i != ApplicationConstants.NUM_OF_COLUMNS - 1 && j == 0) {
				neighbors.put(cellMatrix[i + 1][ApplicationConstants.NUM_OF_ROWS - 1].cellID, cellMatrix[i + 1][ApplicationConstants.NUM_OF_ROWS - 1]);
			}
			else if (i == ApplicationConstants.NUM_OF_COLUMNS - 1 && j != 0) {
				neighbors.put(cellMatrix[0][j - 1].cellID, cellMatrix[0][j - 1]);
			}
		}
		if (!checkBounds(i - 1,j - 1)) {
			if(i == 0 && j == 0) {
				neighbors.put(cellMatrix[ApplicationConstants.NUM_OF_COLUMNS - 1][ApplicationConstants.NUM_OF_ROWS - 1].cellID, cellMatrix[ApplicationConstants.NUM_OF_COLUMNS - 1][ApplicationConstants.NUM_OF_ROWS - 1]);
			}
			else if (i != 0 && j == 0) {
				neighbors.put(cellMatrix[i - 1][ApplicationConstants.NUM_OF_ROWS - 1].cellID, cellMatrix[i - 1][ApplicationConstants.NUM_OF_ROWS - 1]);
			}
			else if (i == 0 && j != 0) {
				neighbors.put(cellMatrix[ApplicationConstants.NUM_OF_COLUMNS - 1][j - 1].cellID, cellMatrix[ApplicationConstants.NUM_OF_COLUMNS - 1][j - 1]);
			}
		}
		return neighbors;
	}

	/**
	 * Creates a map of cardinal directional neighbors for a particular cell with toroidal capabilities
	 * @param i: The x position of the particular cell
	 * @param j: The y position of the particular cell
	 * @return: The map of cardinal directional neighbors
	 */
	private Map createToroidalCardinalNeighborsMap(int i, int j) {
		neighbors = createCardinalNeighborsMap(i, j);
		if (!checkBounds(i+1,j) || !checkBounds(i - 1,j)) neighbors.put(cellMatrix[findXValue(i)][j].cellID, cellMatrix[findXValue(i)][j]);
		if (!checkBounds(i,j+1) || !checkBounds(i,j - 1)) neighbors.put(cellMatrix[i][findYValue(j)].cellID, cellMatrix[i][findYValue(j)]); 	
		return neighbors;
	}
	
	private int findXValue(int i) {
		if (i == 0) {
			return ApplicationConstants.NUM_OF_COLUMNS - 1;
		}
		else {
			return 0;
		}
	}
	
	private int findYValue(int j) {
		if (j == 0) {
			return ApplicationConstants.NUM_OF_ROWS - 1;
		}
		else {
			return 0;
		}
	}

	/**
	 * Creates a map of square neighbors for a particular cell with toroidal capabilities
	 * @param i: The x position of the particular cell
	 * @param j: The y position of the particular cell
	 * @return: The map of square neighbors
	 */
	private Map createToroidalSquareNeighborsMap(int i, int j) {
		neighbors = createToroidalCardinalNeighborsMap(i, j);
		neighbors.putAll(createToroidalCornerNeighborsMap(i, j));
		return neighbors;
	}

	/**
	 * Creates a map of neighbors based off of how hexagons link (non-toroidal)
	 * @param i: The x position of the particular cell
	 * @param j: The y position of the particular cell
	 * @return: The map of hexagonal neighbors
	 */
	private Map createHexagonalNeighborsMap(int i, int j) {
		neighbors = createCardinalNeighborsMap(i, j);
		int[] x = new int[]{-1, 1};
		int[] y = new int[]{1, -1};
		addCellsToMap(i, j, x, y, neighbors);
		return neighbors;
	}

	/**
	 * Adds cells to a map if not out of bounds
	 * @param i: The x position of the particular cell
	 * @param j: The y position of the particular cell
	 * @param x: An array of x positions 
	 * @param y: An array of y positions
	 * @param map: The map that needs to be added to
	 */
	private void addCellsToMap(int i, int j, int[] x, int[] y, Map map) {
		for(int k = 0; k < x.length; k++) {
			if (checkBounds(i + x[k], j + y[k])) {
				map.put(cellMatrix[i + x[k]][j + y[k]].cellID, cellMatrix[i + x[k]][j + y[k]]);
			}
		}
	}

	/**
	 * Checks to make sure referenced locations are in the bounds of the grid
	 * @param i: The x position of the particular location
	 * @param j: The x position of the particular location
	 * @return
	 */
	private boolean checkBounds(int i, int j) {
		return (i < ApplicationConstants.NUM_OF_COLUMNS && i >= 0 && j < ApplicationConstants.NUM_OF_ROWS && j >= 0);
	}

	/**
	 * Populates the neighbors of each of the cells in the cell matrix
	 */
	public void populateNeighborMaps(CellXMLReader cxr, Cell[][] cellMatrix) {
		this.cellMatrix = cellMatrix;
		for(int j = 0; j < ApplicationConstants.NUM_OF_ROWS; j++) {
			for(int i = 0; i < ApplicationConstants.NUM_OF_COLUMNS; i++) {
				if(cxr.getGridEdgeType().equalsIgnoreCase("toroidal") && cxr.getGridNeighborType().equalsIgnoreCase("corner"))
					this.cellMatrix[i][j].neighbors = createToroidalCornerNeighborsMap(i, j);
				else if(cxr.getGridEdgeType().equalsIgnoreCase("toroidal") && cxr.getGridNeighborType().equalsIgnoreCase("cardinal"))
					this.cellMatrix[i][j].neighbors = createToroidalCardinalNeighborsMap(i, j);
				else if(cxr.getGridEdgeType().equalsIgnoreCase("toroidal") && cxr.getGridNeighborType().equalsIgnoreCase("square"))
					this.cellMatrix[i][j].neighbors = createToroidalSquareNeighborsMap(i, j);
				else if(cxr.getGridEdgeType().equalsIgnoreCase("finite") && cxr.getGridNeighborType().equalsIgnoreCase("corner"))
					this.cellMatrix[i][j].neighbors = createCornerNeighborsMap(i, j);
				else if(cxr.getGridEdgeType().equalsIgnoreCase("finite") && cxr.getGridNeighborType().equalsIgnoreCase("cardinal"))
					this.cellMatrix[i][j].neighbors = createCardinalNeighborsMap(i, j);
				else if(cxr.getGridEdgeType().equalsIgnoreCase("finite") && cxr.getGridNeighborType().equalsIgnoreCase("square"))
					this.cellMatrix[i][j].neighbors = createSquareNeighborsMap(i, j);
				else if(cxr.getGridLocationShape().equalsIgnoreCase("hexagonal"))
					this.cellMatrix[i][j].neighbors = createHexagonalNeighborsMap(i, j);
				else
					this.cellMatrix[i][j].neighbors = createSquareNeighborsMap(i, j);
			}	
		}
	}
}
