package ar.fiuba.tecnicas.logging.formatter;

import ar.fiuba.tecnicas.logging.config.LogMessage;

public interface ILogFormatter {

	public String format(LogMessage logMessage);
	
}
