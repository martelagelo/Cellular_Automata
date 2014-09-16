package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	
	private ApplicationLoop myGame;

	@Override
	public void start(Stage primaryStage) {
		Pane root = new Pane();
		myGame = new ApplicationLoop();
		Scene scene = new Scene(root,ApplicationConstants.STAGE_WIDTH,ApplicationConstants.STAGE_HEIGHT);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		Button btnStart = createButton("Start Simulation", 50, 500, root);
		activateStartButton(btnStart, primaryStage);
		
		Button btnExit = createButton("Exit Application", 50, 550, root);
		activateExitAppButton(btnExit);
		
		Button btnImportXML = createButton("Import an XML File", 50, 600, root);
		activateImportXMLButton(btnImportXML);
		
		populateStage(primaryStage, scene);
	}

	/**
	 * Creates and displays the game's main scene. Runs the game loop.
	 */
	private void playGame(Stage stage) {
		Scene scene = myGame.init(stage, ApplicationConstants.STAGE_WIDTH, ApplicationConstants.STAGE_HEIGHT);
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
		stage.setTitle("Cell Automata");
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
	private Button createButton(String content, int x_Coord, int y_Coord, Pane root) {
		Button btn = new Button();
		btn.setLayoutX(x_Coord);
		btn.setLayoutY(y_Coord);
		btn.setText(content);
		btn.setStyle("-fx-background-color: #CC9900;");
		root.getChildren().add(btn);
		return btn;
	}
	
	/**
	 * Creates an event handler that launches the game on button click.
	 * 
	 * @param btn
	 *            : The button that is clicked to launch the event.
	 */
	public void activateStartButton(Button btn, Stage stage) {
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				playGame(stage);
				System.out.println("Start Button Pressed");
			}
		});
	}
	
	/**
	 * Creates an event handler than exits the application on button click.
	 * 
	 * @param btn
	 *            : The button that is clicked to launch the event.
	 */
	public void activateExitAppButton(Button btn) {
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});
	}
	
	public void activateImportXMLButton(Button btn) {
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//TODO
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
