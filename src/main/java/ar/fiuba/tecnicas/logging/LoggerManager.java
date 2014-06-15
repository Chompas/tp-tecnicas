package ar.fiuba.tecnicas.logging;

import java.util.ArrayList;

public final class LoggerManager {
	
	private static LoggerManager uniqueInstance;
	private ArrayList<Logger> loggersWithName;
	
	private LoggerManager() {
		this.loggersWithName = new ArrayList<>();
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
		for (Logger logger : this.loggersWithName) {
			if (logger.getName() == name) {
				return logger;
			}
		}
		return null;
	}

	public boolean addLogger(Logger loggerWithName) {
		if (loggerWithNameExists(loggerWithName.getName())) {
			return false;
		}
		this.loggersWithName.add(loggerWithName);
		return true;
	}
	
	public void deleteLogger(String name) {
		for (int i = 0; i < this.loggersWithName.size(); i++) {
			if (this.loggersWithName.get(i).getName() == name) {
				this.loggersWithName.remove(i);
				break;
			}
		}		
	}
	
	private boolean loggerWithNameExists(String name) {
		for (Logger logger : loggersWithName) {
			if (logger.getName() == name) {
				return true;
			}
		}
		return false;
	}

}
