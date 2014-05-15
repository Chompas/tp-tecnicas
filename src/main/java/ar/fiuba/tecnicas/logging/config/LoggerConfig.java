package ar.fiuba.tecnicas.logging.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import ar.fiuba.tecnicas.logging.handlers.HandlerFactory;
import ar.fiuba.tecnicas.logging.handlers.IHandler;

public class LoggerConfig {

	private IProperties properties;
	
	public LoggerConfig(File configFile) {
		FileInputStream xmlConfig;
		
		try {
			xmlConfig = new FileInputStream(configFile);
			this.properties = new XmlProperties(xmlConfig);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public List<IHandler> getHandlers()
	{
		HandlerFactory factory = new HandlerFactory();		
		return factory.createHandlers(this.properties.getValue("Outputs"));
	}
	
}
