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
	private Stage primaryStage;
	private Timeline animation;
	
	/**
	 * 
	 */
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		Group root = new Group();
		myGame = new ApplicationLoop();
		Scene scene = new Scene(root,ApplicationConstants.STAGE_WIDTH,ApplicationConstants.STAGE_HEIGHT);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		populateStartPageModules(root);
		populateStage(primaryStage, scene);
	}

	/**
	 * Creates and displays the game's main scene. Runs the game loop.
	 */
	private void playGame(Stage stage) {
		Scene scene = myGame.init(stage, ApplicationConstants.STAGE_WIDTH, ApplicationConstants.STAGE_HEIGHT);
		Group root = myGame.getRoot();
		populateGridPageModules(root);
		populateStage(stage, scene);
		runGameLoop(ApplicationConstants.DEFAULT_FRAME_RATE);

	}

	/**
	 * Runs the game loop and the animation that goes along with the game loop.
	 */
	private void runGameLoop(Double speed) {
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
	 * 
	 */
	private void activateErrorPage(){
		Group root = new Group();
		Scene scene = new Scene(root,ApplicationConstants.STAGE_WIDTH,ApplicationConstants.STAGE_HEIGHT);
		populateErrorPageModules(root);
		populateStage(primaryStage, scene);
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
	 * Create modules for the start page
	 * @param root: The stack that holds all scenes modules
	 */
	private void populateStartPageModules(Group root) {
		createTitleLabel(root);
		createNameLabel(root);
		createStartGameButton(root);
		createExitButton(root);
		createImportXMLButton(root);
	}
	
	/**
	 * Initiates all the modules to be displayed 
	 * @param root: the stack that holds all scenes modules
	 */
	private void populateGridPageModules(Group root) {
		createExitButton(root);
		createPauseButton(root);
		createResumeButton(root);
		createStepThroughButton(root);
		createSliderLabel(root);
		createSlider(root);
		createReturnToStartButton(root);
	}
	
	/**
	 *  creates the error display
	 * @param root: the stack that holds all scenes modules
	 */
	private void populateErrorPageModules(Group root) {
		createErrorMessage(root);
		createExitButton(root);
		createReturnToStartButton(root);
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
	
	/**
	 * Method to create the slider for animation speed
	 * @param minValue: smallest value of slider
	 * @param maxValue: largest value of slider
	 * @param x_Coord: x position of slider
	 * @param y_Coord: y position of slider
	 * @param root: the stack that holds all scenes modules
	 * @return
	 */
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
	
	/**
	 * Creates a label
	 * @param content: The text that is to be displayed 
	 * @param font_size: Font size
	 * @param x_Coord: x position of label
	 * @param y_Coord: y position of label
	 * @param root:the stack that holds all scenes modules
	 * @return
	 */
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
	 * Method for creating the start button 
	 * @param root: the stack that holds all scenes modules
	 */
	private void createStartGameButton(Group root){
		Button btnStart = createButton("Start Simulation", 50, 450, root);
		activateStartButton(btnStart, primaryStage);
	}
	
	/**
	 * Method for creating the exit button
	 * @param root: the stack that holds all scenes modules
	 */
	private void createExitButton(Group root) {
		Button btnExit = createButton("Exit Application", 50, 500, root);
		activateExitAppButton(btnExit);
	}
	
	/**
	 * Method for creating button to import XML file
	 * @param root: the stack that holds all scenes modules
	 */
	private void createImportXMLButton(Group root) {
		Button btnImportXML = createButton("Import an XML File", 50, 550, root);
		activateImportXMLButton(btnImportXML);
	}
	
	/**
	 * Method for creating button to return to start menu
	 * @param root: the stack that holds all scenes modules
	 */
	private void createReturnToStartButton(Group root) {
		Button btnReturnToStart = createButton("Return to the start screen", 50, 450, root);
		activateReturntoStart(btnReturnToStart);
	}
	
	/**
	 * Method for creating button to pause animation
	 * @param root: the stack that holds all scenes modules
	 */
	private void createPauseButton(Group root) {
		Button btnPauseApp = createButton("Pause Application", 50, 550, root);
		activatePauseAnimationButton(btnPauseApp);
	}
	
	/**
	 * Method for creating button to resume animation
	 * @param root: the stack that holds all scenes modules
	 */
	private void createResumeButton(Group root) {
		Button btnResumeApp = createButton("Resume Application", 50, 600, root);
		activateResumeAnimationButton(btnResumeApp);
	}
	
	/**
	 * Method for creating button to button to allow for stepping through frames
	 * @param root: the stack that holds all scenes modules
	 */
	private void createStepThroughButton(Group root) {
		Button btnStepThroughFrames = createButton("Step through frames", 50, 650, root);
		activateStepThroughFrame(btnStepThroughFrames);
	}
	
	/**
	 * Method for creating the text label for the slider
	 * @param root
	 */
	private void createSliderLabel(Group root) {
		Label sliderLabel = createLabel("Frame Rate of Application", 1, 50, 700, root);
	}
	
	/**
	 * Method for creating and setting the slider's max and min values as well as coordinate position
	 * @param root: the stack that holds all scenes modules
	 */
	private void createSlider(Group root) {
		Slider slider = createSlider(0, 100, 50, 730, root);
	} 
	
	/**
	 * Method for creating text display for XML error message
	 * @param root: the stack that holds all scenes modules
	 */
	private void createErrorMessage(Group root) {
		Label label = createLabel("There is an error in format in your XML File", 3, 130, 100, root);
	}
	
	/**
	 * Create text display for the authors of project
	 * @param root: the stack that holds all scenes modules
	 */
	private void createNameLabel(Group root) {
		Label programmerNames = createLabel("Michael Deng\nPranava Raparla\nDavid Zhang", 2, 470, 200, root);
	}
	
	/**
	 * Create text display for title of project
	 * @param root: the stack that holds all scenes modules
	 */
	private void createTitleLabel(Group root) {
		Label title = createLabel("CELLULAR AUTOMATA!!", 4, 230, 100, root);
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
				System.out.println("Start Button Pressed");
				playGame(stage);

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

	/**
	 * 
	 * @param btn
	 */
	public void activateImportXMLButton(Button btn) {
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//TODO
				activateErrorPage();
				animation.stop();
			}
		});
	}

	/**
	 * Method for getting value of slider when mouse is let go
	 * @param slider: slider for controlling frame rate
	 */
	private void setSliderEventListener(Slider slider) {
		slider.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				runGameLoop(slider.getValue());
			}
		});
	}

	/**
	 * Method for activating button that will pause animation
	 * @param btn:  The button that is clicked to launch the event.
	 */
	private void activatePauseAnimationButton(Button btn){
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				animation.pause();
			}
		});
	}

	/**
	 * Method for activating button that will resume animation after pause
	 * @param btn:  The button that is clicked to launch the event.
	 */
	private void activateResumeAnimationButton(Button btn){
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				animation.play();
			}
		});
	}

	/**
	 * Method to activate button that returns to start screen
	 * @param btn:  The button that is clicked to launch the event.
	 */
	private void activateReturntoStart(Button btn){
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				start(primaryStage);
				animation.stop();
			}
		});
	}

	/**
	 * Activate button that goes through one frame when clicked 
	 * @param btn:  The button that is clicked to launch the event.
	 */
	private void activateStepThroughFrame(Button btn){
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				myGame.updateGameLoop();
			}
		});
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
