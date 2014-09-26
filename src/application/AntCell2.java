package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
/**
 * INCOMPLETE
 * Date: 9/14/2014
 * 
 * @author Michael Deng
 * @author Pranava Raparla
 * @author David Zhang
 *
 */

public class AntCell2 extends Cell {


	private boolean hasFood = false; //this always has to be passed on to next cell
	private int Orientation; // This has to be updated and pushed every time
	private int homePheromones; // 
	private int foodPheromones;
	private int newOrientation;
	private int pheromoneConstant = 3;
	private int largestPheromone;
	
	private List<AntCell2> moveForwardList = new ArrayList<AntCell2>();
	private List<AntCell2> pheromoneList = new ArrayList<AntCell2>();

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
		Matrix = (AntCell2[][]) cellMatrix;

	}

	private void antForage(){
		if(Matrix[xPos][yPos].currentState == Color.BLACK){
			if(this.hasFood){
				goHome(this);
			}else{			
				findFood(this);
			}
		}
	}


	private void goHome(AntCell2 cell){
		
		homeMoveTo(checkForwardHome(cell));
	}
		
		
		
	}

	
	private void homeMoveTo(AntCell2 cell){
		Matrix[xPos][yPos].updatedState = Color.WHITE;
		dropHomePheromone(this);
		cell.updatedState = Color.BLACK;
		cell.hasFood = true;
		cell.Orientation = newOrientation;
		
	}
	
	private void dropHomePheromone(AntCell2 cell){
		if(this.homePheromones < largestPheromone){
			this.homePheromones = largestPheromone - pheromoneConstant;

	}
	
	private void pheromoneNeighbourFinder(AntCell2 cell){
		
		for(int i = (xPos-1); i<(xPos+1); i++){
			for(int j = (yPos-1); j<(yPos+1); j++){
				while(i != xPos && j!=yPos){
					
					if(checkBounds(i,j)){
						pheromoneList.add((AntCell2) Matrix[i][j]);	
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
	
	private void find LargestFoodPheromone(List list){
		// Find largest and save as largestPheromone
	}

	

	private void checkForwardHome(AntCell2 cell){

		if(cell.Orientation == 1){
			findLargestHome((AntCell2) Matrix[cell.xPos-1][cell.yPos], (AntCell2) Matrix[cell.xPos-1][cell.yPos-1], (AntCell2) Matrix[cell.xPos][cell.yPos-1]);			
		}else if(cell.Orientation ==2){
			findLargestHome((AntCell2)Matrix[cell.xPos-1][cell.yPos-1], (AntCell2)Matrix[cell.xPos][cell.yPos-1],(AntCell2) Matrix[cell.xPos+1][cell.yPos-1]);			
		}else if(cell.Orientation ==3){
			findLargestHome((AntCell2)Matrix[cell.xPos][cell.yPos-1], (AntCell2)Matrix[cell.xPos+1][cell.yPos-1], (AntCell2)Matrix[cell.xPos+1][cell.yPos]);			
		}else if(cell.Orientation == 4){
			findLargestHome((AntCell2)Matrix[cell.xPos-1][cell.yPos+1], (AntCell2)Matrix[cell.xPos-1][cell.yPos], (AntCell2)Matrix[cell.xPos-1][cell.yPos-1]);			
		}else if(cell.Orientation ==5){
			findLargestHome((AntCell2)Matrix[cell.xPos+1][cell.yPos-1], (AntCell2)Matrix[cell.xPos+1][cell.yPos], (AntCell2)Matrix[cell.xPos+1][cell.yPos+1]);			
		}else if(cell.Orientation == 6){
			findLargestHome((AntCell2)Matrix[cell.xPos-1][cell.yPos], (AntCell2)Matrix[cell.xPos-1][cell.yPos+1], (AntCell2)Matrix[cell.xPos][cell.yPos+1]);			
		}else if(cell.Orientation == 7){
			findLargestHome((AntCell2)Matrix[cell.xPos-1][cell.yPos+1], (AntCell2)Matrix[cell.xPos][cell.yPos+1], (AntCell2)Matrix[cell.xPos+1][cell.yPos+1]);			
		}else if(cell.Orientation == 8){
			findLargestHome((AntCell2)Matrix[cell.xPos][cell.yPos+1], (AntCell2)Matrix[cell.xPos+1][cell.yPos+1], (AntCell2)Matrix[cell.xPos+1][cell.yPos]);			
		}		

	}
	
	private void checkForwardFood(AntCell2 cell){

		if(cell.Orientation == 1){
			findLargestFood((AntCell2)Matrix[cell.xPos-1][cell.yPos], (AntCell2)Matrix[cell.xPos-1][cell.yPos-1],(AntCell2) Matrix[cell.xPos][cell.yPos-1]);			
		}else if(cell.Orientation ==2){
			findLargestFood((AntCell2)Matrix[cell.xPos-1][cell.yPos-1], (AntCell2)Matrix[cell.xPos][cell.yPos-1],(AntCell2) Matrix[cell.xPos+1][cell.yPos-1]);			
		}else if(cell.Orientation ==3){
			findLargestFood((AntCell2)Matrix[cell.xPos][cell.yPos-1], (AntCell2)Matrix[cell.xPos+1][cell.yPos-1], (AntCell2)Matrix[cell.xPos+1][cell.yPos]);			
		}else if(cell.Orientation == 4){
			findLargestFood((AntCell2)Matrix[cell.xPos-1][cell.yPos+1], (AntCell2)Matrix[cell.xPos-1][cell.yPos], (AntCell2)Matrix[cell.xPos-1][cell.yPos-1]);			
		}else if(cell.Orientation ==5){
			findLargestFood((AntCell2)Matrix[cell.xPos+1][cell.yPos-1], (AntCell2)Matrix[cell.xPos+1][cell.yPos], (AntCell2)Matrix[cell.xPos+1][cell.yPos+1]);			
		}else if(cell.Orientation == 6){
			findLargestFood((AntCell2)Matrix[cell.xPos-1][cell.yPos], (AntCell2)Matrix[cell.xPos-1][cell.yPos+1],(AntCell2) Matrix[cell.xPos][cell.yPos+1]);			
		}else if(cell.Orientation == 7){
			findLargestFood((AntCell2)Matrix[cell.xPos-1][cell.yPos+1], (AntCell2)Matrix[cell.xPos][cell.yPos+1], (AntCell2)Matrix[cell.xPos+1][cell.yPos+1]);			
		}else if(cell.Orientation == 8){
			findLargestFood((AntCell2)Matrix[cell.xPos][cell.yPos+1], (AntCell2)Matrix[cell.xPos+1][cell.yPos+1], (AntCell2)Matrix[cell.xPos+1][cell.yPos]);			
		}		

	}


	private AntCell2 findLargestHome(AntCell2 cell1, AntCell2 cell2, AntCell2 cell3){

		List<AntCell2> findLargestHomeList = new ArrayList<AntCell2>();

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
			new Cell large = findLargest(findLargestHomeList);
			newOrientationFinder(large);
		}		
		return large;		
	}

	private AntCell2 findLargestFood(AntCell2 cell1, AntCell2 cell2, AntCell2 cell3){
		List<AntCell2> findLargestFoodList = new ArrayList<AntCell2>();

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
			
			new AntCell2 large = findLargest(findLargestFoodList);
			newOrientationFinder(large);

		}
		
		return large;
	}

	
	private void newOrientationFinder(AntCell2 cell){
		
		if(cell == (AntCell2) Matrix[xPos-1][yPos-1]){
			newOrientation = 1;
		}else if(cell == (AntCell2)Matrix[xPos][yPos-1]){
			newOrientation = 2;
		}else if(cell == (AntCell2)Matrix[xPos+1][yPos-1]){
			newOrientation = 3;
		}else if(cell == (AntCell2)Matrix[xPos-1][yPos]){
			newOrientation = 4;
		}else if(cell == (AntCell2)Matrix[xPos+1][yPos]){
			newOrientation = 5;
		}else if(cell == (AntCell2)Matrix[xPos-1][yPos+1]){
			newOrientation = 6;
		}else if(cell == (AntCell2)Matrix[xPos][yPos+1]){
			newOrientation = 7;
		}else if(cell == (AntCell2)Matrix[xPos+1][yPos+1]){
			newOrientation = 8;
		}
		
	}
	

	private boolean checkBounds(int i, int j){
		return (i < ApplicationConstants.NUM_OF_COLUMNS && i >= 0 && j < ApplicationConstants.NUM_OF_ROWS && j >= 0);
	}

	@Override
	void setCurrentState(String s) {
		// TODO Auto-generated method stub

	}




}
