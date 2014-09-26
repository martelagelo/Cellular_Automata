package application;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Segregation extends Cell {

	private double same = 0;
	private double different = 0;
	public double threshold;

		public Segregation(){
			//same = 0;
			//different = 0;
			threshold = 0.5;
			xPos = 0;
			//currentState = Color.WHITE;
		}

	public void setThreshold(double num){
		threshold = num;
	}

	/*
	 * Method for calculating the percentage of same neighbours / total
	 */
	
	private double percentageCalc(){
		for(int i = xPos-1; i <= xPos+1; i++){
			for(int j = yPos-1; j <= yPos+1; j++){
				if(i >= 0 && j >= 0 && i < ApplicationConstants.NUM_OF_COLUMNS && j < ApplicationConstants.NUM_OF_ROWS){
					if(Matrix[i][j].currentState==Matrix[xPos][yPos].currentState){
						same++;
						//System.out.println("YESSSSSSSSS");
					} else {
//					if(Matrix[i][j].currentState!=Matrix[xPos][yPos].currentState){
						different++;							
						//System.out.println("Hello World");
					}
				}							
			}
		}
		same--;
		//System.out.println(same + "    " + different);
		Double d = (same/(same+different));
		System.out.println(d);
		return d;
	}

	/*
	 * Method for updating cells depending on satisfaction of the current cell
	 */
	
	private void cellMover(double percentage){
		System.out.println(percentage);
		if(percentage < threshold){
			System.out.println("MOVE DAT ASS!");
			for(int b = yPos; b < ApplicationConstants.NUM_OF_ROWS; b++){
				for(int a = 0; a < ApplicationConstants.NUM_OF_COLUMNS; a++){
					if(b == yPos && a <= xPos) // check if in part of row before current cell
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
	public void updateCell(int i, int j, Cell[][] cellMatrix) {
		same = 0;
		different = 0;
		super.Matrix = cellMatrix;
		super.xPos = i;
		super.yPos = j;
		System.out.println(xPos + "  " + yPos);
		Double d = percentageCalc();
		cellMover(d);
	}
}

