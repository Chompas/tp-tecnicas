package ar.fiuba.tecnicas.logging.config;

import java.util.List;

import ar.fiuba.tecnicas.logging.handlers.HandlerFactory;
import ar.fiuba.tecnicas.logging.handlers.IHandler;

public class LoggerConfig {

	private IProperties properties;

	public LoggerConfig(String absolutePathConfigFile) {
		this.properties = new JavaProperties(absolutePathConfigFile);
	}

	public LoggerConfig(IProperties properties) {
		this.properties = properties;
	}

	public LogLevel getGlobalLogLevel() {
		String globalLogLevel = this.properties.getValue("LogLevel");
		return LogLevel.valueOf(globalLogLevel);
	}

	public String getFormat() {
		return this.properties.getValue("Format");
	}

	public String getSeparator() {
		return this.properties.getValue("Separator");
	}

	public List<IHandler> getHandlers()	{
		HandlerFactory factory = new HandlerFactory();		
		return factory.createHandlers(this.properties.getValue("Outputs"));
	}

}
