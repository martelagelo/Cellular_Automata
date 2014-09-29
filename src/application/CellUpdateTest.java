package application;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.*;

import javafx.scene.paint.Color;

public class CellUpdateTest {

	@Test
	public void testCellUpdateMethod() {
		List<Color> colors = new ArrayList<Color>();
		colors.add(Color.BLACK);
		colors.add(Color.WHITE);
		GameOfLifeCell testCell = new GameOfLifeCell(2,2,Color.BLACK,null,0.5,"finite","square",colors);
		
		Map<Integer, Cell> neighbors = new HashMap<Integer, Cell>();
		neighbors.put(0, new GameOfLifeCell(1,2,Color.BLACK,null,0.5,"finite","square",colors));
		neighbors.put(0, new GameOfLifeCell(2,1,Color.BLACK,null,0.5,"finite","square",colors));
		neighbors.put(0, new GameOfLifeCell(2,3,Color.BLACK,null,0.5,"finite","square",colors));
		
		testCell.neighbors=neighbors;
		
		testCell.setUpdatedState();
		testCell.update();
		
		assertEquals(testCell.getCurrentState(),Color.WHITE);
	}

}
