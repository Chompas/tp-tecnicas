package ar.fiuba.tecnicas.logging.config;

public enum LogLevel {
	DEBUG(5), 
	INFO(4), 
	WARN(3), 
	ERROR(2), 
	FATAL(1),
	OFF(0);
	
	private int code;

	private LogLevel(int c) {
	  code = c;
	}

	public int getCode() {
	  return code;
	}
}
