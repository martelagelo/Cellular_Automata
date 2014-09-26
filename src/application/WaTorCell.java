package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class WaTorCell extends Cell{
	
	private int fishTillBreed=3;
	private int sharkTillDeath=6;
	private int sharkTillBreed=6;

	private int fishBreed = fishTillBreed;
	private int sharkDeath = sharkTillDeath;
	private int sharkBreed = sharkTillBreed;

	private List<WaTorCell> moveFishList = new ArrayList<WaTorCell>();

	private List<WaTorCell> eatFishList = new ArrayList<WaTorCell>();
	private List<WaTorCell> moveSharkList = new ArrayList<WaTorCell>();
	
	private List<WaTorCell> breedList = new ArrayList<WaTorCell>();


	//FISHES ARE GREEN
	//SHARKS ARE ORANGE
	//SEA IS BLUE	


	@Override
	public void updateCell(int i, int j, Cell[][] cellMatrix) {
		xPos = i;
		yPos = j;	
		Matrix = cellMatrix;
		if(updatedState == null) {
			updateThisCell();
		}
	}

	private void updateThisCell() {
		if (currentState == Color.ORANGE){
			sharkUpdate();
		} else if (currentState == Color.GREEN) {
			fishUpdate();
		} else {
			updatedState = currentState;
		}
	}

	private void sharkUpdate(){	
		eatFishList = findCardinalToroidalNeighbors(xPos, yPos, Color.GREEN, Color.GREEN);	
		if(eatFishList.size() != 0){
			eatFish();
		}
		else {
			moveShark();
		}
	}


	private void fishUpdate(){
		moveFish();
	}



	private void eatFish(){
		for(int i = 0; i < eatFishList.size(); i++){			
			eatFishList.get(i).updatedState = Color.WHITE;
		}
		updatedState = currentState;		
		sharkDeath = sharkTillDeath;
		sharkBreed--;			
		sharkCheck(this);
	}

	private void moveShark(){	
		moveSharkList = findCardinalToroidalNeighbors(xPos, yPos, Color.WHITE, Color.WHITE);
		if (moveSharkList.size() != 0) {
			int r = randomFinder(moveSharkList);
			Matrix[xPos][yPos].updatedState = Color.WHITE;	
			sharkDeath --;
			sharkBreed --;
			moveSharkList.get(r).updatedState = Color.ORANGE;
			moveSharkList.get(r).sharkDeath = sharkDeath;
			moveSharkList.get(r).sharkBreed = sharkBreed;
			sharkCheck(moveSharkList.get(r));
		}
		else {
			updatedState = Color.ORANGE;
			sharkDeath--;
			sharkBreed--;
			sharkCheck(this);

		}

	}


	private void moveFish(){

		moveFishList = findCardinalToroidalNeighbors(xPos, yPos, Color.WHITE, Color.WHITE);
		if(moveFishList.size() != 0) {
			int r = randomFinder(moveFishList);	

			Matrix[xPos][yPos].updatedState = Color.WHITE;
			fishBreed--;
			moveFishList.get(r).updatedState = Color.GREEN;
			moveFishList.get(r).fishBreed = fishBreed;
			fishCheck(moveFishList.get(r));

		}
		else {
			updatedState = Color.GREEN;
			fishBreed--;
			fishCheck(this);
		}
	}



	private void sharkCheck(WaTorCell waTorCell){
		if(sharkDeath == 0){
			killShark(waTorCell);	
		}
		else if(sharkBreed == 0){
			breed(waTorCell, Color.ORANGE);
		}
	}


	private void fishCheck(WaTorCell waTorCell){
		if(fishBreed == 0){
			breed(waTorCell, Color.GREEN);
		}
	}

	private void killShark(WaTorCell waTorCell){
		waTorCell.updatedState = Color.WHITE;
		waTorCell.sharkDeath = sharkTillDeath;
		waTorCell.sharkBreed = sharkTillBreed;
	}

	private void breed(WaTorCell waTorCell, Color color){
		breedList = findCardinalToroidalNeighbors(waTorCell.xPos, waTorCell.yPos, Color.WHITE, Color.WHITE);
		if(breedList.size() != 0){
			int r = randomFinder(breedList);			
			breedList.get(r).updatedState = color;
			breedList.get(r).sharkDeath = sharkTillDeath;
			breedList.get(r).sharkBreed = sharkTillBreed;
			breedList.get(r).fishBreed = fishTillBreed;
		}	
		waTorCell.updatedState = color;
		waTorCell.sharkBreed = sharkTillBreed;	
		waTorCell.fishBreed = fishTillBreed;
	}

	private int randomFinder(List<WaTorCell> moveFishList2){
		int random = ApplicationConstants.rand.nextInt(moveFishList2.size());
		return random;
	}

//	private ArrayList findNeighbours(int i, int j, Color color){
//		ArrayList<WaTorCell3> list = new ArrayList<WaTorCell3>();
//
//		if (checkBounds(i+1,j)) {
//			if ((Matrix[i + 1][j].currentState == color && Matrix[i+1][j].updatedState==null) || Matrix[i+1][j].updatedState == color) {
//				list.add((WaTorCell3) Matrix[i + 1][j]);
//			} 
//		}
//		if (checkBounds(i - 1,j)) {
//			if((Matrix[i - 1][j].currentState == color && Matrix[i-1][j].updatedState==null) || Matrix[i-1][j].updatedState == color) {
//				list.add((WaTorCell3) Matrix[i - 1][j]);
//			}
//		} 
//		if (checkBounds(i,j+1)) {
//			if ((Matrix[i][j+1].currentState == color && Matrix[i][j+1].updatedState==null) || Matrix[i][j+1].updatedState == color) {
//				list.add((WaTorCell3) Matrix[i][j+1]);
//			}
//		} 
//		if (checkBounds(i,j - 1)) {
//			if ((Matrix[i][j-1].currentState == color && Matrix[i][j-1].updatedState==null) || Matrix[i][j-1].updatedState == color) {
//				list.add((WaTorCell3) Matrix[i][j - 1]);
//			}
//		} 		
//		return list;
//	}
//
//	private boolean checkBounds(int i, int j){
//		return (i < ApplicationConstants.NUM_OF_COLUMNS && i >= 0 && j < ApplicationConstants.NUM_OF_ROWS && j >= 0);
//	}

		
}
