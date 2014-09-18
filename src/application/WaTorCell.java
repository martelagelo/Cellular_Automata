package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class WaTorCell extends Cell{
	
	
	private List<Integer> eatFishX = new ArrayList<Integer>();
	private List<Integer> eatFishY = new ArrayList<Integer>();		
	private List<Integer> moveSharkX = new ArrayList<Integer>();
	private List<Integer> moveSharkY = new ArrayList<Integer>();
	private List<Integer> breedCellX = new ArrayList<Integer>();
	private List<Integer> breedCellY = new ArrayList<Integer>();
	private List<Integer> moveFishX = new ArrayList<Integer>();
	private List<Integer> moveFishY = new ArrayList<Integer>();
	
	private int fishThreshold;
	private int sharkThreshold;
	
	//FISHES ARE GREEN
	//SHARKS ARE ORANGE
	//SEA IS BLUE
	

	@Override
	public void updateCell(int i, int j, Cell[][] cellMatrix) {
		// TODO Auto-generated method stub		
		super.xPos = i;
		super.yPos = j;	
		super.Matrix = cellMatrix;
		fishThreshold = 3;
		sharkThreshold = 3;
		
		sharkEatUpdate();
		sharkDieUpdate();
		fishBreedUpdate();
		//fishMoveUpdate still needs to be written.
	}

	@Override
	void setCurrentState(String s) {
		// TODO Auto-generated method stub
		
				
	}
	
	

	/*
	 * All of these methods check all 4 neighbours with 4 separate if statements.
	 * I created arraylists to store the information, because I have to pick a random one afterwards 
	 * to either move to (fish) or to eat (shark)
	 * 
	 * I used separate arraylists for X coordinate and Y coordiante because I couldn't come up with another way
	 */
	
	
	private void sharkEatUpdate(){
		if(Matrix[xPos][yPos].currentState == Color.ORANGE){

			if(Matrix[xPos][yPos+1].currentState != null && Matrix[xPos][yPos+1].currentState == Color.GREEN){
				eatFishX.add(xPos);
				eatFishY.add(yPos+1);				
			}else if(Matrix[xPos][yPos+1].currentState != null && Matrix[xPos][yPos+1].currentState == Color.BLUE){				
				moveSharkX.add(xPos);
				moveSharkY.add(yPos+1);			
			}
			if(Matrix[xPos+1][yPos].currentState != null && Matrix[xPos+1][yPos].currentState == Color.GREEN){
				eatFishX.add(xPos+1);
				eatFishY.add(yPos);
			}else if(Matrix[xPos+1][yPos].currentState != null && Matrix[xPos+1][yPos].currentState == Color.BLUE){
				moveSharkX.add(xPos);
				moveSharkY.add(yPos+1);
			}
			if(Matrix[xPos-1][yPos].currentState != null && Matrix[xPos-1][yPos].currentState == Color.GREEN){
				eatFishX.add(xPos-1);
				eatFishY.add(yPos);
			}else if(Matrix[xPos-1][yPos].currentState != null && Matrix[xPos-1][yPos].currentState == Color.BLUE){
				moveSharkX.add(xPos-1);
				moveSharkY.add(yPos);
			}
			if(Matrix[xPos][yPos-1].currentState != null && Matrix[xPos][yPos-1].currentState == Color.GREEN){
				eatFishX.add(xPos-1);
				eatFishY.add(yPos);
			} else if(Matrix[xPos][yPos-1].currentState != null && Matrix[xPos][yPos-1].currentState == Color.BLUE){
				moveSharkX.add(xPos);
				moveSharkY.add(yPos-1);
			}			
		}
		if(eatFishX != null){
			int random = (int)(Math.random()*eatFishX.size()-1);
			Matrix[eatFishX.get(random)][eatFishY.get(random)].updatedState = Color.GREEN;
			Matrix[xPos][yPos].turnsHungry = 0;				
		}
		
		if(eatFishX==null){				
			int random = (int)(Math.random()*moveSharkX.size()-1);				
			Matrix[moveSharkX.get(random)][moveSharkY.get(random)].updatedState = Color.ORANGE;
			Matrix[moveSharkX.get(random)][moveSharkY.get(random)].turnsHungry = Matrix[xPos][yPos].turnsHungry + 1;
			Matrix[xPos][yPos].updatedState = Color.BLUE;
			Matrix[xPos][yPos].turnsHungry = 0;			
		}
		
	}
	
	private void sharkDieUpdate(){
		if(Matrix[xPos][yPos].turnsHungry== sharkThreshold && Matrix[xPos][yPos].currentState==Color.ORANGE){
			Matrix[xPos][yPos].updatedState = Color.BLUE;
			Matrix[xPos][yPos].turnsHungry = 0;
		}
	}
	
	
	private void fishBreedUpdate(){
		if(Matrix[xPos][yPos].turnsEating == fishThreshold && Matrix[xPos][yPos].currentState == Color.GREEN){
			if(Matrix[xPos][yPos+1].currentState != null && Matrix[xPos][yPos+1].currentState == Color.BLUE){
				breedCellX.add(xPos);
				breedCellY.add(yPos+1);				
			}			
			if(Matrix[xPos+1][yPos].currentState != null && Matrix[xPos+1][yPos].currentState == Color.BLUE){
				breedCellX.add(xPos+1);
				breedCellY.add(yPos);
			}			
			if(Matrix[xPos-1][yPos].currentState != null && Matrix[xPos-1][yPos].currentState == Color.BLUE){
				breedCellX.add(xPos-1);
				breedCellY.add(yPos);
			}			
			if(Matrix[xPos][yPos-1].currentState != null && Matrix[xPos][yPos-1].currentState == Color.BLUE){
				breedCellX.add(xPos-1);
				breedCellY.add(yPos);
			}			
			if(breedCellX != null){
				int random = (int)(Math.random()*breedCellX.size()-1);	
				Matrix[breedCellX.get(random)][breedCellY.get(random)].updatedState = Color.GREEN;
				Matrix[breedCellX.get(random)][breedCellY.get(random)].turnsEating = 0;				
			}	
		}
	}
	

	
	
	
}
