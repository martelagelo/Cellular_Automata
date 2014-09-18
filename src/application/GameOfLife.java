package application;

import javafx.scene.paint.Color;

public class GameOfLife extends Cell {

	private int Alive;
	
	@Override
	public void updateCell(int i, int j, Cell[][] cellMatrix) {
		for(int x=0; x<ApplicationConstants.NUM_OF_COLUMNS; x++){
			for(int y=0; y<ApplicationConstants.NUM_OF_ROWS; y++){
				xPos = x;
				yPos = y;
				
				lifeUpdate(aliveCalculator());					
			}
		}		
	}

	


	private int aliveCalculator(){
		for(int i = xPos-1; i<=xPos+1; i++){
			for(int j = yPos-1; j<=yPos+1; j++){
				while( i!= xPos && j!=yPos){					

					if(Matrix[i][j].currentState == Color.BLACK){
						Alive++;
					}					
				}				
			}
		}
		return Alive;
	}
	
	private void lifeUpdate(int count){
		if(Matrix[xPos][yPos].currentState == Color.BLACK){
			
			if(count<2){
				Matrix[xPos][yPos].updatedState = Color.WHITE;
			}
			if(count==2 || count==3){
				Matrix[xPos][yPos].updatedState = Color.BLACK;
			}
			if(count > 3){
				Matrix[xPos][yPos].updatedState = Color.WHITE;
			}
			
		}
		
		if(Matrix[xPos][yPos].currentState == Color.WHITE){
			if(count==3){
				Matrix[xPos][yPos].updatedState = Color.BLACK;
			}else{
				Matrix[xPos][yPos].updatedState = Color.WHITE;
			}
		}
	}


	@Override
	void setCurrentState(String s) {
		// TODO Auto-generated method stub
		
	}
	
	


}
