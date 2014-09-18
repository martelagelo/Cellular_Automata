package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
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

public class ApplicationLoop {

	private GridPane gridpane;
	private Grid grid = new Grid();
	private Group root;

	/**
	 * Function to do each game frame.
	 */
	private EventHandler<ActionEvent> oneFrame = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent evt) {
			updateGameLoop();
		}
	};

	/**
	 * Creates the group and the scene for the main game play.
	 * 
	 * @param s
	 *            : The application stage.
	 * @param width
	 *            : The pixel width of the application.
	 * @param height
	 *            : The pixel height of the application.
	 * @return: Returns the scene in which the game occurs.
	 */
	public Scene init(Stage s, Integer width, Integer height) {
		root = new Group();
		Scene myScene = new Scene(root, width, height, Color.WHITE);
		grid.setRoot(root);
		gridpane = initializeGridPane(root);
		return myScene;
	}

	/**
	 * Create the game's frame
	 */
	public KeyFrame start(Double frameRate) {
		return new KeyFrame(Duration.millis(8000 / frameRate), oneFrame);
	}

	public void updateGameLoop() {
		gridpane = grid.updateGrid(gridpane);
		System.out.println("Yo\n");
		System.out.println("Mom");

	}
	
	private GridPane initializeGridPane(Group root){
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(5));

		for(int i = 0; i < ApplicationConstants.NUM_OF_COLUMNS; i++) {
			for(int j = 0; j < ApplicationConstants.NUM_OF_ROWS; j++) {
				Rectangle rect = generateCell(Color.WHITE);
				grid.initializeAndPopulateMatrix(i, j, rect.getFill());
				gp.add(rect, i, j,1,1);
			}
		}
		
		root.getChildren().add(gp);
		
		return gp;
	}

	private Rectangle generateCell(Paint color){
		Rectangle rect = new Rectangle();
		rect.setWidth(ApplicationConstants.CELL_WIDTH);
		rect.setHeight(ApplicationConstants.CELL_WIDTH);
		rect.setFill(generateRandomColor());
		return rect;
	}
	
	private Paint generateRandomColor() {
		Random rand = new Random();
		int i = rand.nextInt(100);
		if (i < 10) {
			return Color.RED;
		} else{
			return Color.GREEN;
		}
	}
	
	public Group getRoot(){
		return root;
	}
	
}
