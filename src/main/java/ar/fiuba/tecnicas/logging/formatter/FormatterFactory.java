package ar.fiuba.tecnicas.logging.formatter;

public class FormatterFactory {

	public ILogFormatter createFormatter(String type, String format, String separator) {
		switch(type) {
			case("json"):
				return new JsonLogFormatter();
			case("text"):
				return new TextLogFormatter(format, separator);
			default:
				return new TextLogFormatter(format, separator);
		}
	}

}
