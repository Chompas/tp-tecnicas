package ar.fiuba.tecnicas.logging;

import java.util.Date;

import ar.fiuba.tecnicas.logging.config.LogLevel;

public interface ILogger {

	public void log(Date date, String message, LogLevel level);
	
}
