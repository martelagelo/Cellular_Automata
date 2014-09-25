package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

/**
 * Version 1
 * Date: 9/14/2014
 * 
 * @author Michael Deng
 * @author Pranava Raparla
 * @author David Zhang
 *
 */
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
	//SEA IS WHITE	


	/**
	 * Updates the cell state
	 */
	@Override
	public void updateCell(int i, int j) {
		xPos = i;
		yPos = j;	
		if(updatedState == null) {
			update();
		}
	}
	
	/**
	 * Selects the cell type and updates it
	 */
	private void update() {
		if (currentState == Color.ORANGE){
			sharkUpdate();
		} else if (currentState == Color.GREEN) {
			fishUpdate();
		} else {
			updatedState = currentState;
		}
	}
	
	/**
	 * Have the shark eat the fish around it or move if there are not fish
	 */
	private void sharkUpdate(){	
		eatFishList = findWantedNeighbors(Color.GREEN, Color.GREEN);	
		if(eatFishList.size() != 0){
			eatFish();
		}
		else {
			moveShark();
		}
	}

	/**
	 * Moves the fish
	 */
	private void fishUpdate(){
		moveFish();
	}
	
	/**
	 * Find the fish neighbors and eat them
	 */
	private void eatFish(){
		for(int i = 0; i < eatFishList.size(); i++){			
			eatFishList.get(i).updatedState = Color.WHITE;
		}
		updatedState = currentState;		
		sharkDeath = sharkTillDeath;
		sharkBreed--;			
		sharkCheck(this);
	}

	/**
	 * Moves the shark to a free location and then checks its breed and death statuses
	 */
	private void moveShark(){	
		moveSharkList = findWantedNeighbors(Color.WHITE, Color.WHITE);
		if (moveSharkList.size() != 0) {
			int r = randomFinder(moveSharkList);
			this.updatedState = Color.WHITE;	
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

	/**
	 * Moves the fish to a free location and then checks it breed status
	 */
	private void moveFish(){
		moveFishList = findWantedNeighbors(Color.WHITE, Color.WHITE);
		if(moveFishList.size() != 0) {
			int r = randomFinder(moveFishList);	
			this.updatedState = Color.WHITE;
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

	/**
	 * Checks if the shark should die or breed or do nothing
	 * @param waTorCell: The cell the shark has moved to 
	 */
	private void sharkCheck(WaTorCell waTorCell){
		if(sharkDeath == 0){
			killShark(waTorCell);	
		}
		else if(sharkBreed == 0){
			breed(waTorCell, Color.ORANGE);
		}
	}

	/**
	 * Checks if the fish should breed or do nothing
	 * @param waTorCell: The cell the fish has moved to
	 */
	private void fishCheck(WaTorCell waTorCell){
		if(fishBreed == 0){
			breed(waTorCell, Color.GREEN);
		}
	}

	/**
	 * Kills the shark if its time for him to die
	 * @param waTorCell: The cell the shark moved to 
	 */
	private void killShark(WaTorCell waTorCell){
		waTorCell.updatedState = Color.WHITE;
		waTorCell.sharkDeath = sharkTillDeath;
		waTorCell.sharkBreed = sharkTillBreed;
	}

	/**
	 * Breed either the shark or the fish
	 * @param waTorCell: The cell the shark or fish moved to
	 * @param color: The identification of either a shark or a fish
	 */
	private void breed(WaTorCell waTorCell, Color color){
		breedList = findWantedNeighbors(waTorCell, Color.WHITE, Color.WHITE);
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
	
	/**
	 * Creates a random integer that selects an index in the inputted array
	 * @param moveFishList2: The list that is being referenced
	 * @return: The index of the array selected
	 */
	private int randomFinder(List<WaTorCell> moveFishList2){
		return ApplicationConstants.rand.nextInt(moveFishList2.size());
	}
	
	@Override
	void setCurrentState(String s) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updateCell(int i, int j, Cell[][] cellMatrix) {
		// TODO Auto-generated method stub
		
	}

	
}
