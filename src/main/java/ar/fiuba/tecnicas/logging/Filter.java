package ar.fiuba.tecnicas.logging;

import ar.fiuba.tecnicas.logging.config.LogLevel;

public class Filter {
	
	private LogLevel globalLogLevel;
	
	public Filter(LogLevel globalLogLevel) {
		this.globalLogLevel = globalLogLevel;
	}
	
	public String filter(String message, LogLevel level) {
		if (shouldShowMessage(level)) {
			return message;
		}
		else {
			return "";
		}
	}
	
	private boolean shouldShowMessage(LogLevel level) {
		return level.getCode() <= globalLogLevel.getCode(); 
	}

}
