package ar.fiuba.tecnicas.logging;

import java.util.ArrayList;

public final class LoggersWithNameFactory {
	
	private static LoggersWithNameFactory uniqueInstance;
	private ArrayList<LoggerWithName> loggersWithName;
	
	private LoggersWithNameFactory() {
		this.loggersWithName = new ArrayList<>();
	}
	
	public static LoggersWithNameFactory getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new LoggersWithNameFactory();
		}
		return uniqueInstance;		
	}
	
	public LoggerWithName getLogger(String name) {
		for (LoggerWithName logger : this.loggersWithName) {
			if (logger.getName() == name) {
				return logger;
			}
		}
		return null;
	}

	public boolean addLogger(LoggerWithName loggerWithName) {
		if (loggerWithNameExists(loggerWithName.getName())) {
			return false;
		}
		this.loggersWithName.add(loggerWithName);
		return true;
	}
	
	private boolean loggerWithNameExists(String name) {
		for (LoggerWithName logger : loggersWithName) {
			if (logger.getName() == name) {
				return true;
			}
		}
		return false;
	}

}
