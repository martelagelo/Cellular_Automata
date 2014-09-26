package application;

import java.util.Random;

/**
 * Version 1
 * Date: 9/14/2014
 * 
 * @author Michael Deng
 * @author Pranava Raparla
 * @author David Zhang
 *
 */
public class ApplicationConstants {
	
	public static final Integer STAGE_WIDTH = 1520;
	public static final Integer STAGE_HEIGHT = 820;
	public static final Integer GRID_WIDTH = 1000;
	public static final Integer GRID_HEIGHT = 1000;
	public static final Integer CELL_WIDTH = 20;
	public static final Integer NUM_OF_ROWS = GRID_HEIGHT/CELL_WIDTH ;
	public static final Integer NUM_OF_COLUMNS = GRID_WIDTH/CELL_WIDTH;
	public static final Double DEFAULT_FRAME_RATE = 2.0;
	
	public static final Double HEX_OFFSET = 5.0;
	
	public static final Integer MODULE_X_POS = STAGE_WIDTH - 500;
	
	public static final Integer FIRST_BUTTON_Y_POS = 25;
	public static final Integer SECOND_BUTTON_Y_POS = 75;
	public static final Integer THIRD_BUTTON_Y_POS = 125;
	public static final Integer FOURTH_BUTTON_Y_POS = 175;
	public static final Integer FIFTH_BUTTON_Y_POS = 225;
	public static final Integer SLIDER_LABEL_Y_POS = 275;
	public static final Integer SLIDER_Y_POS = 305;
	public static final Integer LINECHART_Y_POS = 400;
	public static final Integer TITLE_LABEL_Y_POS = 100;
	public static final Integer TITLE_LABEL_X_POS = 130;
	public static final Integer ERROR_LABEL_X_POS = 230;
	public static final Integer NAMES_LABEL_X_POS = 470;
	public static final Integer NAMES_LABEL_Y_POS = 200;
	
	public static final Integer SLIDER_MIN_VALUE = 0;
	public static final Integer SLIDER_MAX_VALUE = 100;
	
	public static final Integer LABEL_FONT_SIZE = 1;
	public static final Integer NAMES_FONT_SIZE = 2;
	public static final Integer TITLE_FONT_SIZE = 3;
	public static final Integer BIGGER_TITLE_FONT_SIZE = 4;
	
	
	public static boolean gridEditable = false;
	
	
	public static Random rand = new Random();
	
//	public ApplicationConstants() {
//		//TODO: Enter something here.
//	}
}
