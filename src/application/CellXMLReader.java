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
	public String myFilename = "src/application/xml/GridSample.xml";
	
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
		myNodeList = myDocument.getDocumentElement().getChildNodes();
		
		myModelType = myNodeList.item(0).getParentNode().getNodeName();
		
		// Loop through Node List to get elements
		for(int i=0; i<myNodeList.getLength();i++) {
			Node node = myNodeList.item(i);
			
			if(node instanceof Element) {
				Cell cell = checkModelTypeAndInitializeCell();
				NodeList childNodes = node.getChildNodes();
				
				for(int j=0; j<childNodes.getLength(); j++) {
					Node cNode = childNodes.item(j);
					// Identify child tag of cell encountered
					if(cNode instanceof Element)
						loadAttributeIntoCell(cNode.getLastChild().getTextContent().trim(), cell);
				}
				myCellList.add(cell);
			}
		}
		printCellList(myCellList);
	}
	
	public Cell checkModelTypeAndInitializeCell() {
		Cell cell = new GameOfLifeCell();
		switch (myModelType) {
		case "GameOfLifeCell":
			cell = new GameOfLifeCell();
			break;
		case "SegregationCell":
			cell = new SegregationCell();
			break;
		case "FireCell":
			cell = new FireCell();
			break;
		case "WaTorCell":
			cell = new WaTorCell();
			break;
		default:
			cell = new GameOfLifeCell();
			break;
		}
		return cell;
	}
	
	public void loadAttributeIntoCell(String attribute, Cell cell) {
		switch(attribute) {
			// Standard attributes for all cells
			case "xPos":
				cell.setXPos(Integer.parseInt(attribute));
				break;
			case "yPos":
				cell.setYPos(Integer.parseInt(attribute));
				break;
			case "state":
				cell.setCurrentState(attribute);
				break;
			// Segregation threshold
			case "threshold":
				((SegregationCell) cell).setThreshold(Integer.parseInt(attribute));
		}
	}
	
	public void printCellList(List<Cell> cellList) {
		for(Cell cell: cellList)
			System.out.println(cell);
	}
}
