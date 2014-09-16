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
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
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
	private double frameRate = 2;

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
		
		Scene myScene = new Scene(root, width, height, Color.WHITE);
		gridpane.setPadding(new Insets(5));

		for(int i = 0; i < ApplicationConstants.NUM_OF_COLUMNS; i++) {
			for(int j = 0; j < ApplicationConstants.NUM_OF_ROWS; j++) {
				Rectangle rect = generateCell(Color.WHITE);
				gridpane.add(rect, i, j,1,1);
			}
		}
		gridpane.setGridLinesVisible(true);
		
		root.getChildren().add(gridpane);
		
		Button btnStop = createButton("Exit Application", 50, 500, root);
		activateExitAppButton(btnStop);
		Label sliderLabel = createLabel("Speed of simulation: " + frameRate, 1, 50, 550, root);
		Slider slider = createSlider(0, 100, 50, 590, root);

		
		return myScene;
	}

	/**
	 * Create the game's frame
	 */
	public KeyFrame start() {
		return new KeyFrame(Duration.millis(2000 / frameRate), oneFrame);
	}

	private void updateGameLoop() {
		//grid.updateGrid(gridpane);
		System.out.println(frameRate);
	}

	private Rectangle generateCell(Paint color){
		Rectangle rect = new Rectangle();
		rect.setWidth(ApplicationConstants.CELL_WIDTH);
		rect.setHeight(ApplicationConstants.CELL_WIDTH);
		rect.setFill(color);
		return rect;
	}
	

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
		slider.setValue(frameRate);
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
	
	private void setSliderEventListener(Slider slider) {
		slider.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				frameRate = slider.getValue();
				System.out.println("NOOOOOO" + slider.getValue());
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
	
}
