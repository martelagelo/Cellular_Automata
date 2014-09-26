package application;
import java.io.IOException;
import java.io.File;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javafx.scene.paint.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class CellXMLReader
{	
	public Document myDocument;
	public NodeList myNodeList;
	public List<Cell> myCellList;
	public String myModelType;
	public double myThreshold;
	public String myGridEdgeType;
	public String myGridLocationShape;
	public String myGridNeighborType;
	public ArrayList<Color> myColors;
	public ArrayList<Integer> myWaTorVariables;
	public int myRows;
	public int myCols;
	
	/**
	 * 
	 */
	public CellXMLReader() {
		myRows = 0;
		myCols = 0;
		myCellList = new ArrayList<Cell>();
		myColors = new ArrayList<Color>();
		myWaTorVariables = new ArrayList<Integer>();
	}
	
	/**
	 * 
	 * @return
	 */
	public Document getDocument() {
		return myDocument;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Cell> getCellList() {
		return myCellList;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getModelType() {
		return myModelType;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Color> getAvailableColors() {
		return myColors;
	}
	/**
	 * 
	 * @return
	 */
	public String getGridLocationShape() {
		return myGridLocationShape;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getGridEdgeType() {
		return myGridEdgeType;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getGridNeighborType() {
		return myGridNeighborType;
	}
	
	/**
	 * 
	 * @param filename
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public void loadAndParseXMLFile(String filename) throws ParserConfigurationException, IOException, SAXException {
		// Get DOM builder from DOM builder factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		// Load and parse XML document, which is now stored as a Tree
		Document doc = (Document) db.parse(new File(filename));
		myDocument = doc;
	}
	
	/**
	 * 
	 */
	public void populateCellListFromDocument() {
		// Do not run method if myDocument has not been populated
		if(myDocument == null)
			return;
		
		// Initialize list variables for parsing elements
		myNodeList = myDocument.getElementsByTagName("cell");
		
		// Find the application details tag and then configure model parameters with enclosed info
        NodeList applicationDetails = myDocument.getElementsByTagName("ApplicationDetails");
        configureModelParameters((Element) applicationDetails.item(0));
		
		//System.out.println("Model: " + myModelType + "\tRows: " + ApplicationConstants.NUM_OF_ROWS + "\tCols: " + ApplicationConstants.NUM_OF_COLUMNS);
		
		// Loop through Node List to get nodes (cells)
        NodeList cellDetails = myDocument.getElementsByTagName("Cells");
        System.out.println("Looping through cell data in XML file... ");
        System.out.println("Cell Details: " + cellDetails);
        System.out.println("Cell Details item 0: " + cellDetails.item(0));
        System.out.println("Cell Details item 0 text: " + cellDetails.item(0).getTextContent());

        String[] rowOfStates = cellDetails.item(0).getTextContent().split("\n");
//        for(int i=0; i<rowOfStates.length; i++)
//        	System.out.println("Row " + i + " length " + rowOfStates[i].length() + " : " + rowOfStates[i]);
        for(String s: rowOfStates) {
        	System.out.println("Going for it...");
        	if(s.length() <= 0 || s.compareTo("\n") == 0 || s.compareTo(" ") == 0 || s.compareTo("\t") == 0)
        		continue;
        	else
        		loadAttributesIntoCell(s);
        }
		printCellList(myCellList);
	}
	
	/**
	 * 
	 * @param modelParameters
	 */
	public void configureModelParameters(Element modelParameters) {
		// TODO: Error Checking
		myModelType = modelParameters.getElementsByTagName("ModelType").item(0).getTextContent();
		myGridEdgeType = modelParameters.getElementsByTagName("GridEdgeType").item(0).getTextContent();
		myGridLocationShape = modelParameters.getElementsByTagName("GridLocationShape").item(0).getTextContent();
		myGridNeighborType = modelParameters.getElementsByTagName("GridNeighborType").item(0).getTextContent();
        setColorScheme(modelParameters.getElementsByTagName("Colors").item(0).getTextContent().toLowerCase());
        //setWaTorVariables(modelParameters.getElementsByTagName("WaTorVariables"));
        if(myModelType.equalsIgnoreCase("wator"))
        	setWaTorVariables(modelParameters);
		//ApplicationConstants.NUM_OF_ROWS = Integer.parseInt(modelParameters.getElementsByTagName("NumRows").item(0).getTextContent());
		//ApplicationConstants.NUM_OF_COLUMNS = Integer.parseInt(modelParameters.getElementsByTagName("NumCols").item(0).getTextContent());
	}
	
	/**
	 * 
	 * @param s
	 */
	public void setColorScheme(String s) {
		for(char ch: s.toCharArray())
			switch (ch) {
			case 'w':
				myColors.add(Color.WHITE);
				break;
			case 'k':
				myColors.add(Color.BLACK);
				break;
			case 'b':
				myColors.add(Color.BLUE);
				break;
			case 'r':
				myColors.add(Color.RED);
				break;
			case 'g':
				myColors.add(Color.GREEN);
				break;
			case 'o':
				myColors.add(Color.ORANGE);
				break;
			case 'y':
				myColors.add(Color.YELLOW);
				break;
			}
	}
	
	public void setWaTorVariables(Element watorModelParameters) {
		if(watorModelParameters == null)
			return;
		myWaTorVariables.add(0,Integer.parseInt(watorModelParameters.getElementsByTagName("FishTillBreed").item(0).getTextContent()));
		myWaTorVariables.add(1,Integer.parseInt(watorModelParameters.getElementsByTagName("SharkTillBreed").item(0).getTextContent()));
        myWaTorVariables.add(2,Integer.parseInt(watorModelParameters.getElementsByTagName("SharkTillDeath").item(0).getTextContent()));
	}
	
	/**
	 * 
	 * @return
	 */
	public Cell checkModelTypeAndInitializeCell() {
		Cell cell = new GameOfLifeCell();
		switch (myModelType.toLowerCase()) {
		case "gameoflife":
			cell = new GameOfLifeCell();
			break;
		case "segregation":
			cell = new SegregationCell();
			break;
		case "fire":
			cell = new FireCell();
			break;
		case "wator":
			if(myWaTorVariables.size()<=0)
				cell = new WaTorCell();
			cell = new WaTorCell(myWaTorVariables.get(0),myWaTorVariables.get(1),myWaTorVariables.get(2));
			break;
		default:
			cell = new GameOfLifeCell();
			break;
		}
		return cell;
	}
	
	/**
	 * 
	 * @param element
	 */
	public void loadAttributesIntoCell(String statesToParse) {
		//System.out.println(statesToParse);
		statesToParse = statesToParse.substring(2);
		if(myCols<statesToParse.length())
			myCols = statesToParse.length();
		for(int i=0; i<statesToParse.length(); i++) {
			Cell cell = checkModelTypeAndInitializeCell();
			System.out.println("\tSubstring check:i " + i + "," + statesToParse.substring(i,i+1));
			int colorIndex = Integer.parseInt(statesToParse.substring(i,i+1));
			if(colorIndex >= myColors.size())
				colorIndex = 0;
			cell.setCurrentState(myColors.get(colorIndex));
			cell.setThreshold(myThreshold);
			cell.setXPos(i);
			cell.setYPos(myRows);
			myCellList.add(cell);
		}
		myRows++;
	}
	
	/**
	 * 
	 * @param cellList
	 */
	public void printCellList(List<Cell> cellList) {
		System.out.println("Cell List: ");
		System.out.println("\tRows: " + myRows);
		System.out.println("\tCols: " + myCols);
		System.out.println("\tSize: " + cellList.size());
		for(Cell cell: cellList)
			System.out.println("\t" + cell);
		
	}
}