package ar.fiuba.tecnicas.logging.formatter;

import ar.fiuba.tecnicas.logging.config.LogLevel;


public class LogFormatter implements ILogFormatter {

	@Override
	public String format(String message, LogLevel level) {
		return message + "-" + level;
	}

}
