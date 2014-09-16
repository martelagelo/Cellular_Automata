package application;

public class Segregation extends Cell {

	private int same;
	private int different;
	private int percentage;
	private int threshold;
	



	for(int x=0; x<ApplicationConstants.NUM_OF_COLUMNS; x++){
		for(int y=0; y<ApplicationConstants.NUM_OF_ROWS; y++){



			for(int i=x-1; i<=x+1; i++){
				for(int j= y-1; j<=y+1; j++){

					if(Matrix[i][j]==Matrix[x][y]){
						
						same++;

					}				
					
					

				}
			}




		}			
	}







}
