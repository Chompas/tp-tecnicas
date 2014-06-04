package ar.fiuba.tecnicas.logging;

import java.util.Date;

import ar.fiuba.tecnicas.logging.config.LogLevel;

public class LoggerWithName extends LoggerDecorator {
	
	private String name = "";

	public LoggerWithName(ILogger logger, String name) {
		super(logger);
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public void log(Date date, String message, LogLevel level) {
		this.logger.log(date, message, level);
	}

}
