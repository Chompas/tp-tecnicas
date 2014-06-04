package ar.fiuba.tecnicas.logging.formatter;

import ar.fiuba.tecnicas.logging.config.DefaultProperties;
import ar.fiuba.tecnicas.logging.config.LogLevel;

public class LogFormatter implements ILogFormatter {

	private String format;
	private String separator;	

	public LogFormatter(String format, String separator) {
		if (format.equals("")) {
			//default format
			this.format = (new DefaultProperties()).getValue("Format");
		} else {
			this.format = format;
		}
		
		if (separator.equals("")) {
			//default separator
			this.separator = (new DefaultProperties()).getValue("Separator");
		} else {
			this.separator = separator;
		}
	}

	@Override
	public LogMessage format(String message, LogLevel level) {
		return new LogMessage(this.format, this.separator, message, level);
	}
	
}
