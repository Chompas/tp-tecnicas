package ar.fiuba.tecnicas.logging.config;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

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
					HashMap<String,String> customOutputs = new HashMap<>();

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
				               case "customOutputs":
				            	   NodeList customOutputsNodes = cNode.getChildNodes();
				            	   String implementor = "";
				            	   String params = "";
				            	   for (int k = 0; k < customOutputsNodes.getLength(); k++) {
				            		   Node customNode = customOutputsNodes.item(k);
				            		   if (customNode instanceof Element) {
				            			    String customContent = customNode.getLastChild().getTextContent().trim();
								            switch (customNode.getNodeName()) {
									            case "implementor":
									            	implementor = customContent;
									            	break;
									            default:
									            	params+=customContent+",";
									            	break;
								            }
				            		   }
				            	   }
				            	   customOutputs.put(implementor, params.substring(0, params.length()-1));
				            	   break;
				            }
				        }
					}
					
					HandlerFactory handlerFactory = new HandlerFactory();
					ArrayList<IHandler> handlers = handlerFactory.createHandlers(outputs);
					
					ArrayList<IHandler> customHandlers = handlerFactory.createCustomHandlers(customOutputs);
					
					if(customHandlers.size() > 0) {
						handlers.addAll(customHandlers);
					}
					
					FormatterFactory formatterFactory = new FormatterFactory();
					ILogFormatter formatter = formatterFactory.createFormatter(type, format, separator);
					
					Logger logger = new Logger(LogLevel.valueOf(level), formatter, handlers, loggerName);
					loggerList.add(logger);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CouldNotReadConfigurationException();
		}
		
		return loggerList;
	}

}
