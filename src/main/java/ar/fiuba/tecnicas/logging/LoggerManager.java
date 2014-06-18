package ar.fiuba.tecnicas.logging;

import java.util.ArrayList;

import ar.fiuba.tecnicas.logging.config.ILogger;
import ar.fiuba.tecnicas.logging.config.Logger;
import ar.fiuba.tecnicas.logging.config.LoggerDefault;
import ar.fiuba.tecnicas.logging.config.PropertiesParser;
import ar.fiuba.tecnicas.logging.config.XmlParser;
import ar.fiuba.tecnicas.logging.exceptions.CouldNotReadConfigurationException;

public final class LoggerManager {
	
	private static LoggerManager uniqueInstance;
	private ArrayList<ILogger> loggers;
	
	private LoggerManager() {
	}

	public void loadConfiguration() {
		this.loggers = new ArrayList<>();
		ArrayList<Logger> loadedLoggers = new ArrayList<>();
		
		try {
			loadedLoggers = (new PropertiesParser()).load("config.properties");
		} catch (CouldNotReadConfigurationException e) {
			try {
				loadedLoggers =  (new XmlParser()).load("config.xml");
			} catch (CouldNotReadConfigurationException f) {
				this.loggers.add((ILogger)new LoggerDefault());
				return;
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
