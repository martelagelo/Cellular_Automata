package application;

import java.util.ArrayList;

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
		Group root = new Group();
		gridpane = new GridPane();
		
		Scene myScene = new Scene(gridpane, width, height, Color.WHITE);
		gridpane.setPadding(new Insets(5));

		for(int i = 0; i < ApplicationConstants.NUM_OF_COLUMNS; i++) {
			for(int j = 0; j < ApplicationConstants.NUM_OF_ROWS; j++) {
				Rectangle rect = generateCell(Color.WHITE);
				gridpane.add(rect, i, j,1,1);
			}
		}
		gridpane.setGridLinesVisible(true);
		
		root.getChildren().add(gridpane);
		
		Button btnStop = createButton("Stop Application", 50, 600, root);

		return myScene;
	}

	/**
	 * Create the game's frame
	 */
	public KeyFrame start() {
		return new KeyFrame(Duration.millis(1000 / 1), oneFrame);
	}

	private void updateGameLoop() {
		grid.updateGrid(gridpane);
		//System.out.println(gridpane.getChildren().get(2));
		//Platform.exit();
	}

	private Rectangle generateCell(Paint color){
		Rectangle rect = new Rectangle();
		rect.setWidth(ApplicationConstants.CELL_WIDTH);
		rect.setHeight(ApplicationConstants.CELL_WIDTH);
		rect.setFill(color);;
		return rect;
	}
	
	/**
	 * Creates a button.
	 * 
	 * @param content
	 *            : What the button says.
	 * @param x_Coord
	 *            : The x position of the button on the application.
	 * @param y_Coord
	 *            : The y position of the button on the application.
	 * @return: Returns the newly created button.
	 */
	private Button createButton(String content, int x_Coord, int y_Coord, Group root) {
		Button btn = new Button();
		btn.setLayoutX(x_Coord);
		btn.setLayoutY(y_Coord);
		btn.setText(content);
		btn.setStyle("-fx-background-color: #CC9900;");
		root.getChildren().add(btn);
		return btn;
	}

	

}
