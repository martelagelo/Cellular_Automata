package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class Main extends Application {
	
	private ApplicationLoop myGame;
	private Timeline animation;

	@Override
	public void start(Stage primaryStage) {
		Group root = new Group();
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
		Group root = myGame.getRoot();
		
		Button btnStop = createButton("Exit Application", 50, 450, root);
		activateExitAppButton(btnStop);
		
		Label sliderLabel = createLabel("Frame Rate of Application", 1, 50, 600, root);
		Slider slider = createSlider(0, 100, 50, 630, root);
		
		populateStage(stage, scene);
		
		runGameLoop(ApplicationConstants.DEFAULT_FRAME_RATE);
		
		Button btnPauseApp = createButton("Pause Application", 50, 500, root);
		activatePauseAnimationButton(btnPauseApp);
		
		Button btnResumeApp = createButton("Resume Application", 50, 550, root);
		activateResumeAnimationButton(btnResumeApp);
	}

	/**
	 * Runs the game loop and the animation that goes along with the game loop.
	 */
	public void runGameLoop(Double speed) {
		if(animation != null) {
			animation.pause();
		}
		KeyFrame frame = myGame.start(speed);
		animation = new Timeline();
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
	private Button createButton(String content, int x_Coord, int y_Coord, Group root) {
		Button btn = new Button();
		btn.setLayoutX(x_Coord);
		btn.setLayoutY(y_Coord);
		btn.setText(content);
		btn.setStyle("-fx-background-color: #CC9900;");
		root.getChildren().add(btn);
		return btn;
	}
	
	private Slider createSlider(int minValue, int maxValue,  int x_Coord, int y_Coord, Group root){
		Slider slider = new Slider();
		slider.setMin(minValue);
		slider.setMax(maxValue);
		slider.setValue(ApplicationConstants.DEFAULT_FRAME_RATE);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setBlockIncrement(2);
		slider.setLayoutX(x_Coord);
		slider.setLayoutY(y_Coord);
		root.getChildren().add(slider);
		setSliderEventListener(slider);
		return slider;
	}
	
	private Label createLabel(String content, int font_size, int x_Coord, int y_Coord,  Group root) {
		Label label = new Label();
		label.setText(content);
		label.setStyle("-fx-font-size: " + font_size
				+ "em; -fx-background-color: #ffffff");
		label.setLayoutX(x_Coord);
		label.setLayoutY(y_Coord);
		label.setTextFill(Color.BLACK);
		root.getChildren().add(label);
		return label;
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
	
	private void setSliderEventListener(Slider slider) {
		slider.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				runGameLoop(slider.getValue());
			}
		});
	}
	
	private void activatePauseAnimationButton(Button btn){
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				animation.pause();
			}
		});
	}
	
	private void activateResumeAnimationButton(Button btn){
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				animation.play();
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
