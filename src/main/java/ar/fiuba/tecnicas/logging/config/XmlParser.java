package ar.fiuba.tecnicas.logging.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ar.fiuba.tecnicas.logging.Logger;
import ar.fiuba.tecnicas.logging.exceptions.CouldNotReadConfigurationException;
import ar.fiuba.tecnicas.logging.handlers.HandlerFactory;
import ar.fiuba.tecnicas.logging.handlers.IHandler;

public class XmlParser {

	public ArrayList<Logger> load(String filename) throws CouldNotReadConfigurationException {
		
		ArrayList<Logger> loggerList = new ArrayList<>();

		try {
			File file = new File(filename);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);
			
			NodeList nodeList = document.getDocumentElement().getChildNodes();

			for (int i = 0; i < nodeList.getLength(); i++) {

				Node node = nodeList.item(i);
			    
				if (node instanceof Element) {
			  
					NodeList childNodes = node.getChildNodes();
					
					String loggerName = null;
					String level = null;
					String format = null;
					String separator = null;
					String outputs = null;

					for (int j = 0; j < childNodes.getLength(); j++) {
						Node cNode = childNodes.item(j);
						
				        if (cNode instanceof Element) {
				            String content = cNode.getLastChild().getTextContent().trim();
				            switch (cNode.getNodeName()) {
				              case "name":
				            	loggerName = content;
				                break;
				              case "level":
				                level = content;
				                break;
				              case "format":
					                format = content;
					                break;
				              case "separator":
					                separator = content;
					                break;
				              case "outputs":
					                outputs = content;
					                break;
				            }
				        }
					}

					System.out.println("Name: "  + loggerName);
					System.out.println("Level: " + level);
					System.out.println("Format: " + format);
					System.out.println("Separator: " + separator);
					System.out.println("Outputs: " + outputs);
					System.out.println("-------------");
					
					HandlerFactory handlerFactory = new HandlerFactory();
					List<IHandler> handlers = handlerFactory.createHandlers(outputs);
					
					Logger logger = new Logger(level, null, handlers, loggerName);
					loggerList.add(logger);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return loggerList;
	}

}
