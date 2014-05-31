package ar.fiuba.tecnicas.logging;

import ar.fiuba.tecnicas.logging.config.LogLevel;

public class Filter {

	private LogLevel globalLogLevel;

	public Filter(LogLevel globalLogLevel) {
		this.globalLogLevel = globalLogLevel;
	}

	public String filter(String message, LogLevel level, String filterRegex) {
		if (shouldShowMessage(level) && shouldShowMessage(message, filterRegex)) {
			return message;
		} else {
			return "";
		}
	}

	private boolean shouldShowMessage(LogLevel level) {
		return level.getCode() <= globalLogLevel.getCode();
	}

	private boolean shouldShowMessage(String message, String filterRegex) {
		boolean result = true;
		if (!filterRegex.equals("")) {
			result = message.matches(filterRegex);
		}
		return result;
	}

}
