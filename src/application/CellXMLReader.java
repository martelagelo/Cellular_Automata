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
	
	public CellXMLReader() {
		myCellList = new ArrayList<Cell>();
	}
	
	public Document getDocument() {
		return myDocument;
	}
	
	public List<Cell> getCellList() {
		return myCellList;
	}
	
	public void loadAndParseXMLFile(String filename) throws ParserConfigurationException, IOException, SAXException {
		// Get DOM builder from DOM builder factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		// Load and parse XML document, which is now stored as a Tree
		// TODO: switch myFilename for filename once functional
		Document doc = (Document) db.parse(new File(filename));
		myDocument = doc;
	}
	
	public void populateCellListFromDocument() {
		// Do not run method if myDocument has not been populated
		if(myDocument == null)
			return;
				
		// Initialize list variables for parsing elements
		myNodeList = myDocument.getElementsByTagName("cell");

        NodeList applicationDetails = myDocument.getElementsByTagName("ApplicationDetails");
        Element modelParameters = (Element) applicationDetails.item(0);
		myModelType = modelParameters.getElementsByTagName("ModelType").item(0).getTextContent();
		//ApplicationConstants.NUM_OF_ROWS = Integer.parseInt(modelParameters.getElementsByTagName("Rows").item(0).getTextContent());
		//ApplicationConstants.NUM_OF_COLUMNS = Integer.parseInt(modelParameters.getElementsByTagName("Cols").item(0).getTextContent());
		System.out.println("Model: " + myModelType + "\tRows: " + ApplicationConstants.NUM_OF_ROWS + "\tCols: " + ApplicationConstants.NUM_OF_COLUMNS);
				
		// Loop through Node List to get nodes (cells)
		for(int i=0; i<myNodeList.getLength();i++) {
			Node node = myNodeList.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) { // better than testing if instanceof element
				Cell cell = checkModelTypeAndInitializeCell();
	    		loadAttributesIntoCell(cell, node);
				myCellList.add(cell);
				System.out.println(cell);
			}
		}
		printCellList(myCellList);
	}
	
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
	
	public void loadAttributesIntoCell(Cell cell, Node node) {
		//System.out.println("New Cell:: ");
		Element element = (Element) node;
		cell.setXPos(Integer.parseInt(element.getElementsByTagName("xPos").item(0).getTextContent()));
		//System.out.println("\txPos: " + cell.xPos);
		cell.setYPos(Integer.parseInt(element.getElementsByTagName("yPos").item(0).getTextContent()));
		//System.out.println("\tyPos: " + cell.yPos);
		cell.setCurrentState(element.getElementsByTagName("state").item(0).getTextContent());
		//System.out.println("\t\tstate: " + cell.currentState);
		// Segregation threshold
		if(myModelType.equalsIgnoreCase("threshold"))
			((SegregationCell) cell).setThreshold(Double.parseDouble(element.getElementsByTagName("threshold").item(0).getTextContent()));
	}
	
	public void printCellList(List<Cell> cellList) {
		for(Cell cell: cellList)
			System.out.println(cell);
	}
	
	//helper function to pull int from the XML file
	public int getIntegerFromCellBasedOnTagName(String tagName, Element cell){
		return Integer.parseInt(cell.getElementsByTagName(tagName).item(0).getTextContent());
	}  

}