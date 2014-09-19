package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class WaTorCell2 extends Cell{

	private int fishTimeTillBreedingParameter = 3;
	private int sharkTimeTillDeathParameter = 3;
	private int sharkTimeTillBreedingParameter = 3;

	private int fishTimeTillBreeding = fishTimeTillBreedingParameter;
	private int sharkTimeTillDeath = sharkTimeTillDeathParameter;
	private int sharkTimeTillBreeding = sharkTimeTillBreedingParameter;

	@Override
	public void updateCell(int i, int j, Cell[][] cellMatrix) {
		xPos = i;
		yPos = j;
		Matrix = cellMatrix;
		updateState();
	}

	private void updateState(){
		//System.out.println("Yeah");
		if (updatedState == null) {
			//System.out.println("Made it in");
			if (currentState == Color.ORANGE) {
				updateShark();
			} else if (currentState == Color.GREEN) {
				updateFish();
			} else {
				updatedState = currentState;
			}
		}
	}

	private void updateShark() {
		ArrayList<WaTorCell2> fishNeighbors = findNeighbors(xPos, yPos, Color.GREEN);
		if (fishNeighbors.size() != 0) {
			System.out.println(fishNeighbors.size());
			sharkTimeTillDeath = sharkTimeTillDeathParameter;
			fishNeighbors.get(new Random().nextInt(fishNeighbors.size())).updatedState = Color.BLUE;
			updatedState = currentState;
			checkTimeTillSharkEvents(this);
		} else {
			moveShark();
		}
	}

	private void moveShark() {
		ArrayList<WaTorCell2> emptyNeighbors = findNeighbors(xPos, yPos, Color.BLUE);
		System.out.println(emptyNeighbors.size());
		if (emptyNeighbors.size() != 0) {
			emptyNeighbors.get(new Random().nextInt(emptyNeighbors.size())).updatedState = Color.ORANGE;
			populateOtherCell(emptyNeighbors.get(new Random().nextInt(emptyNeighbors.size())));
			updatedState = Color.BLUE;
			checkTimeTillSharkEvents(emptyNeighbors.get(new Random().nextInt(emptyNeighbors.size())));
		}
		else {
			checkTimeTillSharkEvents(this);
		}
	}

	private void checkTimeTillSharkEvents(WaTorCell2 cell){
		if (cell.sharkTimeTillDeath == 0) {
			killShark(cell);
		} else if (cell.sharkTimeTillBreeding == 0){
			cell.sharkTimeTillBreeding = sharkTimeTillBreedingParameter;
			breed(cell, Color.ORANGE);
		} else {
			cell.sharkTimeTillBreeding--;
		}
	}

	private void killShark(WaTorCell2 cell) {
		cell.updatedState = Color.BLUE;
		cell.sharkTimeTillDeath = sharkTimeTillDeathParameter;
		cell.sharkTimeTillBreeding = sharkTimeTillBreedingParameter;
	}

	private void updateFish() {
		ArrayList<WaTorCell2> freeList = findNeighbors(xPos, yPos, Color.BLUE);
		if (freeList.size() != 0) {
			freeList.get(new Random().nextInt(freeList.size())).updatedState = currentState;
			populateOtherCell(freeList.get(new Random().nextInt(freeList.size())));
			updatedState = Color.BLUE;
			checkTimeTillFishBreeding(freeList.get(new Random().nextInt(freeList.size())));
		} else {
			updatedState = currentState;
		}
	}

	private void checkTimeTillFishBreeding(WaTorCell2 cell) {
		if (cell.fishTimeTillBreeding == 0) {
			cell.fishTimeTillBreeding = fishTimeTillBreedingParameter;
			breed(cell, Color.GREEN);
		} else {
			cell.fishTimeTillBreeding--;
		}
	}

	private void breed(WaTorCell2 cell, Color color) {
		ArrayList<WaTorCell2> freeList = findNeighbors(cell.xPos, cell.yPos, Color.BLUE);
		if (freeList.size() != 0) {
			freeList.get(new Random().nextInt(freeList.size())).updatedState = color;
			initializeNewCell(freeList.get(new Random().nextInt(freeList.size())));
		}
	}

	private ArrayList findNeighbors(int i, int j, Color color) {

		ArrayList<WaTorCell2> list = new ArrayList<WaTorCell2>();

		if (checkBounds(i + 1,j)) {
			if (Matrix[i + 1][j].currentState == color) {
				list.add((WaTorCell2) Matrix[i + 1][j]);
			}
		} else if (checkBounds(i - 1,j)) {
			if(Matrix[i - 1][j].currentState == color) {
				list.add((WaTorCell2) Matrix[i - 1][j]);
			}
		} else if (checkBounds(i,j + 1)) {
			if (Matrix[i][j + 1].currentState == color) {
				list.add((WaTorCell2) Matrix[i][j + 1]);
			}
		} else if (checkBounds(i,j - 1)) {
			if (Matrix[i][j - 1].currentState == color) {
				list.add((WaTorCell2) Matrix[i][j - 1]);
			}
		} 

		return list;
	}

	private boolean checkBounds(int i, int j){
		return (i < ApplicationConstants.NUM_OF_COLUMNS && i >= 0 && j < ApplicationConstants.NUM_OF_ROWS && j >= 0);
	}

	private void initializeNewCell(WaTorCell2 cell) {
		cell.fishTimeTillBreeding = this.fishTimeTillBreedingParameter;
		cell.sharkTimeTillBreeding = this.sharkTimeTillBreedingParameter;
		cell.sharkTimeTillDeath = this.sharkTimeTillDeathParameter;
	}

	private void populateOtherCell(WaTorCell2 cell) {
		cell.fishTimeTillBreeding = fishTimeTillBreeding;
		cell.sharkTimeTillBreeding = sharkTimeTillBreeding;
		cell.sharkTimeTillDeath = sharkTimeTillDeath;
	}

	@Override
	void setCurrentState(String s) {
		// TODO Auto-generated method stub

	}

}
