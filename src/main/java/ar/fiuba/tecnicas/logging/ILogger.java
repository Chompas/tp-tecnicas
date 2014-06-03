package ar.fiuba.tecnicas.logging;

import ar.fiuba.tecnicas.logging.config.LogLevel;

public interface ILogger {

	public void log(String message, LogLevel level);
	
}
