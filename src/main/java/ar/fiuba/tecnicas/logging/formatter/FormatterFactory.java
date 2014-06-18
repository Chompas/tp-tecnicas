package ar.fiuba.tecnicas.logging.formatter;

import ar.fiuba.tecnicas.logging.config.LoggerDefault;

public class FormatterFactory {

	public ILogFormatter createFormatter(String format) {
		LoggerDefault defaultLogger = new LoggerDefault();
		switch(format) {
			case("json"):
				return new JsonLogFormatter();
			case("text"):
				return new LogFormatter(defaultLogger.getFormat(), defaultLogger.getSeparator());
			default:
				return new LogFormatter(defaultLogger.getFormat(), defaultLogger.getSeparator());
		}
	}

}
