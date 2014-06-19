package ar.fiuba.tecnicas.logging;

import java.util.ArrayList;
import java.util.Date;

import ar.fiuba.tecnicas.logging.config.ILogger;
import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.config.Logger;
import ar.fiuba.tecnicas.logging.config.LoggerDefault;
import ar.fiuba.tecnicas.logging.config.PropertiesParser;
import ar.fiuba.tecnicas.logging.config.XmlParser;
import ar.fiuba.tecnicas.logging.exceptions.CouldNotReadConfigurationException;

public final class LoggerManager {
	
	private static LoggerManager uniqueInstance;
	private ArrayList<ILogger> loggers;
	
	public void loadConfiguration() {
		this.loggers.clear();
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
	
	public void logAll(Date date, String message, LogLevel level) {
		for (ILogger logger : this.loggers) {
			logger.log(date, message, level);
		}
	}
	
	public void logAll(Date date, String message, LogLevel level, Throwable e) {
		for (ILogger logger : this.loggers) {
			logger.log(date, message, level, e);
		}
	}
	
	public ILogger getLogger(String name) {
		for (ILogger logger : this.loggers) {
			if (logger.getName().equals(name)) {
				return logger;
			}
		}
		return null;
	}
		
	private LoggerManager() {
		this.loggers = new ArrayList<>();
	}
	
	private void addLoggers(ArrayList<Logger> loadedLoggers) {
		for (Logger logger : loadedLoggers) {
			this.loggers.add((ILogger)logger);
		}
	}
}
