package ar.fiuba.tecnicas.logging;

import java.util.List;

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
