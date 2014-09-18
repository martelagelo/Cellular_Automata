package application;

import java.io.IOException;
import java.util.*;

import javax.xml.parsers.*;

import javafx.scene.paint.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class CellXMLReader {
	
	public static void main(String[] args) {
		Document d = getAndLoadXMLFile("xml/GridSample.xml");
		NodeList nl = getNodeListFromDocument(d);
		
	}
	
	public static Document getAndLoadXMLFile(String filename) throws ParserConfigurationException, IOException, SAXException {
		// Get DOM builder from DOM builder factory
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		//Load and parse XML document, which is now stored as a Tree
		Document document = (Document) builder.parse(ClassLoader.getSystemResourceAsStream(filename));
		return document;
	}
	
	public static NodeList getNodeListFromDocument(Document document) {
		List<Cell> cellList = new ArrayList<>();
		
		NodeList nodeList = document.getDocumentElement().getChildNodes();
		
		for(int i=0; i<nodeList.getLength();i++) {
			Node node = nodeList.item(i);
			if(node instanceof Element) {
				Cell cell;
				if(node instanceof Segregation) {
					cell = new Segregation();
				}
				NodeList childNodes = node.getChildNodes();
				for(int j=0; j<childNodes.getLength(); j++) {
					Node cNode = childNodes.item(j);
					
					//Identifying child tag of cell encountered
					if(cNode instanceof Element) {
						String content = cNode.getLastChild().getTextContent().trim();
						switch(cNode.getNodeName()) {
							case "xPos":
								cell.setXPos(Integer.parseInt(content));
								break;
							case "yPos":
								cell.setYPos(Integer.parseInt(content));
								break;
							case "state":
								cell.setCurrentState(content);
								break;
						}
					}
				}
				cellList.add(cell);
			}
				
		}
		
		return nodeList;
	}
}
