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
	public ArrayList<Color> myColors;
	public int myRows;
	public int myCols;
	
	/**
	 * 
	 */
	public CellXMLReader() {
		myCellList = new ArrayList<Cell>();
		myRows = 0;
		myCols = 0;
		myColors = new ArrayList<Color>();
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
		// TODO: switch myFilename for filename once functional
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
		for(int i=0; i<myNodeList.getLength();i++) {
			Node node = myNodeList.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) // better than testing if instanceof element
				loadAttributesIntoCell((Element) node);
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
        setColorScheme(modelParameters.getElementsByTagName("Colors").item(0).getTextContent().toLowerCase());
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
			cell = new WaTorCell();
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
	public void loadAttributesIntoCell(Element element) {
		if(element.getElementsByTagName("Row").getLength()<=0)
			return;
		String statesToParse = element.getElementsByTagName("Row").item(0).getTextContent();
		if(myCols<statesToParse.length())
			myCols = statesToParse.length();
		for(int i=0; i<statesToParse.length(); i++) {
			Cell cell = checkModelTypeAndInitializeCell();
			int colorIndex = Integer.parseInt(statesToParse.substring(i,i+1));
			if(colorIndex > myColors.size())
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
		for(Cell cell: cellList)
			System.out.println(cell);
	}
}