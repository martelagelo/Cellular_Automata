package application;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


public class Grid {
	
	int i = 0;
	
	void updateGrid(GridPane gridpane){
		for(int i = 0; i < ApplicationConstants.NUM_OF_COLUMNS; i++) {
			for(int j = 0; j < ApplicationConstants.NUM_OF_ROWS; j++) {
				if (this.i == 0) {
					Rectangle rect = generateCell(Color.BROWN);
					gridpane.add(rect, i, j, 1, 1);
				}
				else {
					Rectangle rect = generateCell(Color.WHITE);
					gridpane.add(rect, i, j, 1, 1);
				}
			}
		}
		if (this.i==0){
			this.i = 1;
		}
		else {
			this.i = 0;
		}
	}
	
	private Rectangle generateCell(Paint color){
		Rectangle rect = new Rectangle();
		rect.setWidth(ApplicationConstants.CELL_WIDTH);
		rect.setHeight(ApplicationConstants.CELL_WIDTH);
		rect.setFill(color);;
		return rect;
	}
}
