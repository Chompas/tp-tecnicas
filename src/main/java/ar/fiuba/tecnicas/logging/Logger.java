package ar.fiuba.tecnicas.logging;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.config.LoggerConfig;
import ar.fiuba.tecnicas.logging.formatter.ILogFormatter;
import ar.fiuba.tecnicas.logging.formatter.LogFormatter;
import ar.fiuba.tecnicas.logging.handlers.IHandler;

public class Logger {
	
	private List<IHandler> outputs;
	private LoggerConfig config;
	private ILogFormatter logFormatter;
	private Filter filter;
	
	public Logger(File configFile) {		
		this.config = new LoggerConfig(configFile);
		this.filter = new Filter(this.config.getGlobalLogLevel());
		this.outputs = new ArrayList<IHandler>();
		this.logFormatter = new LogFormatter(this.config.getFormat());		
	}
	
	public void log(String message, LogLevel level) {
		String filteredMessage = this.filter.filter(message, level);
		String formattedMessage = this.logFormatter.format(filteredMessage, level);
		for (IHandler handler : this.outputs){
			handler.write(formattedMessage);
		}
	}
	
	public void addHandler(IHandler handler) {
		outputs.add(handler);
	}
}
