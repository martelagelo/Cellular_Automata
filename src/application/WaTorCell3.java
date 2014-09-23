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
		// TODO Auto-generated method stub
		

		xPos = i;
		yPos = j;	
	}
	
	
	private void sharkUpdate(){	
		eatFishList = findNeighbours(xPos, yPos, Color.GREEN);	
		
		if(eatFishList!=null){
			eatFish();
		}else{
			moveSharkList = findNeighbours(xPos, yPos, Color.WHITE);
			moveShark();
		}		
	}
	
	
	private void eatFish(){
		
		
		for(int i=0; i < eatFishList.size(); i++){			
			eatFishList.get(i).updatedState = Color.WHITE;		
		}
		Matrix[xPos][yPos].updatedState = Color.ORANGE;		
		sharkDeath = sharkTillDeath;
		sharkBreed --;			
		sharkCheck(this);
	}
	
	private void moveShark(){	
		
		int r = randomFinder(moveSharkList);
		moveSharkList.get(r).updatedState = Color.ORANGE;		
		Matrix[xPos][yPos].updatedState = Color.WHITE;	
		sharkDeath --;
		sharkBreed --;
		sharkCheck(moveSharkList.get(r));
	}
	
	
	private void sharkCheck(WaTorCell3 cell){
		
		if(sharkDeath == 0){
			killShark(cell);
		}else if(sharkBreed == 0){
			breedShark(cell);
		}
	}
	
	private void killShark(WaTorCell3 cell){
		cell.updatedState = Color.WHITE;
		cell.sharkDeath = sharkTillDeath;
		cell.sharkBreed = sharkTillBreed;
	}
	
	private void breedShark(WaTorCell3 cell){
		breedSharkList = findNeighbours(cell.xPos, cell.yPos, Color.WHITE);
		
		if(breedSharkList != null){			
			int r = randomFinder(breedSharkList);			
			breedSharkList.get(r).updatedState = Color.ORANGE;
			breedSharkList.get(r).sharkDeath = sharkTillDeath;
			breedSharkList.get(r).sharkBreed = sharkTillBreed;	
		}		
		cell.updatedState = Color.ORANGE;
		cell.sharkBreed = sharkTillBreed;		
	}
	
	private int randomFinder(List<WaTorCell3> list){
			
		int random = new Random().nextInt(list.size()-1);
		
		return random;
		
	}
	
	
	private ArrayList findNeighbours(int i, int j, Color color){
		
		ArrayList<WaTorCell3> list = new ArrayList<WaTorCell3>();
		
		if (checkBounds(i + 1,j)) {
			if (Matrix[i + 1][j].currentState == color) {
				list.add((WaTorCell3) Matrix[i + 1][j]);
			}				
		} 
		
		if (checkBounds(i - 1,j)) {
			if(Matrix[i - 1][j].currentState == color) {
				list.add((WaTorCell3) Matrix[i - 1][j]);
			}
		} 
		if (checkBounds(i,j + 1)) {
			if (Matrix[i][j + 1].currentState == color) {
				list.add((WaTorCell3) Matrix[i][j + 1]);
			}
		} 
		if (checkBounds(i,j - 1)) {
			if (Matrix[i][j - 1].currentState == color) {
				list.add((WaTorCell3) Matrix[i][j - 1]);
			}
		} 		
		return list;
	}
	
	
	private boolean checkBounds(int i, int j){
		return (i < ApplicationConstants.NUM_OF_COLUMNS && i >= 0 && j < ApplicationConstants.NUM_OF_ROWS && j >= 0);
	}
	

	@Override
	void setCurrentState(String s) {
		// TODO Auto-generated method stub
		
	}

}
