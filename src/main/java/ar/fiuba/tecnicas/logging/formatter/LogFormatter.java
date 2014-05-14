package ar.fiuba.tecnicas.logging.formatter;

import ar.fiuba.tecnicas.logging.config.LogLevel;


public class LogFormatter implements ILogFormatter {
	
	private String format;

	public LogFormatter() {
		super();
		//TODO: Agregar formato por defecto
	}
	
	public LogFormatter(String format) {
		super();
		this.format = format;
	}

	@Override
	public String format(String message, LogLevel level) {
		
		//TODO: Modificar mensaje de acuerdo al format
	
		return Thread.currentThread().getStackTrace()[3].getLineNumber() + " - " + message + "-" + level;
	}

}
