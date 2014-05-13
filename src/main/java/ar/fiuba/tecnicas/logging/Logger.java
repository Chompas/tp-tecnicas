package ar.fiuba.tecnicas.logging;

import java.util.ArrayList;
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
		this.outputs = new ArrayList<IHandler>();
	}
	
	public void log(String message, LogLevel level) {
		for (IHandler handler : this.outputs){
			handler.write(message);
		}
	}

	public void log(String message, LogLevel level, ILogFormatter formatter) {
		
	}
	
	public void addHandler(IHandler handler) {
		outputs.add(handler);
	}
}
