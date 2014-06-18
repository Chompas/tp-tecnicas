package ar.fiuba.tecnicas.logging.config;

import java.util.Date;

import ar.fiuba.tecnicas.logging.ILogger;
import ar.fiuba.tecnicas.logging.LogLevel;
import ar.fiuba.tecnicas.logging.LogMessage;
import ar.fiuba.tecnicas.logging.formatter.LogFormatter;
import ar.fiuba.tecnicas.logging.handlers.ConsoleHandler;
import ar.fiuba.tecnicas.logging.handlers.IHandler;

public class LoggerDefault implements ILogger {
	
	private String separator;
	private IHandler handler;
	private String format;
	private String name;
	private LogFormatter formatter;
	
	public LoggerDefault() {
		this.handler = new ConsoleHandler();
		this.format = "[%p] %n %m";
		this.separator = "-";
		this.formatter = new LogFormatter(this.format, this.separator);
		this.name = "Default";
	}
	
	public String getSeparator() {
		return this.separator;
	}
	
	public String getFormat() {
		return this.format;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void log(Date date, String message, LogLevel level) {
		LogMessage logMessage = new LogMessage(date, formatter, message, level);
		this.handler.write(logMessage.getFormattedMessage());
	}

	@Override
	public void log(Date date, String message, LogLevel level, Throwable e) {
		LogMessage logMessage = new LogMessage(date, formatter, message, level);
		logMessage.addException(e);
		this.handler.write(logMessage.getFormattedMessage());
	}
}
