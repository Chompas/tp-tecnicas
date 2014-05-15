package ar.fiuba.tecnicas.logging;

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
	
	private boolean showLevel(LogLevel level) {
		return (level.getCode() <= config.getGlobalLogLevel().getCode()); 
	}
	
	public Logger(String configPath) {
		
		this.config = new LoggerConfig(configPath);
		this.outputs = new ArrayList<IHandler>();
		
		logFormatter = new LogFormatter(this.config.getFormat());
		
	}
	
	public void log(String message, LogLevel level) {
		if (showLevel(level)) {
			String formattedMessage = logFormatter.format(message, level);
			for (IHandler handler : this.outputs){
				handler.write(formattedMessage);
			}
		}
	}
	
	public void addHandler(IHandler handler) {
		outputs.add(handler);
	}
}
