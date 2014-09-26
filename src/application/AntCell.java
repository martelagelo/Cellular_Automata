package application;

import java.util.ArrayList;

import java.util.List;

import javafx.scene.paint.Color;

/**
 * Version 1
 * 
 * INCOMPLETE
 * 
 * Date: 9/14/2014
 * 
 * @author Michael Deng
 * @author Pranava Raparla
 * @author David Zhang
 *
 */
public class AntCell extends Cell {


	private boolean hasFood = false; //this always has to be passed on to next cell
	private int Orientation; // This has to be updated and pushed every time
	private int homePheromones; // 
	private int foodPheromones;
	private int newOrientation;
	private int pheromoneConstant = 3;
	private int largestPheromone;
	
	private List<AntCell> moveForwardList = new ArrayList<AntCell>();
	private List<AntCell> pheromoneList = new ArrayList<AntCell>();

	//ant is black
	//ground is white
	//food is blue



	//ant boolean must always be pushed
	// orientation updated and pushed
	// pheromones stay at the current


	@Override
	protected void updateCell(int i, int j, Cell[][] cellMatrix) {
		// TODO Auto-generated method stub	
		xPos = i;
		yPos = j;
		Matrix = (AntCell[][]) cellMatrix;

	}

	private void antForage(){
		if(Matrix[xPos][yPos].currentState == Color.BLACK){
			if(this.hasFood){
				goHome(this);
			}else{			
				//findFood(this);
			}
		}
	}


	private void goHome(AntCell cell){
		
		//homeMoveTo(checkForwardHome(cell));
	}

	
	private void homeMoveTo(AntCell cell){
		Matrix[xPos][yPos].updatedState = Color.WHITE;
		dropHomePheromone(this);
		cell.updatedState = Color.BLACK;
		cell.hasFood = true;
		cell.Orientation = newOrientation;
		
	}
	
	private void dropHomePheromone(AntCell cell) {
		if(this.homePheromones < largestPheromone)
			this.homePheromones = largestPheromone - pheromoneConstant;

	}
	
	private void pheromoneNeighbourFinder(AntCell cell){
		
		for(int i = (xPos-1); i<(xPos+1); i++){
			for(int j = (yPos-1); j<(yPos+1); j++){
				while(i != xPos && j!=yPos){
					
					if(checkBounds(i,j)){
						pheromoneList.add((AntCell) Matrix[i][j]);	
						if(cell.hasFood==true){
							findLargestHomePheromone(pheromoneList);
						}else if(cell.hasFood = false){
							findLargestFoodPheromone(pheromoneList);
						}
					}									
				}
			}
		}		
	}
	
	
	private void findLargestHomePheromone(List list){
		//Find largest and save as largestPheromone
	}
	
	private void findLargestFoodPheromone(List list){
		// Find largest and save as largestPheromone
	}

	

	private void checkForwardHome(AntCell cell){

		if(cell.Orientation == 1){
			findLargestHome((AntCell) Matrix[cell.xPos-1][cell.yPos], (AntCell) Matrix[cell.xPos-1][cell.yPos-1], (AntCell) Matrix[cell.xPos][cell.yPos-1]);			
		}else if(cell.Orientation ==2){
			findLargestHome((AntCell)Matrix[cell.xPos-1][cell.yPos-1], (AntCell)Matrix[cell.xPos][cell.yPos-1],(AntCell) Matrix[cell.xPos+1][cell.yPos-1]);			
		}else if(cell.Orientation ==3){
			findLargestHome((AntCell)Matrix[cell.xPos][cell.yPos-1], (AntCell)Matrix[cell.xPos+1][cell.yPos-1], (AntCell)Matrix[cell.xPos+1][cell.yPos]);			
		}else if(cell.Orientation == 4){
			findLargestHome((AntCell)Matrix[cell.xPos-1][cell.yPos+1], (AntCell)Matrix[cell.xPos-1][cell.yPos], (AntCell)Matrix[cell.xPos-1][cell.yPos-1]);			
		}else if(cell.Orientation ==5){
			findLargestHome((AntCell)Matrix[cell.xPos+1][cell.yPos-1], (AntCell)Matrix[cell.xPos+1][cell.yPos], (AntCell)Matrix[cell.xPos+1][cell.yPos+1]);			
		}else if(cell.Orientation == 6){
			findLargestHome((AntCell)Matrix[cell.xPos-1][cell.yPos], (AntCell)Matrix[cell.xPos-1][cell.yPos+1], (AntCell)Matrix[cell.xPos][cell.yPos+1]);			
		}else if(cell.Orientation == 7){
			findLargestHome((AntCell)Matrix[cell.xPos-1][cell.yPos+1], (AntCell)Matrix[cell.xPos][cell.yPos+1], (AntCell)Matrix[cell.xPos+1][cell.yPos+1]);			
		}else if(cell.Orientation == 8){
			findLargestHome((AntCell)Matrix[cell.xPos][cell.yPos+1], (AntCell)Matrix[cell.xPos+1][cell.yPos+1], (AntCell)Matrix[cell.xPos+1][cell.yPos]);			
		}		

	}
	
