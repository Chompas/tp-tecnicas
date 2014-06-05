package ar.fiuba.tecnicas.logging;

import java.util.Date;

import ar.fiuba.tecnicas.logging.config.LogLevel;

public interface ILogger {

	public void log(Date date, String message, LogLevel level);
	public void log(Date date, String message, LogLevel level, String loggerName);
	public void log(Date date, String message, LogLevel level, Throwable e);
	public void log(Date date, String message, LogLevel level, Throwable e, String loggerName);
	
}
