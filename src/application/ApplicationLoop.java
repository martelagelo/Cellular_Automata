package application;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ApplicationLoop {

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
		GridPane root = new GridPane();
		Scene myScene = new Scene(root, width, height, Color.WHITE);

		return myScene;
	}
	
	/**
	 * Create the game's frame
	 */
	public KeyFrame start() {
		return new KeyFrame(Duration.millis(1000 / 60), oneFrame);
	}

	private void updateGameLoop() {
		
	}

}
