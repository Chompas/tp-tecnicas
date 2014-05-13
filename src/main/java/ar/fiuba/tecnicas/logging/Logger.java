package ar.fiuba.tecnicas.logging;

import java.util.List;

import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.config.LoggerConfig;
import ar.fiuba.tecnicas.logging.formatter.ILogFormatter;
import ar.fiuba.tecnicas.logging.handlers.IHandler;

public class Logger {
	
	private List<IHandler> outputs;
	
	private LoggerConfig config;
	
	public Logger(LoggerConfig config) {
		this.config = config;
	}
	
	public void log(String message, LogLevel level) {
	
	}

	public void log(String message, LogLevel level, ILogFormatter formatter) {
		
	}
	
	public void addHandler(IHandler handler) {
		
	}
}
