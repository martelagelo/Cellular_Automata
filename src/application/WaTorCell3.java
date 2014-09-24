package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.paint.Color;

public class WaTorCell3 extends Cell{


	private int fishTillBreed=3;
	private int sharkTillDeath=3;
	private int sharkTillBreed=3;

	private int fishBreed = fishTillBreed;
	private int sharkDeath = sharkTillDeath;
	private int sharkBreed = sharkTillBreed;

	private List<WaTorCell3> moveFishList = new ArrayList<WaTorCell3>();
	private List<WaTorCell3> breedFishList = new ArrayList<WaTorCell3>();

	private List<WaTorCell3> eatFishList = new ArrayList<WaTorCell3>();
	private List<WaTorCell3> moveSharkList = new ArrayList<WaTorCell3>();
	private List<WaTorCell3> breedSharkList = new ArrayList<WaTorCell3>();


	//FISHES ARE GREEN
	//SHARKS ARE ORANGE
	//SEA IS BLUE	


	@Override
	public void updateCell(int i, int j, Cell[][] cellMatrix) {
		xPos = i;
		yPos = j;	
		Matrix = cellMatrix;
		if(updatedState == null) {
			sharkUpdate();
		}
	}


	private void sharkUpdate(){	
		if (currentState == Color.ORANGE){
			eatFishList = findNeighbours(xPos, yPos, Color.GREEN);	
			if(eatFishList.size() != 0){
				eatFish();
			}
			else {
				moveShark();
			}
		}
		else {
			updatedState = currentState;
		}
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
		moveSharkList = findNeighbours(xPos, yPos, Color.WHITE);
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


	private void sharkCheck(WaTorCell3 cell){

		if(sharkDeath == 0){
			//killShark(cell);
			breedShark(cell);
			cell.sharkBreed = sharkTillBreed;
		}else if(sharkBreed == 0){
			//breedShark(cell);
		}
	}

	private void killShark(WaTorCell3 cell){
		cell.updatedState = Color.WHITE;
		cell.sharkDeath = sharkTillDeath;
		cell.sharkBreed = sharkTillBreed;
	}

	private void breedShark(WaTorCell3 cell){
		breedSharkList = findNeighbours(cell.xPos, cell.yPos, Color.WHITE);
		if(breedSharkList.size() != 0){
			int r = randomFinder(breedSharkList);			
			breedSharkList.get(r).updatedState = Color.ORANGE;
			breedSharkList.get(r).sharkDeath = sharkTillDeath;
			breedSharkList.get(r).sharkBreed = sharkTillBreed;	
		}	
		
		cell.updatedState = Color.ORANGE;
		cell.sharkBreed = sharkTillBreed;		
	}

	private int randomFinder(List<WaTorCell3> list){
		System.out.println(list.size());
		int random = new Random().nextInt(list.size());

		return random;

	}


	private ArrayList findNeighbours(int i, int j, Color color){

		ArrayList<WaTorCell3> list = new ArrayList<WaTorCell3>();

		if (checkBounds(i+1,j)) {
			if ((Matrix[i + 1][j].currentState == color && Matrix[i+1][j].updatedState==null) || Matrix[i+1][j].updatedState == color) {
				list.add((WaTorCell3) Matrix[i + 1][j]);
				//System.out.println("YOOOOOOOO");

			} 
		}
		if (checkBounds(i - 1,j)) {
			if((Matrix[i - 1][j].currentState == color && Matrix[i-1][j].updatedState==null) || Matrix[i-1][j].updatedState == color) {
				list.add((WaTorCell3) Matrix[i - 1][j]);
				//System.out.println("YOOOOOOOO");
			}
		} 
		if (checkBounds(i,j+1)) {
			if ((Matrix[i][j+1].currentState == color && Matrix[i][j+1].updatedState==null) || Matrix[i][j+1].updatedState == color) {
				list.add((WaTorCell3) Matrix[i][j+1]);
				//System.out.println("YOOOOOOOO");
			}
		} 
		if (checkBounds(i,j - 1)) {
			if ((Matrix[i][j-1].currentState == color && Matrix[i][j-1].updatedState==null) || Matrix[i][j-1].updatedState == color) {
				list.add((WaTorCell3) Matrix[i][j - 1]);
				//System.out.println("YOOOOOOOO");
			}
		} 		
		return list;
	}


	private boolean checkBounds(int i, int j){
		return (i < ApplicationConstants.NUM_OF_COLUMNS && i >= 0 && j < ApplicationConstants.NUM_OF_ROWS && j >= 0);
	}

	private boolean checkIfUpdated(int i, int j){
		return (Matrix[i][j].updatedState==null);
	}


	@Override
	void setCurrentState(String s) {
		// TODO Auto-generated method stub

	}

}
