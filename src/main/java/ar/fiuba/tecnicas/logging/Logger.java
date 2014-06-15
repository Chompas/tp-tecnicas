package ar.fiuba.tecnicas.logging;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.config.LoggerConfig;
import ar.fiuba.tecnicas.logging.formatter.ILogFormatter;
import ar.fiuba.tecnicas.logging.formatter.ILogMessage;
import ar.fiuba.tecnicas.logging.formatter.LogFormatter;
import ar.fiuba.tecnicas.logging.formatter.LogMessage;
import ar.fiuba.tecnicas.logging.handlers.IHandler;

public class Logger implements ILogger {

	private static String EMPTY_LOGGER_NAME = "";
	private List<IHandler> outputs;
	private LoggerConfig config;
	private ILogFormatter logFormatter;
	private Filter filter;
	private String filterRegex;
	private CustomFilter customFilter;

	public Logger() {		
		this.config = new LoggerConfig();
		this.filter = new Filter(this.config.getGlobalLogLevel());
		this.outputs = new ArrayList<>();
		this.logFormatter = new LogFormatter(this.config.getFormat(), this.config.getSeparator());
		this.filterRegex = "";
		this.customFilter = new CustomFilter();

		this.addHandlersFromConfig();
	}

	public void log(Date date, String message, LogLevel level) {
		ILogMessage logMessage = filter(date, message, level, EMPTY_LOGGER_NAME);
		write(logMessage);
	}
	
	public void log(Date date, String message, LogLevel level, String loggerName) {
		ILogMessage logMessage = filter(date, message, level, loggerName);
		write(logMessage);
	}
	
	public void log(Date date, String message, LogLevel level, Throwable e) {
		ILogMessage logMessage = filter(date, message, level, EMPTY_LOGGER_NAME);
		logMessage.addException(e);
		write(logMessage);
	}
	
	public void log(Date date, String message, LogLevel level, Throwable e, String loggerName) {
		ILogMessage logMessage = filter(date, message, level, loggerName);
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
	
	private ILogMessage filter(Date date, String message, LogLevel level, String loggerName) {
		LogMessage logMessage = this.logFormatter.format(date, message, level, loggerName);
		return this.filter.filter(logMessage, level,this.filterRegex, this.customFilter);
	}
	
	private void write(ILogMessage logMessage) {
		for (IHandler handler : this.outputs) {
			handler.write(logMessage);
		}
	}
	
	private void addHandlersFromConfig() {
		for (IHandler handler : this.config.getHandlers()) {
			this.outputs.add(handler);
		}
	}
}
