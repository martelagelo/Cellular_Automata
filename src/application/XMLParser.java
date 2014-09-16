package application;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLParser {


	public XMLParser(){
		//Get the DOM Builder Factory
		DocumentBuilderFactory factory =
				DocumentBuilderFactory.newInstance();
		//Get the DOM Builder
		DocumentBuilder builder = factory.newDocumentBuilder();

		//Load and Parse the XML document
		//document contains the complete XML as a Tree.
		Document document =
				builder.parse(
						ClassLoader.getSystemResourceAsStream("xml/firespreader.xml"));
	}


}
