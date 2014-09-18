package application;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Segregation extends Cell {

	private int same;
	private int different;
	private int threshold;


	public Segregation(){
		same = 0;
		different = 0;
		threshold = 0;
		xPos = 0;
		currentState = Color.WHITE;
	}
	
	void segregateThisCell(){		
		cellMover(percentageCalc());
	}
	
	private void setThreshold(double num){
		threshold = num;
	}


	private double percentageCalc(){
		for(int x=0; x<ApplicationConstants.NUM_OF_COLUMNS; x++){
			for(int y=0; y<ApplicationConstants.NUM_OF_ROWS; y++){
				xPos = x;
				yPos = y;

				for(int i=x-1; i<=x+1; i++){
					for(int j= y-1; j<=y+1; j++){

						if(Matrix[i][j].currentState==Matrix[x][y].currentState){
							same++;
						}	

						if(Matrix[i][j].currentState!=null){
							if(Matrix[i][j].currentState!=Matrix[x][y].currentState){
								different++;								
							}


						}							
					}
					same = same -1;
				}
			}		
		}	
		return same/(same+different);
	}


	private void cellMover(double percentage){
		if(percentage < threshold){
			for(int b = yPos; b<ApplicationConstants.NUM_OF_ROWS; b++){
				for(int a = 0; a<ApplicationConstants.NUM_OF_COLUMNS; a++){
					if(b==yPos && a<=xPos) // check if in part of row before current cell
						continue; // continue to next iteration if true

					if (Matrix[a][b].currentState==Color.WHITE && Matrix[a][b].updatedState == null){
						Matrix[a][b].updatedState = Matrix[xPos][yPos].currentState;
						Matrix[xPos][yPos].updatedState = Color.WHITE;


					}
				}
			}
		}
		Matrix[xPos][yPos].updatedState = Matrix[xPos][yPos].currentState;		
	}


	@Override
	void setCurrentState(String s) {

		switch(s.toUpperCase()) {
		case "WHITE":
			currentState = Color.WHITE;
			break;
		case "RED":
			currentState = Color.RED;
			break;
		case "BLUE":
			currentState = Color.BLUE;
			break;
		}
	}

	@Override
	public void updateCell(int i, int j, Cell[][] cellMatrix) {
		// TODO Auto-generated method stub

	}



	}

