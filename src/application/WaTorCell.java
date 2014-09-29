//This entire class is part of my masterpiece
//DAVID ZHANG
package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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
	
	private static final int FISH_TILL_BREED_DEFAULT = 3;
	private static final int SHARK_TILL_DEATH_DEFAULT = 6;
	private static final int SHARK_TILL_BREED_DEFAULT = 6;

	private int fishBreed;
	private int sharkDeath;
	private int sharkBreed;

	private List<WaTorCell> moveFishList;
	private List<WaTorCell> eatFishList;
	private List<WaTorCell> moveSharkList;
	private List<WaTorCell> breedList;
	
	private final static Color FISH_COLOR_DEFAULT = Color.GREEN;
	private final static Color SHARK_COLOR_DEFAULT = Color.ORANGE;
	private final static Color EMPTY_COLOR_DEFAULT = Color.WHITE;
	
	public int numberOfFishTest;
	public int numberOfEmptyTest;
	private List<WaTorCell> testList;
	
	public int numberOfEmptyBeforeBreed;
	public int numberOfEmptyAfterBreed;
	private List<WaTorCell> testBreedList;
	

	/**
	 *  Constructors
	 */
	public WaTorCell() {
		this(FISH_TILL_BREED_DEFAULT, SHARK_TILL_BREED_DEFAULT, SHARK_TILL_BREED_DEFAULT);
	}
	
	public WaTorCell(int ftb, int stb, int std) {
		fishBreed = ftb;
		sharkBreed = stb;
		sharkDeath = std;
		moveFishList = new ArrayList<WaTorCell>();
		eatFishList = new ArrayList<WaTorCell>();
		moveSharkList = new ArrayList<WaTorCell>();
		breedList = new ArrayList<WaTorCell>();
		testList = new ArrayList<WaTorCell>();
		testBreedList = new ArrayList<WaTorCell>();
	}
	

	
	/**
	 * Updates the cell state
	 */
	@Override
	protected void updateCell(int i, int j, Cell[][] cellMatrix) {
		xPos = i;
		yPos = j;	
		if(updatedState == null) {
			updateThisCell();
		}
	}
	
	/**
	 * Selects the cell type and updates it
	 */
	private void updateThisCell() {
		if (currentState == SHARK_COLOR_DEFAULT){
			sharkUpdate();
		} else if (currentState == FISH_COLOR_DEFAULT) {
			fishUpdate();
		} else {
			updatedState = currentState;
		}
	}
	
	/**
	 * Have the shark eat the fish around it or move if there are not fish
	 */
	private void sharkUpdate(){	
		eatFishList = findWantedNeighbors(FISH_COLOR_DEFAULT, FISH_COLOR_DEFAULT);	
		if(eatFishList.size() != 0){
			eatFish();
		}
		else {
			moveShark();
		}
	}

	/**
	 * Find the fish neighbors and eat them
	 */
	private void eatFish(){
		for(int i = 0; i < eatFishList.size(); i++){			
			eatFishList.get(i).updatedState = EMPTY_COLOR_DEFAULT;
			numberOfEmpty();
		}
		updatedState = currentState;		
		sharkDeath = SHARK_TILL_DEATH_DEFAULT;
		sharkBreed--;			
		sharkCheck(this);
	}
	//tester
	public int numberOfFish(){
		numberOfFishTest = eatFishList.size();
		return numberOfFishTest;
	}
	//tester
	public int numberOfEmpty(){
		testList = findWantedNeighbors(EMPTY_COLOR_DEFAULT, EMPTY_COLOR_DEFAULT);
		numberOfEmptyTest = testList.size();
		return numberOfEmptyTest;
		
	}

	/**
	 * Moves the shark to a free location and then checks its breed and death statuses
	 */
	private void moveShark(){	
		moveSharkList = findWantedNeighbors(FISH_COLOR_DEFAULT, EMPTY_COLOR_DEFAULT);
		if (moveSharkList.size() != 0) {			
			moveSharkToEmpty(this);		
		}
		else {
			sharkStays(this);

		}
	}
	
	/**
	 * Moves shark to an adjacent empty cell
	 * @param cell: the cell the iteration is currently on
	 */
		
	private void moveSharkToEmpty(WaTorCell cell){
		int r = randomFinder(moveSharkList);
		cell.updatedState = EMPTY_COLOR_DEFAULT;	
		sharkDeath --;
		sharkBreed --;
		moveSharkList.get(r).updatedState = SHARK_COLOR_DEFAULT;
		moveSharkList.get(r).sharkDeath = sharkDeath;
		moveSharkList.get(r).sharkBreed = sharkBreed;
		sharkCheck(moveSharkList.get(r));
	}
	
	
	/**
	 * Shark stays. State needs to be updated.
	 * @param cell: the cell the iteration is currently on
	 */
	private void sharkStays(WaTorCell cell){
		updatedState = SHARK_COLOR_DEFAULT;
		sharkDeath--;
		sharkBreed--;
		sharkCheck(cell);		
	}

	/**
	 * Moves the fish to a free location and then checks its breed status
	 */
	private void fishUpdate(){
		moveFishList = findWantedNeighbors(EMPTY_COLOR_DEFAULT , EMPTY_COLOR_DEFAULT );
		if(moveFishList.size() != 0) {
			moveFishToEmpty(this);
		}
		else {
			fishStays(this);
		}
	}

	/**
	 * Moves fish to an adjacent empty cell
	 * @param cell: the cell the iteration is currently on
	 */
	private void moveFishToEmpty(WaTorCell cell){
		int r = randomFinder(moveFishList);	
		cell.updatedState = EMPTY_COLOR_DEFAULT;
		fishBreed--;
		moveFishList.get(r).updatedState = FISH_COLOR_DEFAULT;
		moveFishList.get(r).fishBreed = fishBreed;
		fishCheck(moveFishList.get(r));	
	}
	
	
	/**
	 * Fish stays. State needs to be updated.
	 * @param cell: the cell the iteration is currently on
	 */
	private void fishStays(WaTorCell cell){
		updatedState = FISH_COLOR_DEFAULT;
		fishBreed--;
		fishCheck(this);	
	}	
	
	/**
	 * Checks if the shark should die or breed or do nothing
	 * @param waTorCell: The cell the shark has moved to 
	 */
	private void sharkCheck(WaTorCell cell){
		if(sharkDeath == 0){
			killShark(cell);	
		}
		else if(sharkBreed == 0){
			breed(cell, SHARK_COLOR_DEFAULT);
		}
	}

	/**
	 * Checks if the fish should breed or do nothing
	 * @param waTorCell: The cell the fish has moved to
	 */
	private void fishCheck(WaTorCell cell){
		if(fishBreed == 0){
			breed(cell, FISH_COLOR_DEFAULT);
		}
	}

	/**
	 * Kills the shark if its time for him to die
	 * @param waTorCell: The cell the shark moved to 
	 */
	private void killShark(WaTorCell waTorCell){
		waTorCell.updatedState = EMPTY_COLOR_DEFAULT;
		waTorCell.sharkDeath = SHARK_TILL_DEATH_DEFAULT;
		waTorCell.sharkBreed = SHARK_TILL_BREED_DEFAULT;
	}

	/**
	 * Breed either the shark or the fish
	 * @param waTorCell: The cell the shark or fish moved to
	 * @param color: The identification of either a shark or a fish
	 */
	private void breed(WaTorCell cell, Color color){
		breedList = findWantedNeighbors(cell, EMPTY_COLOR_DEFAULT, EMPTY_COLOR_DEFAULT);
		numberOfEmptyBeforeBreed = breedList.size(); //tester
		if(breedList.size() != 0){			
			breedToEmpty(color);
			testBreedList = findWantedNeighbors(cell,EMPTY_COLOR_DEFAULT, EMPTY_COLOR_DEFAULT);
			numberOfEmptyAfterBreed = testBreedList.size(); //tester
			breedTester();
		}	
		cell.updatedState = color;
		cell.sharkBreed = SHARK_TILL_BREED_DEFAULT;	
		cell.fishBreed = FISH_TILL_BREED_DEFAULT;
	}
	//Tester
	public boolean breedTester(){		
		return numberOfEmptyBeforeBreed == numberOfEmptyAfterBreed+1;		
	}

	
	/**
	 * Breeds to an empty adjacent cell
	 * @param cell: current cell
	 * @param color: the color of the species that has to be bred
	 */
	private void breedToEmpty(Color color){		
		int r = randomFinder(breedList);			
		breedList.get(r).updatedState = color;
		breedList.get(r).sharkDeath = SHARK_TILL_DEATH_DEFAULT;
		breedList.get(r).sharkBreed = SHARK_TILL_BREED_DEFAULT;
		breedList.get(r).fishBreed = FISH_TILL_BREED_DEFAULT;	
	}
	
	
	
	/**
	 * Creates a random integer that selects an index in the inputed array
	 * @param List: The list that is being referenced
	 * @return: The index of the array selected
	 */
	private int randomFinder(List<WaTorCell> List){
		return ApplicationConstants.rand.nextInt(List.size());
	}
}