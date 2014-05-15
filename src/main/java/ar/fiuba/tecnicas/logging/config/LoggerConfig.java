package ar.fiuba.tecnicas.logging.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LoggerConfig {

	private LogLevel globalLogLevel;
	private IProperties properties;
	
	public LoggerConfig(String configPath) {
		FileInputStream xmlConfig;
		try {
			xmlConfig = new FileInputStream(configPath);
			this.properties = new XmlProperties(xmlConfig);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setGlobalLogLevel() {
		//TODO
	}

	public LogLevel getGlobalLogLevel() {
		//TODO: read level from config
		return LogLevel.ERROR;
	}
	
	public String getFormat() {
		return this.properties.getValue("Format");
	}
	
}
