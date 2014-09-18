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
	 * 
	 * @param root
	 */
	private void populateStartPageModules(Group root) {
		createTitleLabel(root);
		createNameLabel(root);
		createStartGameButton(root);
		createExitButton(root);
		createImportXMLButton(root);
	}
	
	/**
	 * 
	 * @param root
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
	 * 
	 * @param root
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
	 * 
	 * @param minValue
	 * @param maxValue
	 * @param x_Coord
	 * @param y_Coord
	 * @param root
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
	 * 
	 * @param content
	 * @param font_size
	 * @param x_Coord
	 * @param y_Coord
	 * @param root
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
	 * 
	 * @param root
	 */
	private void createStartGameButton(Group root){
		Button btnStart = createButton("Start Simulation", 50, 450, root);
		activateStartButton(btnStart, primaryStage);
	}
	
	/**
	 * 
	 * @param root
	 */
	private void createExitButton(Group root) {
		Button btnExit = createButton("Exit Application", 50, 500, root);
		activateExitAppButton(btnExit);
	}
	
	/**
	 * 
	 * @param root
	 */
	private void createImportXMLButton(Group root) {
		Button btnImportXML = createButton("Import an XML File", 50, 550, root);
		activateImportXMLButton(btnImportXML);
	}
	
	/**
	 * 
	 * @param root
	 */
	private void createReturnToStartButton(Group root) {
		Button btnReturnToStart = createButton("Return to the start screen", 50, 450, root);
		activateReturntoStart(btnReturnToStart);
	}
	
	/**
	 * 
	 * @param root
	 */
	private void createPauseButton(Group root) {
		Button btnPauseApp = createButton("Pause Application", 50, 550, root);
		activatePauseAnimationButton(btnPauseApp);
	}
	
	/**
	 * 
	 * @param root
	 */
	private void createResumeButton(Group root) {
		Button btnResumeApp = createButton("Resume Application", 50, 600, root);
		activateResumeAnimationButton(btnResumeApp);
	}
	
	/**
	 * 
	 * @param root
	 */
	private void createStepThroughButton(Group root) {
		Button btnStepThroughFrames = createButton("Step through frames", 50, 650, root);
		activateStepThroughFrame(btnStepThroughFrames);
	}
	
	/**
	 * 
	 * @param root
	 */
	private void createSliderLabel(Group root) {
		Label sliderLabel = createLabel("Frame Rate of Application", 1, 50, 700, root);
	}
	
	/**
	 * 
	 * @param root
	 */
	private void createSlider(Group root) {
		Slider slider = createSlider(0, 100, 50, 730, root);
	} 
	
	/**
	 * 
	 * @param root
	 */
	private void createErrorMessage(Group root) {
		Label label = createLabel("There is an error in format in your XML File", 3, 130, 100, root);
	}
	
	/**
	 * 
	 * @param root
	 */
	private void createNameLabel(Group root) {
		Label programmerNames = createLabel("Michael Deng\nPranava Raparla\nDavid Zhang", 2, 470, 200, root);
	}
	
	/**
	 * 
	 * @param root
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
	 * 
	 * @param slider
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
	 * 
	 * @param btn
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
	 * 
	 * @param btn
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
	 * 
	 * @param btn
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
	 * 
	 * @param btn
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
