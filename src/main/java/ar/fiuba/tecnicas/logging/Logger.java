package ar.fiuba.tecnicas.logging;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.config.LoggerConfig;
import ar.fiuba.tecnicas.logging.formatter.ILogFormatter;
import ar.fiuba.tecnicas.logging.formatter.LogFormatter;
import ar.fiuba.tecnicas.logging.formatter.LogMessage;
import ar.fiuba.tecnicas.logging.handlers.IHandler;

public class Logger implements ILogger {

	private static String EMPTY_LOGGER_NAME = "";
	private static String EMPTY_FILTER_REGEX = "";
	private List<IHandler> outputs = new ArrayList<>();
	private LoggerConfig config = new LoggerConfig();
	private ILogFormatter logFormatter;
	private Filter filter;
	private String filterRegex = EMPTY_FILTER_REGEX;
	private CustomFilter customFilter = new CustomFilter();
	private String name = EMPTY_LOGGER_NAME;
	
	public Logger() {
		init();
	}
	
	public Logger(String name) {
		init();
		this.name = name;
	}

	public void log(Date date, String message, LogLevel level) {
		LogMessage logMessage = filter(date, message, level, name);
		write(logMessage);
	}
	
	
	public void log(Date date, String message, LogLevel level, Throwable e) {
		LogMessage logMessage = filter(date, message, level, name);
		logMessage.addException(e);
		write(logMessage);
	}
	
	public void addHandler(IHandler handler) {
		this.outputs.add(handler);
	}

	public void addFilterRegex(String filterRegex) {
		this.filterRegex = filterRegex;
	}
	
	public void addCustomFilter(CustomFilter customFilter) {
		this.customFilter = customFilter;
	}
	
	public String getName() {
		return this.name;
	}
	
	private void init() {
		this.filter = new Filter(this.config.getGlobalLogLevel());
		this.logFormatter = new LogFormatter(this.config.getFormat(), this.config.getSeparator());
		this.addHandlersFromConfig();
	}
	
	private LogMessage filter(Date date, String message, LogLevel level, String loggerName) {
		String plainMessage = this.logFormatter.format(date, message, level, loggerName);
		LogMessage logMessage = new LogMessage(date, this.config.getFormat(), this.config.getSeparator(), plainMessage, level);
		String filteredMessage = this.filter.filter(logMessage, level, filterRegex, customFilter);

		return new LogMessage(date, this.config.getFormat(), this.config.getSeparator(), filteredMessage, level);
	}
	
	private void write(LogMessage logMessage) {
		if (logMessage.getPlainMessage() != "") {
			for (IHandler handler : this.outputs) {
				handler.write(logMessage);
			}
		}		
	}
	
	private void addHandlersFromConfig() {
		for (IHandler handler : this.config.getHandlers()) {
			this.outputs.add(handler);
		}
	}
}
