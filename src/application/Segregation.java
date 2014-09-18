package application;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Segregation extends Cell {

	private int same;
	private int different;
	private int percentage;
	private int threshold;
	private int currentX;
	private int currentY;


	void segregateThisCell(){
		cellMover(percentageCalc());
	}


	private double percentageCalc(){
		for(int x=0; x<ApplicationConstants.NUM_OF_COLUMNS; x++){
			for(int y=0; y<ApplicationConstants.NUM_OF_ROWS; y++){
				currentX = x;
				currentY = y;

				for(int i=x-1; i<=x+1; i++){
					for(int j= y-1; j<=y+1; j++){

						if(Matrix[i][j].currentState==Matrix[x][y].currentState){
							same++;
						}	

						if(Matrix[i][j].currentState!=null){
							if(Matrix[i][j].currentState!=Matrix[x][y].currentState){
								different++;								
							}

							same = same -1;
						}							
					}
				}
			}		
		}	
		return same/(same+different);
	}


	private void cellMover(double percentage){
		if(percentage < threshold){
			for(int b = currentY; b<ApplicationConstants.NUM_OF_ROWS; b++){
				for(int a = 0; a<ApplicationConstants.NUM_OF_COLUMNS; a++){
					if(b==currentY && a<=currentX) // check if in part of row before current cell
						continue; // continue to next iteration if true
					if (Matrix[a][b].currentState==Color.WHITE && Matrix[a][b].updatedState == null){
						Matrix[a][b].updatedState = Matrix[currentX][currentY].currentState;
						Matrix[currentX][currentY].updatedState = Color.WHITE;


					}
				}
			}
		}
		Matrix[currentX][currentY].updatedState = Matrix[currentX][currentY].currentState;		
	}



}