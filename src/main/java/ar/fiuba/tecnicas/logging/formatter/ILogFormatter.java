package ar.fiuba.tecnicas.logging.formatter;

import java.util.Date;

import ar.fiuba.tecnicas.logging.config.LogLevel;

public interface ILogFormatter {

	public LogMessage format(Date date, String message, LogLevel level);
	
}
