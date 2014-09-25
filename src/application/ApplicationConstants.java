package application;

import java.util.Random;

public class ApplicationConstants {
	
	public static final Integer STAGE_WIDTH = 1500;
	public static final Integer STAGE_HEIGHT = 800;
	public static final Integer CELL_WIDTH = 20;
	public static final Integer NUM_OF_ROWS = STAGE_HEIGHT/CELL_WIDTH - 1;
	public static final Integer NUM_OF_COLUMNS = STAGE_WIDTH/CELL_WIDTH - 1 - 25;
	public static final Double DEFAULT_FRAME_RATE = 2.0;
	
	public static final Integer MODULE_X_POS = STAGE_WIDTH - 500;
	
	public static final Integer FIRST_BUTTON_Y_POS = 25;
	public static final Integer SECOND_BUTTON_Y_POS = 75;
	public static final Integer THIRD_BUTTON_Y_POS = 125;
	public static final Integer FOURTH_BUTTON_Y_POS = 175;
	public static final Integer FIFTH_BUTTON_Y_POS = 225;
	public static final Integer SLIDER_LABEL_Y_POS = 275;
	public static final Integer SLIDER_Y_POS = 305;
	
	
	
	public static boolean gridEditable = false;
	
	
	public static Random rand = new Random();
	
//	public ApplicationConstants() {
//		//TODO: Enter something here.
//	}
}
