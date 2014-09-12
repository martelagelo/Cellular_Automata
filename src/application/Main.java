package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Main extends Application {

	public static final Integer STAGE_WIDTH = 1100;
	public static final Integer STAGE_HEIGHT = 700;
	
	private ApplicationLoop myGame;

	@Override
	public void start(Stage primaryStage) {
		Pane root = new Pane();
		Scene scene = new Scene(root,STAGE_WIDTH,STAGE_HEIGHT);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		populateStage(primaryStage, scene);
	}

	/**
	 * Creates and displays the game's main scene. Runs the game loop.
	 */
	private void playGame(Stage stage) {
		Scene scene = myGame.init(stage, STAGE_WIDTH, STAGE_HEIGHT);
		populateStage(stage, scene);
		runGameLoop();
	}

	/**
	 * Runs the game loop and the animation that goes along with the game loop.
	 */
	public void runGameLoop() {
		KeyFrame frame = myGame.start();
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	/**
	 * Populates and shows the stage.
	 * 
	 * @param scene
	 *            : The application's current scene.
	 */
	private void populateStage(Stage stage, Scene scene) {
		stage.setTitle("Cookie Fall!");
		stage.setScene(scene);
		stage.show();
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
	private Button createButton(Pane root, String content, int x_Coord, int y_Coord) {
		Button btn = new Button();
		btn.setLayoutX(x_Coord);
		btn.setLayoutY(y_Coord);
		btn.setText(content);
		btn.setStyle("-fx-background-color: #CC9900;");
		root.getChildren().add(btn);
		return btn;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
