package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Version 1
 * Date: 9/14/2014
 * 
 * @author Michael Deng
 * @author Pranava Raparla
 * @author David Zhang
 *
 */
public class ApplicationLoop {

	private GridPane gridpane;
	private Grid grid = new Grid();
	private Group root;
	private CellXMLReader cellXMLReader;
	private Timeline animation;

	XYChart.Series series;
	public boolean goToErrorPage = false;
	private int currentFrameCount;


	/**
	 * Function to do each game frame
	 */
	private EventHandler<ActionEvent> oneFrame = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent evt) {
			updateGameLoop();
		}
	};

	/**
	 * Creates the group and the scene for the main game play
	 * 
	 * @param s
	 *            : The application stage
	 * @param width
	 *            : The pixel width of the application
	 * @param height
	 *            : The pixel height of the application
	 * @return: Returns the scene in which the game occurs
	 */
	public Scene init(Stage s, Integer width, Integer height, CellXMLReader cxr) {
		currentFrameCount = 0;
		root = new Group();
		Scene myScene = new Scene(root, width, height, Color.WHITE);
		grid.setRoot(root);
		cellXMLReader = cxr;
		gridpane = initializeGridPane(root);
		editGrid(gridpane.getChildren());
		return myScene;
	}

	/**
	 * Create the game's frame
	 */
	public KeyFrame start(Double frameRate) {
		return new KeyFrame(Duration.millis(8000 / frameRate), oneFrame);
	}

	/**
	 * Updates the grid
	 */
	public void updateGameLoop() {
		// GameType.updateGame(grid);
		grid.updateGrid(gridpane);
		currentFrameCount++;
		addPointsToLineChart(currentFrameCount, countNumberOfCertainColorSpaces(Color.BLACK));
	}

	/**
	 * Creates what the initial GridPane and cellMatrix look like
	 * @param root: The stack all the modules of the page go on
	 * @return: The newly created GirdPane
	 */
	private GridPane initializeGridPane(Group root){		
		// Set up GridPane
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(5));

		// Loop through entire Grid and randomly populate cells
		for(int i = 0; i < ApplicationConstants.NUM_OF_COLUMNS; i++) {
			for(int j = 0; j < ApplicationConstants.NUM_OF_ROWS; j++) {
				Rectangle rect = generateCell(Color.WHITE);
				//				Cell cell = cellXMLReader.checkModelTypeAndInitializeCell();
				//				grid.initializeAndPopulateMatrix(i, j, rect.getFill(), cell);
				grid.initializeAndPopulateMatrix(i, j, rect.getFill());
				gp.add(rect, i, j,1,1);
			}
		}

		// Loop through entire list of cells read in from XML file and add to grid
		//		for(Cell cell: cellXMLReader.getCellList()) {
		//			Rectangle rect = generateCell(cell.currentState);
		//			grid.initializeAndPopulateMatrix(cell);
		//			gp.add(rect, cell.xPos, cell.yPos,1,1);
		//		}
		//		

		root.getChildren().add(gp);
		return gp;
	}

	/**
	 * Generates a cell in the object form of a rectangle
	 * @param color: The color of the cell
	 * @return: The newly created rectangle for the grid pane
	 */
	private Rectangle generateCell(Paint color){
		Rectangle rect = new Rectangle();
		rect.setWidth(ApplicationConstants.CELL_WIDTH);
		rect.setHeight(ApplicationConstants.CELL_WIDTH);
		rect.setFill(generateRandomColor());
		return rect;
	}

	//	private Paint generateRandomColor() {
	//		Random rand = new Random();
	//		int i = rand.nextInt(100);
	//		if (i < 45) {
	//			return Color.RED;
	//		} else if (i > 55) {
	//			return Color.BLUE;
	//		} else{
	//			return Color.WHITE;
	//		}
	//	}

//		private Paint generateRandomColor() {
//			Random rand = new Random();
//			int i = rand.nextInt(100);
//			if (i < 45) {
//				return Color.RED;
//			} else if (i > 55) {
//				return Color.BLUE;
//			} else{
//				return Color.WHITE;
//			}
//		}
	
		private Paint generateRandomColor() {
			Random rand = new Random();
			int i = rand.nextInt(100);
			if (i < 20) {
				return Color.BLACK;
			} else {
				return Color.WHITE;
			}
		}

	//	private Paint generateRandomColor() {
	//		Random rand = new Random();
	//		int i = rand.nextInt(100);
	//		if (i > 90){
	//			return Color.ORANGE;
	//		} else {
	//			return Color.GREEN;
	//		}
	//	}

	//	private Paint generateRandomColor() {
	//		Random rand = new Random();
	//		int i = rand.nextInt(100);
	//		if (i < 20) {
	//			return Color.GREEN;
	//		} else if (i > 92){
	//			return Color.ORANGE;
	//		} else {
	//			return Color.BLUE;
	//		}
	//	}

//	private Paint generateRandomColor() {
//		Random rand = new Random();
//		int i = rand.nextInt(100);
//		if (i < 10) {
//			return Color.RED;
//		} else{
//			return Color.GREEN;
//		}
//	}

	//	private Paint generateRandomColor() {
	//		Random rand = new Random();
	//		int i = rand.nextInt(100);
	//		if (i < 10) {
	//			return Color.RED;
	//		} else{
	//			return Color.GREEN;
	//		}
	//	}

	/**
	 * Gets the root of the current scene
	 * @return: The root of the main game's scene
	 */
	public Group getRoot(){
		return root;
	}

	/**
	 * Gives the linechart the capacity to have data inputted
	 * @param lineChart: The physical graph
	 */
	public void populateLineChart(LineChart lineChart){
		series = new XYChart.Series();
		lineChart.getData().add(series);
	}

	/**
	 * Adds points to the lineChart once per frame
	 * @param XValue: The current frame
	 * @param YValue: The number of a certain type of blocks
	 */
	private void addPointsToLineChart(int XValue, int YValue) {
		series.getData().add(new XYChart.Data(XValue, YValue));
	}

	/**
	 * Counts the number of blocks of a specified color in the grid
	 * @param color: The specified color the program wants to check
	 * @return: The number of certain colored blocks
	 */
	private Integer countNumberOfCertainColorSpaces(Color color){
		int counter = 0;
		ObservableList<Node> list = gridpane.getChildren();
		for(Node r : list) {
			Rectangle rect = (Rectangle) r;
			if (rect.getFill() == color) counter++;
		}
		return counter;
	}
	
	/**
	 * Allows the grid to be editable when the game is paused
	 */
	private void editGrid(ObservableList<Node> list){
		for(Node r : list) {
			r.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event){
					if (ApplicationConstants.gridEditable) {
						grid.changeCellState(list.get((int) (((r.getLayoutX()-5) / ApplicationConstants.CELL_WIDTH) * ApplicationConstants.NUM_OF_ROWS + ((r.getLayoutY()-5) / ApplicationConstants.CELL_WIDTH))));
					}
				}
			});
		}
	}
}
