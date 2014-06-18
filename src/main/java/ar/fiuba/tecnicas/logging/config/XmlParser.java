package ar.fiuba.tecnicas.logging.config;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ar.fiuba.tecnicas.logging.exceptions.CouldNotReadConfigurationException;
import ar.fiuba.tecnicas.logging.formatter.FormatterFactory;
import ar.fiuba.tecnicas.logging.formatter.ILogFormatter;
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
					
					String loggerName = "";
					String level = "";
					String type = "";
					String separator = "";
					String format = "";
					String outputs = "";

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
				              case "type":
				                type = content;
				                break;
				               case "separator":
					            separator = content;
					            break;
				               case "outputs":
					            outputs = content;
					            break;
				               case "format":
				            	  format = content;
				            	  break;
				            }
				        }
					}
					
					HandlerFactory handlerFactory = new HandlerFactory();
					ArrayList<IHandler> handlers = handlerFactory.createHandlers(outputs);
					
					FormatterFactory formatterFactory = new FormatterFactory();
					ILogFormatter formatter = formatterFactory.createFormatter(type, format, separator);
					
					Logger logger = new Logger(LogLevel.valueOf(level), formatter, handlers, loggerName);
					loggerList.add(logger);
				}
			}
		} catch (Exception e) {
			throw new CouldNotReadConfigurationException();
		}
		
		return loggerList;
	}

}
