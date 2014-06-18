package ar.fiuba.tecnicas.logging;

import java.util.ArrayList;
import java.util.Date;

import ar.fiuba.tecnicas.logging.filters.CustomFilter;
import ar.fiuba.tecnicas.logging.filters.Filter;
import ar.fiuba.tecnicas.logging.formatter.ILogFormatter;
import ar.fiuba.tecnicas.logging.handlers.IHandler;

public class Logger implements ILogger {

	private static String EMPTY_LOGGER_NAME = "";
	private static String EMPTY_FILTER_REGEX = "";
	private ArrayList<IHandler> outputs = new ArrayList<>();
	private LogLevel globalLogLevel;
	private ILogFormatter formatter;
	private Filter filter;
	private String filterRegex = EMPTY_FILTER_REGEX;
	private CustomFilter customFilter = new CustomFilter();
	private String name = EMPTY_LOGGER_NAME;
	
	public Logger(LogLevel globalLogLevel, ILogFormatter formatter, ArrayList<IHandler> handlers) {
		this.globalLogLevel = globalLogLevel;
		this.filter = new Filter(globalLogLevel);
		this.formatter = formatter;
		this.outputs = handlers;
	}
	
	public Logger(LogLevel globalLogLevel, ILogFormatter formatter, ArrayList<IHandler> handlers, String name) {
		this(globalLogLevel, formatter, handlers);
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
	
	public void addFilterRegex(String filterRegex) {
		this.filterRegex = filterRegex;
	}
	
	public void addCustomFilter(CustomFilter customFilter) {
		this.customFilter = customFilter;
	}
	
	public String getName() {
		return this.name;
	}
	
	public LogLevel getGlobalLogLevel() {
		return this.globalLogLevel;
	}

	public ArrayList<IHandler> getHandlers() {
		return this.outputs;
	}

	public ILogFormatter getIlogFormatter() {
		return this.formatter;
	}
	
	private LogMessage filter(Date date, String message, LogLevel level, String loggerName) {
		LogMessage logMessage = new LogMessage(date, formatter, message, level);
		String filteredMessage = this.filter.filter(logMessage, level, filterRegex, customFilter);

		return new LogMessage(date, formatter, filteredMessage, level);
	}
	
	private void write(LogMessage logMessage) {
		if (logMessage.getPlainMessage() != "") {
			for (IHandler handler : this.outputs) {
				handler.write(logMessage.getFormattedMessage());
			}
		}		
	}	
}
