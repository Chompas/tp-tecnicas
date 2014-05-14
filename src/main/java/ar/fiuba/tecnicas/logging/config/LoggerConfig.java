package ar.fiuba.tecnicas.logging.config;

public class LoggerConfig {

	private LogLevel globalLogLevel;
	private IProperties properties;
	
	public LoggerConfig(IProperties properties) {
		this.properties = properties;
	}
	
	public void setGlobalLogLevel() {
		//TODO
	}

	public LogLevel getGlobalLogLevel() {
		//TODO
		return null;
	}
}
