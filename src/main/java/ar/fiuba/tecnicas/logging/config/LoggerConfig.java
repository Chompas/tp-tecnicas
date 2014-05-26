package ar.fiuba.tecnicas.logging.config;

import java.io.File;
import java.util.List;

import ar.fiuba.tecnicas.logging.handlers.HandlerFactory;
import ar.fiuba.tecnicas.logging.handlers.IHandler;

public class LoggerConfig {

	private IProperties properties;

	public LoggerConfig() {
		String filename = "config.properties";
		File file = new File(filename);
		
		if (file.exists()) {
			this.properties = new JavaProperties(filename);
		} else {
			this.properties = new DefaultProperties();
		}
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
