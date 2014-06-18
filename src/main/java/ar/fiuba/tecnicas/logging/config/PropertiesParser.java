package ar.fiuba.tecnicas.logging.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import ar.fiuba.tecnicas.logging.exceptions.CouldNotReadConfigurationException;
import ar.fiuba.tecnicas.logging.formatter.FormatterFactory;
import ar.fiuba.tecnicas.logging.formatter.ILogFormatter;
import ar.fiuba.tecnicas.logging.handlers.HandlerFactory;
import ar.fiuba.tecnicas.logging.handlers.IHandler;

public class PropertiesParser {

	public ArrayList<Logger> load(String fileName) throws CouldNotReadConfigurationException {
		Properties properties = new Properties();
		ArrayList<Logger> loggerList = new ArrayList<>();
	
		try {
			InputStream file = new FileInputStream(fileName);
			properties.load(file);
			file.close();
			
			LogLevel level = LogLevel.valueOf(properties.getProperty("LogLevel"));
			String format = properties.getProperty("Format");
			String name = properties.getProperty("Name");
			String separator = properties.getProperty("Separator");
			
			HandlerFactory handlerFactory = new HandlerFactory();
			ArrayList<IHandler> handlers = handlerFactory.createHandlers(properties.getProperty("Outputs"));
			
			FormatterFactory formatterFactory = new FormatterFactory();
			ILogFormatter formatter = formatterFactory.createFormatter("text", format, separator);
			
			Logger logger = new Logger(level, formatter, handlers, name);
			loggerList.add(logger);
			
		} catch (IOException e) {
			throw new CouldNotReadConfigurationException();
		}
		
		return loggerList;
	}

}
