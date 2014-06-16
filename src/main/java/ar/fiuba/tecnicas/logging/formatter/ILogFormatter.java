package ar.fiuba.tecnicas.logging.formatter;

import java.util.Date;

import ar.fiuba.tecnicas.logging.config.LogLevel;

public interface ILogFormatter {

	public String format(Date date, String message, LogLevel level);
	public String format(Date date, String message, LogLevel level, String loggerName);
	
}
