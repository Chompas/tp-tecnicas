package ar.fiuba.tecnicas.logging;

import java.util.ArrayList;

import ar.fiuba.tecnicas.logging.config.LoggerDefault;
import ar.fiuba.tecnicas.logging.config.PropertiesParser;
import ar.fiuba.tecnicas.logging.config.XmlParser;
import ar.fiuba.tecnicas.logging.exceptions.CouldNotReadConfigurationException;

public final class LoggerManager {
	
	private static LoggerManager uniqueInstance;
	private ArrayList<ILogger> loggers;
	
	private LoggerManager() {
		this.loggers = new ArrayList<>();
		ArrayList<Logger> loadedLoggers = new ArrayList<>();
		
		try {
			loadedLoggers = (new PropertiesParser()).load("config.properties");
		} catch (CouldNotReadConfigurationException e) {
			try {
				loadedLoggers =  (new XmlParser()).load("config.xml");				
			} catch (CouldNotReadConfigurationException f) {
				this.loggers.add(new LoggerDefault());
			}
		} 
		
		addLoggers(loadedLoggers);
	}

	public static LoggerManager getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new LoggerManager();
		}
		return uniqueInstance;		
	}
	
	public ILogger getLogger(String name) {
		if (name.equals("")) {
			return null;
		}
		for (ILogger logger : this.loggers) {
			if (logger.getName().equals(name)) {
				return logger;
			}
		}
		return null;
	}
	
	private void addLoggers(ArrayList<Logger> loadedLoggers) {
		for (Logger logger : loadedLoggers) {
			this.loggers.add((ILogger)logger);
		}
	}
}
