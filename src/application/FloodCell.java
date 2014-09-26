package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class FloodCell extends Cell{

	private boolean OKToFlood = false;
	private List<WaTorCell> neighborsList = new ArrayList<WaTorCell>();
	
	@Override
	protected void updateCell(int i, int j) {
		OKToFlood = false;
		xPos = i;
		yPos = j;	
		update();
	}
	
	private void update() {
		if (findWantedNeighbors(Color.BLACK).size() != 0) {
			OKToFlood = true;
		}
		setUpdatedState();
	}
	
	private void setUpdatedState() {
		if (OKToFlood) {
			updatedState = Color.BLACK;
		}
		else {
			updatedState = currentState;
		}
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
