package ar.fiuba.tecnicas.logging.formatter;

import ar.fiuba.tecnicas.logging.config.LogLevel;


public class LogFormatter implements ILogFormatter {
	
	private String format;

	public LogFormatter() {
		super();
	}
	
	public LogFormatter(String format) {
		super();
		this.format = format;
	}

	@Override
	public String format(String message, LogLevel level) {
		return message + "-" + level;
	}

}
