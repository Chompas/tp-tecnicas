package ar.fiuba.tecnicas.logging.config;

import java.util.Date;

public interface ILogger {

	public void log(Date date, String message, LogLevel level);
	public void log(Date date, String message, LogLevel level, Throwable e);
	public String getName();
	
}
