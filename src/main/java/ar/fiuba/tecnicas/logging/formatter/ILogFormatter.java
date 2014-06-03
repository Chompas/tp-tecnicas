package ar.fiuba.tecnicas.logging.formatter;

import ar.fiuba.tecnicas.logging.config.LogLevel;


public interface ILogFormatter {

	public LogMessage format(String message, LogLevel level);
	
}
