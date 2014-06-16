package ar.fiuba.tecnicas.logging.formatter;

import ar.fiuba.tecnicas.logging.LogMessage;

public interface ILogFormatter {

	public String format(LogMessage logMessage);
	
}
