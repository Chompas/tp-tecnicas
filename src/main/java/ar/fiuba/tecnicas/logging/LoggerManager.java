package ar.fiuba.tecnicas.logging;

import java.util.ArrayList;

public final class LoggerManager {
	
	private static LoggerManager uniqueInstance;
	private ArrayList<Logger> loggers;
	
	private LoggerManager() {
		this.loggers = new ArrayList<>();
	}
	
	public static LoggerManager getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new LoggerManager();
		}
		return uniqueInstance;		
	}
	
	public Logger getLogger(String name) {
		if (name == "") {
			return null;
		}
		for (Logger logger : this.loggers) {
			if (logger.getName() == name) {
				return logger;
			}
		}
		return null;
	}

	public boolean addLogger(Logger logger) {
		if (loggerWithNameExists(logger.getName())) {
			return false;
		}
		this.loggers.add(logger);
		return true;
	}
	
	public boolean deleteLogger(String name) {
		for (int i = 0; i < this.loggers.size(); i++) {
			if (this.loggers.get(i).getName() == name) {
				this.loggers.remove(i);
				return true;
			}
		}
		return false;
	}
	
	private boolean loggerWithNameExists(String name) {
		for (Logger logger : loggers) {
			if (logger.getName() == name) {
				return true;
			}
		}
		return false;
	}

}
