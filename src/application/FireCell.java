package application;

import javafx.scene.paint.Color;

public class FireCell extends Cell {

	private Boolean isNextToFire = false;
	private double burningChance = 0;

	public void updateCell(int column, int row, Cell[][] grid) {
		super.Matrix = grid;
		super.xPos = column;
		super.yPos = row;
		findFireNeighbors();
		calculateBurnChance();
	}

	private void findFireNeighbors(){
		if (super.Matrix[xPos + 1][yPos].currentState == Color.RED) {
			isNextToFire = true;
		}
	}

	private void calculateBurnChance(){
		if (isNextToFire) {
			if (super.currentState == Color.GREEN) {
				burningChance = .7;
			} else if (super.currentState == Color.YELLOW) {
				burningChance = .3;
			} else {
				burningChance = .8;
			}
		} else {
			if (super.currentState == Color.RED) {
				burningChance = .7;
			} else {
				burningChance = 0.0;
			}
		}

	}

}
