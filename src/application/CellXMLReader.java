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
	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
		Document d = getAndLoadXMLFile("src/application/xml/GridSample.xml");
		NodeList nl = getNodeListFromDocument(d);
	}
	
	public static Document getAndLoadXMLFile(String filename) throws ParserConfigurationException, IOException, SAXException {
		// Get DOM builder from DOM builder factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		// Load and parse XML document, which is now stored as a Tree
		Document doc = (Document) db.parse(new File(filename));
		return doc;
	}
	
	public static NodeList getNodeListFromDocument(Document document) {
		// Initialize list variables for parsing elements
		List<Cell> cellList = new ArrayList<>();
		NodeList nodeList = document.getDocumentElement().getChildNodes();
		
		// Loop through Node List to get elements
		for(int i=0; i<nodeList.getLength();i++) {
			Node node = nodeList.item(i);
			
			if(node instanceof Element) {
				Cell cell = checkModelTypeAndInitializeCell(node.getParentNode().getNodeName());
				NodeList childNodes = node.getChildNodes();
				
				for(int j=0; j<childNodes.getLength(); j++) {
					Node cNode = childNodes.item(j);
					// Identify child tag of cell encountered
					if(cNode instanceof Element)
						loadAttributeIntoCell(cNode.getLastChild().getTextContent().trim(), cell);
				}
				cellList.add(cell);
			}
		}
		for(Cell cell: cellList) {
			System.out.println(cell);
		}
		return nodeList;
	}
	
	public static Cell checkModelTypeAndInitializeCell(String ModelType) {
		Cell cell = new GameOfLifeCell();
		switch (ModelType) {
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
	
	public static void loadAttributeIntoCell(String attribute, Cell cell) {
		switch(attribute) {
			// Standard attributes for all cells
			case "xPos":
				cell.setXPos(Integer.parseInt(content));
				break;
			case "yPos":
				cell.setYPos(Integer.parseInt(content));
				break;
			case "state":
				cell.setCurrentState(content);
				break;
			// Segregation threshold
			case "threshold":
				((SegregationCell) cell).setThreshold(Integer.parseInt(content));
		}
	}
}