	private void checkForwardFood(AntCell cell){

		if(cell.Orientation == 1){
			findLargestFood((AntCell)Matrix[cell.xPos-1][cell.yPos], (AntCell)Matrix[cell.xPos-1][cell.yPos-1],(AntCell) Matrix[cell.xPos][cell.yPos-1]);			
		}else if(cell.Orientation ==2){
			findLargestFood((AntCell)Matrix[cell.xPos-1][cell.yPos-1], (AntCell)Matrix[cell.xPos][cell.yPos-1],(AntCell) Matrix[cell.xPos+1][cell.yPos-1]);			
		}else if(cell.Orientation ==3){
			findLargestFood((AntCell)Matrix[cell.xPos][cell.yPos-1], (AntCell)Matrix[cell.xPos+1][cell.yPos-1], (AntCell)Matrix[cell.xPos+1][cell.yPos]);			
		}else if(cell.Orientation == 4){
			findLargestFood((AntCell)Matrix[cell.xPos-1][cell.yPos+1], (AntCell)Matrix[cell.xPos-1][cell.yPos], (AntCell)Matrix[cell.xPos-1][cell.yPos-1]);			
		}else if(cell.Orientation ==5){
			findLargestFood((AntCell)Matrix[cell.xPos+1][cell.yPos-1], (AntCell)Matrix[cell.xPos+1][cell.yPos], (AntCell)Matrix[cell.xPos+1][cell.yPos+1]);			
		}else if(cell.Orientation == 6){
			findLargestFood((AntCell)Matrix[cell.xPos-1][cell.yPos], (AntCell)Matrix[cell.xPos-1][cell.yPos+1],(AntCell) Matrix[cell.xPos][cell.yPos+1]);			
		}else if(cell.Orientation == 7){
			findLargestFood((AntCell)Matrix[cell.xPos-1][cell.yPos+1], (AntCell)Matrix[cell.xPos][cell.yPos+1], (AntCell)Matrix[cell.xPos+1][cell.yPos+1]);			
		}else if(cell.Orientation == 8){
			findLargestFood((AntCell)Matrix[cell.xPos][cell.yPos+1], (AntCell)Matrix[cell.xPos+1][cell.yPos+1], (AntCell)Matrix[cell.xPos+1][cell.yPos]);			
		}		

	}


	private AntCell findLargestHome(AntCell cell1, AntCell cell2, AntCell cell3){

		List<AntCell> findLargestHomeList = new ArrayList<AntCell>();

		if(checkBounds(cell1.xPos, cell1.yPos)){		
			findLargestHomeList.add(cell1);						
		}
		if(checkBounds(cell2.xPos, cell2.yPos)){
			findLargestHomeList.add(cell2);			
		}
		if(checkBounds(cell3.xPos, cell3.yPos)){
			findLargestHomeList.add(cell3);
		}		
	
		if(findLargestHomeList.size() != 0){				
			//new Cell large = findLargest(findLargestHomeList);
			//newOrientationFinder(large);
		}
		return null;
		//return large;		
	}

	private AntCell findLargestFood(AntCell cell1, AntCell cell2, AntCell cell3){
		List<AntCell> findLargestFoodList = new ArrayList<AntCell>();

		if(checkBounds(cell1.xPos, cell1.yPos)){
			findLargestFoodList.add(cell1);		
		}

		if(checkBounds(cell2.xPos, cell2.yPos)){
			findLargestFoodList.add(cell2);			
		}
		if(checkBounds(cell3.xPos, cell3.yPos)){
			findLargestFoodList.add(cell3);			
		}
		if(findLargestFoodList.size() != 0){
			
			//new AntCell large = findLargest(findLargestFoodList);
			//newOrientationFinder(large);

		}
		return null;
		//return large;
	}

	
	private void newOrientationFinder(AntCell cell){
		
		if(cell == (AntCell) Matrix[xPos-1][yPos-1]){
			newOrientation = 1;
		}else if(cell == (AntCell)Matrix[xPos][yPos-1]){
			newOrientation = 2;
		}else if(cell == (AntCell)Matrix[xPos+1][yPos-1]){
			newOrientation = 3;
		}else if(cell == (AntCell)Matrix[xPos-1][yPos]){
			newOrientation = 4;
		}else if(cell == (AntCell)Matrix[xPos+1][yPos]){
			newOrientation = 5;
		}else if(cell == (AntCell)Matrix[xPos-1][yPos+1]){
			newOrientation = 6;
		}else if(cell == (AntCell)Matrix[xPos][yPos+1]){
			newOrientation = 7;
		}else if(cell == (AntCell)Matrix[xPos+1][yPos+1]){
			newOrientation = 8;
		}
		
	}
	

	private boolean checkBounds(int i, int j){
		return (i < ApplicationConstants.NUM_OF_COLUMNS && i >= 0 && j < ApplicationConstants.NUM_OF_ROWS && j >= 0);
	}



}