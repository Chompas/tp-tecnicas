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
	private Filter filter;

	public Logger() {		
		this.config = new LoggerConfig();
		this.filter = new Filter(this.config.getGlobalLogLevel());
		this.outputs = new ArrayList<>();
		this.logFormatter = new LogFormatter(this.config.getFormat(), this.config.getSeparator());

		this.addHandlersFromConfig();		
	}

	public void log(String message, LogLevel level) {
		String filteredMessage = this.filter.filter(message, level);
		String formattedMessage = this.logFormatter.format(filteredMessage, level);
		for (IHandler handler : this.outputs) {
			handler.write(formattedMessage);
		}
	}

	public void addHandler(IHandler handler) {
		this.outputs.add(handler);
	}

	private void addHandlersFromConfig() {
		for (IHandler handler : this.config.getHandlers()) {
			this.outputs.add(handler);
		}
	}
}
