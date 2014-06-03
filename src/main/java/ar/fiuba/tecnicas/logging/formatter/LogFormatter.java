package ar.fiuba.tecnicas.logging.formatter;

import ar.fiuba.tecnicas.logging.config.LogLevel;

public class LogFormatter implements ILogFormatter {

	private String format;
	private String separator;
	
	

	public LogFormatter(String format, String separator) {
		super();
		
		if (format.equals("")) {
			//default format
			this.format = "[%p] %n %m";
		} else {
			this.format = format;
		}
		
		if (separator.equals("")) {
			//default separator
			this.separator = "-";
		} else {
			this.separator = separator;
		}
	}

	@Override
	public String format(String message, LogLevel level) {


	}
	


}
